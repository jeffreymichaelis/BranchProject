package com.jmichaelis.branchproject.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class GithubUser {
    private String login;
    private String avatarUrl;
    private String url;
    private String name;
    private String location;
    private String email;
    private Date createdAt;
    private Date updatedAt;
    private List<GithubRepo> repos;
}
