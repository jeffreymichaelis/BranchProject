package com.jmichaelis.branchproject.service;

import com.jmichaelis.branchproject.model.GithubUser;

public interface GithubClientWrapper {
    GithubUser getUser(String loginId);
}
