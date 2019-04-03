package org.sqribe.api.graphql.resolvers;

import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sqribe.api.models.User;
import org.sqribe.api.repositories.StoryRepository;
import org.sqribe.api.repositories.UserRepository;

import java.util.Collection;

@Component
public class StoryResolver {

    @Autowired
    StoryRepository storyRepository;

    @Autowired
    UserRepository userRepository;



    public DataFetcher getStory() {
        return environment -> {
            long storyId = Long.parseLong(environment.getArgument("id"));
            org.sqribe.api.models.Story story = storyRepository.findById(storyId).get();
            return story;
        };
    }

    public DataFetcher getStories() {
        return environment -> {
            if(environment.containsArgument("id")) {
                long userId = Long.parseLong(environment.getArgument("id"));
                User user = userRepository.findById(userId).orElse(null);
                if(user == null) {
                    throw new Exception("User(" + userId +") not found.");
                }
                Collection<org.sqribe.api.models.Story> stories = storyRepository.findByOwner(user);
                return stories;
            } else {
                Collection<org.sqribe.api.models.Story> stories = storyRepository.findAll();
                return stories;
            }
        };
    }
}
