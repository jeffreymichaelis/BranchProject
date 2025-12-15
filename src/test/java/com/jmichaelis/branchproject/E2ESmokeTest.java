package com.jmichaelis.branchproject;

import com.jmichaelis.branchproject.model.GithubUserResponseV1;
import com.jmichaelis.branchproject.rest.GithubController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class E2ESmokeTest {

    @Autowired
    GithubController githubController;

    @Test
    void contextLoads() {
    }

    @Test
    public void smokeTest(){
        // Will rate limit at 60 requests per hour
        ResponseEntity<GithubUserResponseV1> response = githubController.getUser("octocat");
        assertNotNull(response);
        assertThat(response.getBody().getUserName()).isEqualTo("octocat");
        assertThat(response.getBody().getDisplayName()).isEqualTo("The Octocat");
    }
}
