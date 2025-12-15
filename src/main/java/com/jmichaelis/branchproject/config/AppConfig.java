package com.jmichaelis.branchproject.config;

import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubAbuseLimitHandler;
import org.kohsuke.github.GitHubBuilder;
import org.kohsuke.github.authorization.AuthorizationProvider;
import org.kohsuke.github.connector.GitHubConnector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class AppConfig {

    @Bean
    public GitHub gitHubApiClient() throws IOException {
        return new GitHubBuilder()
                .withAbuseLimitHandler(GitHubAbuseLimitHandler.FAIL)
                .withAuthorizationProvider(AuthorizationProvider.ANONYMOUS)
                .withConnector(GitHubConnector.DEFAULT)
                .build();
    }
}
