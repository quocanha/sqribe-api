package org.sqribe.api.story;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface StoryRepository extends PagingAndSortingRepository<Story, Long> {
}
