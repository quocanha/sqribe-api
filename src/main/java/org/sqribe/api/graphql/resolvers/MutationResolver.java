package org.sqribe.api.graphql.resolvers;

import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.sqribe.api.models.Story;
import org.sqribe.api.repositories.StoryRepository;
import org.sqribe.api.security.UserDetailsImpl;

import java.util.Map;

@Component
public class MutationResolver {

    @Autowired
    StoryRepository storyRepository;

    public DataFetcher updateStory() {
        return environment -> {
            // The graphql specification dictates that input object arguments MUST
            // be maps.  You can convert them to POJOs inside the data fetcher if that
            // suits your code better
            //
            // See http://facebook.github.io/graphql/October2016/#sec-Input-Objects
            //

            long storyId = Long.parseLong(environment.getArgument("id"));

            String title = environment.getArgument("title");
            String description = environment.getArgument("description");

            if(storyRepository.findById(storyId).isPresent()) {
                Story story = storyRepository.findById(storyId).get();

                story.setTitle(title);
                story.setDescription(description);

                storyRepository.save(story);

                return story;
            }
            else
            {
                throw new Exception("Not found");
            }
        };
    }
}
