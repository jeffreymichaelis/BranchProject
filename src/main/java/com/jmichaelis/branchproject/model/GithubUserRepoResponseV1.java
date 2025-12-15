package com.jmichaelis.branchproject.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GithubUserRepoResponseV1 {
    private String name;
    private String url;

    public static GithubUserRepoResponseV1 fromGithubRepo(GithubRepo repo) {
        return GithubUserRepoResponseV1.builder()
                .name(repo.getName())
                .url(repo.getUrl())
                .build();
    }
}
