package com.jmichaelis.branchproject.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GithubUserResponseV1 {
    @JsonProperty("user_name")
    String userName;

    @JsonProperty("display_name")
    String displayName;

    @JsonProperty("avatar")
    String avatar;

    @JsonProperty("geo_location")
    String geoLocation;

    @JsonProperty("email")
    String email;

    @JsonProperty("url")
    String url;

    @JsonProperty("created_at")
    String createdAt;

    @JsonProperty("repos")
    List<GithubUserRepoResponseV1> repos;

    public static GithubUserResponseV1 fromGithubUser(GithubUser gitHubUser){
        return GithubUserResponseV1.builder()
                .userName(gitHubUser.getLogin())
                .displayName(gitHubUser.getName())
                .avatar(gitHubUser.getAvatarUrl())
                .geoLocation(gitHubUser.getLocation())
                .email(gitHubUser.getEmail())
                .url(gitHubUser.getUrl())
                .createdAt(gitHubUser.getCreatedAt().toString())
                .repos(gitHubUser.getRepos().stream()
                        .map(GithubUserRepoResponseV1::fromGithubRepo)
                        .toList())
                .build();
    }
}
