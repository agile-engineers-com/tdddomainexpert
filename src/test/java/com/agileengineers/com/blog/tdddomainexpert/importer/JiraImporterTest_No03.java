package com.agileengineers.com.blog.tdddomainexpert.importer;

import org.junit.Test;

import com.agileengineers.com.blog.tdddomainexpert.importer.support.OfflineJiraSourceRepository;
import com.agileengineers.com.blog.tdddomainexpert.importer.support.build.JiraBuilder;
import com.agileengineers.com.blog.tdddomainexpert.importer.support.build.JiraBuilderJsonStrategy;
import com.agileengineers.com.blog.tdddomainexpert.importer.support.verify.ModelRepositoryVerifier;

public class JiraImporterTest_No03 {

	@Test public void
	should_import_all_3_issues_from_jira_and_store_them_in_repository() {

		given().issue();
		given().issue();
		given().issue();

		whenIssuesImportedAndStored();

		then().storedIssues().counts(3);
	}

	@Test public void
	should_import_title_of_issue() {

		given().issue().withSummary("Ich bin eine User-Story");
		whenIssuesImportedAndStored();

		then().storedIssue().withTitle("Ich bin eine User-Story").isPresent();
	}

	@Test public void
	should_associate_issue_with_assigned_targetVersion() {

		given().issue().plannedFor("1.0");
		whenIssuesImportedAndStored();

		then().storedIssue().withTargetVersion("1.0").isPresent();
	}

	// ///////////////////// TEST INTERNALS ////////////////////////

	private ModelRepositoryVerifier verifier = new ModelRepositoryVerifier();
	private JiraBuilderJsonStrategy jiraBuilderStrategy = new JiraBuilderJsonStrategy();

	private JiraBuilder
	given() {
		return new JiraBuilder(jiraBuilderStrategy);
	}

	private ModelRepositoryVerifier
	then() {
		return verifier;
	}

	private void
	whenIssuesImportedAndStored() {
		JiraSourceRepository jira = OfflineJiraSourceRepository.with(jiraBuilderStrategy.backlog());

		JiraImporter importer = new JiraImporter(jira, verifier.modelRepository());
		importer.importAndStore();
	}
}
