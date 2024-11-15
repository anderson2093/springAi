package com.anderson.pruebaai.dto;

import java.util.List;

public record AuthorBook(
        String author,
        List<String>books
)
{ }
