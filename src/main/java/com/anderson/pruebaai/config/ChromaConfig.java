package com.anderson.pruebaai.config;

import org.springframework.ai.chroma.ChromaApi;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.ai.vectorstore.ChromaVectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;


@Configuration
public class ChromaConfig {

    @Bean
    public ChromaVectorStore chromaVectorStore(OpenAiEmbeddingModel embeddingModel,ChromaApi chromaApi){
        return new ChromaVectorStore(embeddingModel,chromaApi,true);
    }
}
