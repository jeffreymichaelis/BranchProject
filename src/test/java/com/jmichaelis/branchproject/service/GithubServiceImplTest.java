package com.jmichaelis.branchproject.service;

import com.jmichaelis.branchproject.model.GithubUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class GithubServiceImplTest {
    @Mock
    private GithubClientWrapper githubClientWrapper;

    @InjectMocks
    private GithubServiceImpl service;

    @Test
    void getGitHubUserByUsername() {
        String username = "username";
        GithubUser expected = GithubUser.builder()
                .login(username)
                .build();

        when(githubClientWrapper.getUser(username)).thenReturn(expected);
        GithubUser actual = service.getGitHubUserByUsername(username);
        assertSame(expected, actual);
    }
}