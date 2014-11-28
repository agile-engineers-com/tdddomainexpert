package com.agileengineers.com.blog.tdddomainexpert.importer.support.build;

public interface JiraBuilderStrategy {

	void createIssue();

	void setIssuesSummary(String summaryToSet);

	void setIssuesTargetVersion(String versionToPlanFor);

}
