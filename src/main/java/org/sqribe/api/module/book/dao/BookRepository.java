package org.sqribe.api.module.book.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.sqribe.api.module.book.domain.Book;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByTitle(String title);
}
