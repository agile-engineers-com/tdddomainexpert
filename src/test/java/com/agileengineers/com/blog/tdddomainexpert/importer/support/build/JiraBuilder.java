package com.agileengineers.com.blog.tdddomainexpert.importer.support.build;


public class JiraBuilder {

	private JiraBuilderStrategy strategy;

	public JiraBuilder(JiraBuilderStrategy strategy) {
		this.strategy = strategy;
	}

	public JiraIssueBuilder issue() {
		JiraIssueBuilder issueBuilder = new JiraIssueBuilder(strategy);
		return issueBuilder;
	}

}