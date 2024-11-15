package com.anderson.pruebaai.service.impl;

import com.anderson.pruebaai.model.Book;
import com.anderson.pruebaai.repository.IBookRepo;
import com.anderson.pruebaai.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService {

    private final IBookRepo repo;

    @Override
    public Book save(Book book) throws Exception {
        return repo.save(book);
    }

    @Override
    public List<Book> saveAll(List<Book> t) throws Exception {
        return repo.saveAll(t);
    }

    @Override
    public Book update(Book book, Integer integer) throws Exception {
        return repo.save(book);
    }

    @Override
    public List<Book> findAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public Book findById(Integer integer) throws Exception {
        return repo.findById(integer).orElse(new Book());
    }

    @Override
    public void delete(Integer integer) throws Exception {
        repo.deleteById(integer);
    }
}
