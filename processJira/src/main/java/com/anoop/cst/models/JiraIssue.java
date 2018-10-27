package com.anoop.cst.models;

public class JiraIssue {
    public static class IssueField {
        int storyPoints;

        IssueField(int storyPoints) {
            this.storyPoints = storyPoints;
        }

        public int getStoryPoints() {
            return storyPoints;
        }
    }

    String issueKey;
    IssueField fields;

    public JiraIssue(String issueKey, int storyPoints) {
        this.issueKey = issueKey;
        this.fields = new IssueField(storyPoints);
    }

    public String getIssueKey() {
        return issueKey;
    }

    public IssueField getFields() {
        return fields;
    }
}