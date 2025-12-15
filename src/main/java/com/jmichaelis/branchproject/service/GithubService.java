package com.jmichaelis.branchproject.service;

import com.jmichaelis.branchproject.model.GithubUser;

public interface GithubService {
    GithubUser getGitHubUserByUsername(String username);
}
