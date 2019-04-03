package org.sqribe.api.graphql;

import com.google.common.collect.ImmutableMap;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.sqribe.api.graphql.resolvers.AuthResolver;
import org.sqribe.api.graphql.resolvers.StoryResolver;
import org.sqribe.api.models.Story;
import org.sqribe.api.models.User;
import org.sqribe.api.repositories.StoryRepository;
import org.sqribe.api.repositories.UserRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Component
public class GraphQLDataFetchers {

    @Autowired
    StoryResolver story;

    @Autowired
    AuthResolver auth;
}
