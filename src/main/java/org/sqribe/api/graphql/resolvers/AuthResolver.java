package org.sqribe.api.graphql.resolvers;

import graphql.schema.DataFetcher;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.sqribe.api.models.User;
import org.sqribe.api.security.UserDetailsImpl;

@Component
public class AuthResolver {
    public DataFetcher getPrincipal() {
        return environment -> {
            UserDetailsImpl userDetails = (UserDetailsImpl)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return userDetails.getUser();
        };
    }
}
