package org.sqribe.api.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.sqribe.api.models.Story;
import org.sqribe.api.models.User;

import java.util.Collection;

@Repository
public interface StoryRepository extends CrudRepository<Story, Long> {

    Collection<Story> findAll();

    Collection<Story> findByOwner(User user);
}
