package com.orgtool.jiraissueapi.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.orgtool.jiraissueapi.models.JiraIssue;
import com.orgtool.jiraissueapi.services.IssueService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class JiraIssueController {

    private final IssueService issueService;

    public JiraIssueController(IssueService issueService){
    this.issueService = issueService;
}

    @GetMapping("/issues/{issueKey}")
    public JiraIssue getJiraIssue(@PathVariable String issueKey) throws JsonProcessingException {
       return issueService.getJiraIssue(issueKey);
    }

}