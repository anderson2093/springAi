package com.anderson.pruebaai.controller;

import com.anderson.pruebaai.dto.AuthorBook;
import com.anderson.pruebaai.dto.ResponseDTO;
import com.anderson.pruebaai.model.Author;
import com.anderson.pruebaai.service.IAuthorService;
import com.anderson.pruebaai.util.ChatHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chats")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ChatController {

    private final OpenAiChatModel openAiChatModel;
    private final ChatHistory chatHistory;
    private final IAuthorService service;
    @GetMapping("/generate")
    public ResponseEntity<ResponseDTO<String>> generateText(@RequestParam String message) {
        ChatResponse chatResponse=openAiChatModel.call(new Prompt(message));
        String result = chatResponse.getResult().getOutput().getContent();
        return ResponseEntity.ok(new ResponseDTO<>(200,"success",result));
    }

    @GetMapping("/generate/prompt")
    public ResponseEntity<ResponseDTO<String>> generateTextPrompt(@RequestParam String author, @RequestParam String bookName) {
        PromptTemplate promptTemplate = new PromptTemplate("Tell me about {author} and his book {bookName} and only print 500 characters in spanish");
        Prompt prompt = promptTemplate.create(Map.of("author", author, "bookName", bookName));

        ChatResponse chatResponse = openAiChatModel.call(prompt);
        String result = chatResponse.getResult().getOutput().getContent();
        return ResponseEntity.ok(new ResponseDTO<>(200,"success",result));
    }

    @GetMapping("/generate/output")
    public ResponseEntity<AuthorBook> generateTextOutput(@RequestParam String author) {
        BeanOutputConverter<AuthorBook> outputConverter = new BeanOutputConverter<>(AuthorBook.class);
        String template = """
                Tell me book titles of {author}. {format} in spanish
                """;
        PromptTemplate promptTemplate = new PromptTemplate(template,Map.of("author", author,"format", outputConverter.getFormat()));
        Prompt prompt = promptTemplate.create(Map.of("author", author));

        Generation generation = openAiChatModel.call(prompt).getResult();
        AuthorBook authorBook = outputConverter.convert(generation.getOutput().getContent());
        return ResponseEntity.ok(authorBook);
    }

    @GetMapping("/generateConversation")
    public ResponseEntity<ResponseDTO<String>> generateConversation(@RequestParam String message) {
        chatHistory.addMessage("1",new UserMessage(message));
        ChatResponse chatResponse = openAiChatModel.call(new Prompt(chatHistory.getAll("1")));
       String result = chatResponse.getResult().getOutput().getContent();
       return ResponseEntity.ok(new ResponseDTO<>(200,"success",result));
    }

    @GetMapping("/generate/output-authors-bd")
    public ResponseEntity<List<AuthorBook>> generateTextOutputAuthors() throws Exception {
        List<Author> authors = getAllAuthors();
        List<AuthorBook> authorBooks = new ArrayList<>();

        BeanOutputConverter<AuthorBook> outputConverter = new BeanOutputConverter<>(AuthorBook.class);

        for (Author author : authors) {
            String template = """
                Tell me book titles of {author}. {format} in Spanish.
                """;
            PromptTemplate promptTemplate = new PromptTemplate(template, Map.of("author", author.getFirstname()+" "+author.getLastname(), "format", outputConverter.getFormat()));
            Prompt prompt = promptTemplate.create(Map.of("author", author.getFirstname()+" "+author.getLastname()));

            Generation generation = openAiChatModel.call(prompt).getResult();
            AuthorBook authorBook = outputConverter.convert(generation.getOutput().getContent());
            authorBooks.add(authorBook);
        }

        return ResponseEntity.ok(authorBooks);
    }

    public List<Author> getAllAuthors() throws Exception {
        return service.findAll();
    }
}
