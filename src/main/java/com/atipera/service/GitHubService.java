package com.atipera.service;

import com.atipera.dto.*;
import com.atipera.exception.GitHubUserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

import java.util.*;

@Service
public class GitHubService {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String GITHUB_API = "https://api.github.com";

    public List<RepositoryDto> getNonForkRepositories(String username) {
        String repoUrl = GITHUB_API + "/users/" + username + "/repos";

        try {
            var repos = restTemplate.getForObject(repoUrl, List.class);
            List<Map<String, Object>> repoList = (List<Map<String, Object>>) repos;

            List<RepositoryDto> result = new ArrayList<>();

            for (Map<String, Object> repo : repoList) {
                if (!(Boolean) repo.get("fork")) {
                    String name = (String) repo.get("name");
                    Map<String, Object> owner = (Map<String, Object>) repo.get("owner");
                    String ownerLogin = (String) owner.get("login");

                    // branches
                    String branchesUrl = GITHUB_API + "/repos/" + ownerLogin + "/" + name + "/branches";
                    List<Map<String, Object>> branches = restTemplate.getForObject(branchesUrl, List.class);

                    List<BranchDto> branchDtos = new ArrayList<>();
                    for (Map<String, Object> branch : branches) {
                        String branchName = (String) branch.get("name");
                        Map<String, Object> commit = (Map<String, Object>) branch.get("commit");
                        String sha = (String) commit.get("sha");

                        branchDtos.add(new BranchDto(branchName, sha));
                    }

                    result.add(new RepositoryDto(name, ownerLogin, branchDtos));
                }
            }

            return result;
        } catch (HttpClientErrorException.NotFound e) {
            throw new GitHubUserNotFoundException("GitHub user not found");
        }
    }
}
