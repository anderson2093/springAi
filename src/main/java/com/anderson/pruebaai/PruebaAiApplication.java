package com.anderson.pruebaai;

import com.anderson.pruebaai.model.Author;
import com.anderson.pruebaai.model.Book;
import com.anderson.pruebaai.service.IAuthorService;
import com.anderson.pruebaai.service.IBookService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class PruebaAiApplication implements ApplicationRunner {

    private final ResourceLoader resourceLoader;
    private final IAuthorService authorService;
    private final IBookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(PruebaAiApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Resource resource1 = resourceLoader.getResource("classpath:json/authors.json");
        Resource resource2 = resourceLoader.getResource("classpath:json/books.json");

        byte[] jsonData1 = FileCopyUtils.copyToByteArray(resource1.getInputStream());
        byte[] jsonData2 = FileCopyUtils.copyToByteArray(resource2.getInputStream());

        String jsonString1 = new String(jsonData1, StandardCharsets.UTF_8);
        String jsonString2 = new String(jsonData2, StandardCharsets.UTF_8);

        ObjectMapper objectMapper = new ObjectMapper();
        List<Author> authors1 = objectMapper.readValue(jsonString1, new TypeReference<>() {});
        List<Book> books1 = objectMapper.readValue(jsonString2, new TypeReference<>() {});

        authorService.saveAll(authors1);
        bookService.saveAll(books1);
    }
}
