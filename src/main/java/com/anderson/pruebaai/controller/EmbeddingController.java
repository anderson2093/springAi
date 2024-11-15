package com.anderson.pruebaai.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.ai.vectorstore.ChromaVectorStore;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/embeddings")
@RequiredArgsConstructor
public class EmbeddingController {

    private final OpenAiEmbeddingModel openAiEmbeddingModel;
    private final ChromaVectorStore vectorStore;

    @GetMapping("/generate")
    public Map<String, EmbeddingResponse> generateEmbeddings(@RequestParam("message") String message) {
        EmbeddingResponse response = openAiEmbeddingModel.embedForResponse(List.of(message));
        return Map.of("embedding", response);
    }

    @GetMapping("/vectorstore")
    public List<Document> useVectorStore(@RequestParam("message") String message) {
        List<Document> documents = List.of(
                new Document("Spring AI es los maximo",Map.of("meta1","meta1")),
                new Document("Python es más popular en IA"),
                new Document("El futuro es la Inteligencia Artificial",Map.of("meta1","meta1"))
        );
        vectorStore.add(documents);
        List<Document> results=vectorStore.similaritySearch(SearchRequest.query(message).withTopK(1));
        return results;
    }
}
