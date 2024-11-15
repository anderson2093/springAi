package com.anderson.pruebaai.repository;

import com.anderson.pruebaai.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBookRepo extends JpaRepository<Book, Integer> {

    // Select *from Book b Where b.name LIKE ?
    List<Book> findByNameLike(String name);
}
