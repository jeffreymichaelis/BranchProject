package com.jmichaelis.branchproject.rest;

import com.jmichaelis.branchproject.model.GithubUserResponseV1;
import com.jmichaelis.branchproject.service.GithubService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/github/v1")
public class GithubController {

    private final GithubService githubService;

    public GithubController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/user")
    public ResponseEntity<GithubUserResponseV1> getUser(@RequestParam String username) {
        try {
            return new ResponseEntity<>(GithubUserResponseV1.fromGithubUser(
                    githubService.getGitHubUserByUsername(username)), HttpStatus.OK
            );
        }
        catch (Exception e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.NOT_FOUND);
        }
    }

}
