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
import org.sqribe.api.models.Chapter;
import org.sqribe.api.models.Story;
import org.sqribe.api.models.User;
import org.sqribe.api.repositories.AuthorityRepository;
import org.sqribe.api.repositories.ChapterRepository;
import org.sqribe.api.repositories.StoryRepository;
import org.sqribe.api.repositories.UserRepository;

import java.util.Arrays;
import java.util.Collection;

@Configuration
@Profile("development")
public class DevelopmentConfiguration {

    private static final Logger log = LoggerFactory.getLogger(DevelopmentConfiguration.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    StoryRepository storyRepository;

    @Autowired
    ChapterRepository chapterRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthorityRepository authorityRepository;

    public DevelopmentConfiguration() {}


    @EventListener(ApplicationReadyEvent.class)
    public void createData() {
        this.createRoles();

        User admin = new User("admin", this.passwordEncoder.encode("admin"));
        admin.setAuthorities(Arrays.asList(this.authorityRepository.findByAuthority("ROLE_ADMIN")));
        this.userRepository.save(admin);

        User trainer = new User("trainer", this.passwordEncoder.encode("trainer"));
        trainer.setAuthorities(Arrays.asList(this.authorityRepository.findByAuthority("ROLE_TRAINER")));
        this.userRepository.save(trainer);

        String[] countWords = new String[]{
                "first",
                "second",
                "third",
                "fourth",
                "fifth",
                "sixth",
                "seventh",
                "eighth",
                "ninth",
                "tenth"
        };

        for (String storyWord : countWords) {
            Story story = new Story("My " + storyWord + " story");
            story.setOwner(admin);
            for (String chapterWord : countWords) {
                Collection<Chapter> chapters = story.getChapters();
                Chapter chapter = new Chapter("The " + chapterWord + " chapter of the " + storyWord + " story.");
                this.chapterRepository.save(chapter);
                chapters.add(chapter);
                story.setChapters(chapters);
            }

            this.storyRepository.save(story);
        }
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
