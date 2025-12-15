package com.jmichaelis.branchproject.service;

import com.jmichaelis.branchproject.model.GithubRepo;
import com.jmichaelis.branchproject.model.GithubUser;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class GithubClientWrapperImpl implements GithubClientWrapper {
    private final GitHub github;

    public GithubClientWrapperImpl(GitHub github) {
        this.github = github;
    }

    private List<GithubRepo> mapGhRepoToGithubRepo(Map<String, GHRepository> ghRepositoryMap){
        return ghRepositoryMap.entrySet()
                .stream()
                .map(entry ->
                    new GithubRepo(
                            entry.getKey(),
                            entry.getValue().getHtmlUrl().toString()))
                .toList();
    }

    private GithubUser mapGHUserToGithubUser(GHUser ghUser) throws IOException {
        return GithubUser.builder()
                .login(ghUser.getLogin())
                .avatarUrl(ghUser.getAvatarUrl())
                .url(ghUser.getHtmlUrl().toString())
                .name(ghUser.getName())
                .location(ghUser.getLocation())
                .email(ghUser.getEmail())
                .createdAt(ghUser.getCreatedAt())
                .updatedAt(ghUser.getUpdatedAt())
                .repos(mapGhRepoToGithubRepo(ghUser.getRepositories()))
                .build();
    }

    @Cacheable("GithubUser")
    public GithubUser getUser(String loginId) {
        try {
            return mapGHUserToGithubUser(github.getUser(loginId));
        } catch (IOException e) {
            throw new RuntimeException(String.format("Failed to get user %s from github", loginId));
        }
    }
}
