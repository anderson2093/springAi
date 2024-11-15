package com.anderson.pruebaai.repository;

import com.anderson.pruebaai.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthorRepo extends JpaRepository<Author, Integer> {
}
