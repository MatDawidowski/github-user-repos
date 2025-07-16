package com.atipera.controller;

import com.atipera.dto.RepositoryDto;
import com.atipera.service.GitHubService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/github")
public class GitHubController {
    private final GitHubService service;

    public GitHubController(GitHubService service) {
        this.service = service;
    }

    @GetMapping("/{username}/repositories")
    public List<RepositoryDto> getRepositories(@PathVariable String username) {
        return service.getNonForkRepositories(username);
    }
}
