package com.anderson.pruebaai.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/functions")
@RequiredArgsConstructor
public class FunctionCallingController {

    private final OpenAiChatModel openAiChatModel;

    @GetMapping
    public ResponseEntity<String>getWeatherInfo(){
        UserMessage userMessage = new UserMessage("What's the weather in San Francisco?");
        ChatResponse response = openAiChatModel.call(new Prompt(List.of(userMessage)));
        String result = response.getResult().getOutput().getContent();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/book")
    public ResponseEntity<String>getBookInfo(@RequestParam("bookName") String bookName){
        UserMessage userMessage = new UserMessage("Cual es la informacion de este libro" + bookName+"?");
        ChatResponse response = openAiChatModel.call(new Prompt(List.of(userMessage),
                OpenAiChatOptions.builder().withFunction("BookInfo").build()));
        String result = response.getResult().getOutput().getContent();
        return ResponseEntity.ok(result);
    }
}
