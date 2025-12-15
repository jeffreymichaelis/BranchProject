package com.jmichaelis.branchproject.service;

import com.jmichaelis.branchproject.model.GithubUser;
import org.springframework.stereotype.Service;

@Service
public class GithubServiceImpl implements GithubService {
    GithubClientWrapper githubClientWrapper;

    GithubServiceImpl(GithubClientWrapper githubClientWrapper){
        this.githubClientWrapper = githubClientWrapper;
    }

    @Override
    public GithubUser getGitHubUserByUsername(String username) {
        return githubClientWrapper.getUser(username);
    }
}
