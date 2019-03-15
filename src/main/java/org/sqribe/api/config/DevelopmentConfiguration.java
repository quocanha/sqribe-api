package org.sqribe.api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.sqribe.api.models.Authority;
import org.sqribe.api.models.User;
import org.sqribe.api.repositories.AuthorityRepository;
import org.sqribe.api.repositories.UserRepository;

import java.util.Arrays;

@Configuration
@Profile("development")
public class DevelopmentConfiguration {

    private static final Logger log = LoggerFactory.getLogger(DevelopmentConfiguration.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthorityRepository authorityRepository;

    public DevelopmentConfiguration() {}


    @EventListener(ApplicationReadyEvent.class)
    public void createData() {
        this.createRoles();
        this.createTestAccounts();
    }

    private void createTestAccounts() {
        User admin = new User("admin", this.passwordEncoder.encode("admin"));
        admin.setAuthorities(Arrays.asList(this.authorityRepository.findByAuthority("ROLE_ADMIN")));
        this.userRepository.save(admin);

        User trainer = new User("trainer", this.passwordEncoder.encode("trainer"));
        trainer.setAuthorities(Arrays.asList(this.authorityRepository.findByAuthority("ROLE_TRAINER")));
        this.userRepository.save(trainer);
    }

    private void createRoles() {
        this.createAuthorityIfNotFound("ROLE_ADMIN");
        this.createAuthorityIfNotFound("ROLE_MANAGER");
        this.createAuthorityIfNotFound("ROLE_TRAINER");
        this.createAuthorityIfNotFound("ROLE_COORDINATOR");
        this.createAuthorityIfNotFound("ROLE_COACH");
    }


    private Authority createAuthorityIfNotFound(String authorityStr) {
        Authority authority = this.authorityRepository.findByAuthority(authorityStr);
        if (authority == null) {
            authority = new Authority(authorityStr);
            this.authorityRepository.save(authority);
        }
        return authority;
    }

}
