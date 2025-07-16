package com.atipera;

import com.atipera.dto.RepositoryDto;
import com.atipera.dto.BranchDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GitHubIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldReturnNonForkRepositoriesWithBranches() {
        String url = "http://localhost:" + port + "/api/github/MatDawidowski/repositories";

        ResponseEntity<RepositoryDto[]> response = restTemplate.getForEntity(url, RepositoryDto[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        RepositoryDto[] repositories = response.getBody();
        assertThat(repositories).isNotNull();
        assertThat(repositories.length).isGreaterThan(0);

        for (RepositoryDto repo : repositories) {
            assertThat(repo.name()).isNotBlank();
            assertThat(repo.ownerLogin()).isEqualTo("MatDawidowski");
            assertThat(repo.branches()).isNotEmpty();

            repo.branches().forEach(branch -> {
                assertThat(branch.name()).isNotBlank();
                assertThat(branch.lastCommitSha()).isNotBlank();
            });
        }
    }
}
