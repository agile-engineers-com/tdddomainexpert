package com.agileengineers.com.blog.tdddomainexpert.importer.support.build;


public class JiraIssueBuilder {

	private JiraBuilderStrategy strategy;

	public JiraIssueBuilder(JiraBuilderStrategy strategy) {
		this.strategy = strategy;
		strategy.createIssue();
	}

	public void withSummary(String summaryToSet) {
		strategy.setIssuesSummary(summaryToSet);
	}

	public void plannedFor(String versionToPlanFor) {
		strategy.setIssuesTargetVersion(versionToPlanFor);
	}
}