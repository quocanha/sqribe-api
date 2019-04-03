package org.sqribe.api.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.sqribe.api.models.Chapter;

@Repository
public interface ChapterRepository extends CrudRepository<Chapter, Long> {}
