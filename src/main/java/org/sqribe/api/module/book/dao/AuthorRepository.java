package org.sqribe.api.module.book.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.sqribe.api.module.book.domain.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

}
