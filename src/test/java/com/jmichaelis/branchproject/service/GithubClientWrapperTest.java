package com.jmichaelis.branchproject.service;

import com.jmichaelis.branchproject.model.GithubRepo;
import com.jmichaelis.branchproject.model.GithubUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GithubClientWrapperTest {

    @Mock
    private GitHub gitHub;

    @InjectMocks
    private GithubClientWrapperImpl githubClientWrapper;

    String loginId = "username";
    Date createdAt = Date.from(java.time.Instant.EPOCH);

    @Test
    void getUser_mapsUserAndRepos() throws Exception {
        GHUser ghUser = getDefaultGHUser();
        doReturn(ghUser).when(gitHub).getUser(loginId);

        GithubUser result = githubClientWrapper.getUser(loginId);

        assertThat(result).isNotNull();
        assertThat(result.getLogin()).isEqualTo(loginId);
        assertThat(result.getAvatarUrl()).isEqualTo("https://example.com/avatar.png");
        assertThat(result.getUrl()).isEqualTo("https://github.com/name");
        assertThat(result.getName()).isEqualTo("name");
        assertThat(result.getLocation()).isEqualTo("location");
        assertThat(result.getEmail()).isEqualTo("user@example.com");
        assertThat(result.getCreatedAt()).isEqualTo(createdAt);

        List<GithubRepo> mappedRepos = result.getRepos();
        assertThat(mappedRepos).hasSize(2);

        assertThat(mappedRepos.get(0).getName()).isEqualTo("repo1");
        assertThat(mappedRepos.get(0).getUrl()).isEqualTo("https://github.com/name/repo1");

        assertThat(mappedRepos.get(1).getName()).isEqualTo("repo2");
        assertThat(mappedRepos.get(1).getUrl()).isEqualTo("https://github.com/name/repo2");
    }

    @Test
    void getUser_Exception() throws Exception {
        String loginId = "invalidUser";
        when(gitHub.getUser(loginId)).thenThrow(new IOException("io exception"));

        assertThatThrownBy(() -> githubClientWrapper.getUser(loginId))
                .isInstanceOf(RuntimeException.class);
    }

    private GHUser getDefaultGHUser() throws IOException {
        GHUser ghUser = mock(GHUser.class);

        when(ghUser.getLogin()).thenReturn(loginId);

        when(ghUser.getAvatarUrl()).thenReturn("https://example.com/avatar.png");
        when(ghUser.getHtmlUrl()).thenReturn(new URL("https://github.com/name"));
        when(ghUser.getName()).thenReturn("name");
        when(ghUser.getLocation()).thenReturn("location");
        when(ghUser.getEmail()).thenReturn("user@example.com");
        when(ghUser.getCreatedAt()).thenReturn(createdAt);

        GHRepository repo1 = mock(GHRepository.class);
        when(repo1.getHtmlUrl()).thenReturn(new URL("https://github.com/name/repo1"));

        GHRepository repo2 = mock(GHRepository.class);
        when(repo2.getHtmlUrl()).thenReturn(new URL("https://github.com/name/repo2"));

        Map<String, GHRepository> repos = new LinkedHashMap<>();
        repos.put("repo1", repo1);
        repos.put("repo2", repo2);
        when(ghUser.getRepositories()).thenReturn(repos);

        return ghUser;
    }
}