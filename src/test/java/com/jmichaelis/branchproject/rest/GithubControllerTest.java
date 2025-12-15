package com.jmichaelis.branchproject.rest;

import com.jmichaelis.branchproject.model.GithubRepo;
import com.jmichaelis.branchproject.model.GithubUser;
import com.jmichaelis.branchproject.model.GithubUserResponseV1;
import com.jmichaelis.branchproject.service.GithubService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GithubControllerTest {
    @Mock
    private GithubService githubService;

    @InjectMocks
    private GithubController controller;

    @Test
    void getUser_happyPath() {
        String username = "username";
        GithubUser githubUser = getMockedGithubUser();

        when(githubService.getGitHubUserByUsername(username)).thenReturn(githubUser);
        GithubUserResponseV1 response = controller.getUser(username).getBody();

        assertNotNull(response);
    }

    @Test
    void getUser_notFound() {
        String username = "invalidUser";

        when(githubService.getGitHubUserByUsername(username))
                .thenThrow(new RuntimeException("exception"));

        ResponseEntity<GithubUserResponseV1> response = controller.getUser(username);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    private GithubUser getMockedGithubUser() {
        return GithubUser.builder()
                .login("username")
                .avatarUrl("https://example.com/avatar.png")
                .url("https://github.com/username")
                .name("Display Name")
                .location("City, Country")
                .email("user@example.com")
                .createdAt(Date.from(Instant.parse("1970-01-01T00:00:00Z")))
                .updatedAt(Date.from(Instant.parse("1970-01-01T00:00:00Z")))
                .repos(List.of(
                        GithubRepo.builder()
                                .name("repo1")
                                .url("https://github.com/username/repo1")
                                .build(),
                        GithubRepo.builder()
                                .name("repo2")
                                .url("https://github.com/username/repo2")
                                .build()
                ))
                .build();
    }
}