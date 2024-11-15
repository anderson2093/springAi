package com.anderson.pruebaai.service.impl;

import com.anderson.pruebaai.model.Author;
import com.anderson.pruebaai.repository.IAuthorRepo;
import com.anderson.pruebaai.service.IAuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements IAuthorService {

    private final IAuthorRepo repo;

    @Override
    public Author save(Author author) throws Exception {
        return repo.save(author);
    }

    @Override
    public List<Author> saveAll(List<Author>authors) throws Exception {
        return repo.saveAll(authors);
    }

    @Override
    public Author update(Author author, Integer integer) throws Exception {
        return repo.save(author);
    }

    @Override
    public List<Author> findAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public Author findById(Integer integer) throws Exception {
        return repo.findById(integer).orElse(new Author());
    }

    @Override
    public void delete(Integer integer) throws Exception {
        repo.deleteById(integer);
    }
}
