package com.agileengineers.com.blog.tdddomainexpert.importer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.agileengineers.com.blog.tdddomainexpert.importer.model.Issue;
import com.agileengineers.com.blog.tdddomainexpert.importer.model.ModelRepository;

public class JiraImporterTest_No02 {

	@Test public void
	should_import_all_3_issues_from_jira_and_store_them_in_repository() {

		addJiraIssue("ASE-001");
		addJiraIssue("ASE-002");
		addJiraIssue("ASE-003");

		importer.importAndStore();

		assertThat(getStoredIssues().size(), is(3));
	}

	@Test public void
	should_import_title_of_issue() {

		addJiraIssue("ASE-001", "Ich bin eine User-Story");
		importer.importAndStore();

		assertThat(getStoredIssues().get(0).getTitle(), is("Ich bin eine User-Story"));
	}

	private JiraImporter importer;
	private JSONObject jsonBacklog;
	private JSONArray jsonIssueArray;
	private ModelRepository repository;

	private JSONObject
	addJiraIssue(String withKey) {
		JSONObject jsonIssue = new JSONObject();
		jsonIssue.put("key", withKey);
		jsonIssueArray.put(jsonIssue);

		return jsonIssue;
	}

	private void
	addJiraIssue(String withKey, String withSummary) {
		JSONObject jsonIssue = addJiraIssue(withKey);
		jsonIssue.put("summary", withSummary);
	}

	private List<Issue>
	getStoredIssues() {
		ArgumentCaptor<Issue> issueCaptor = ArgumentCaptor.forClass(Issue.class);
		verify(repository, atLeastOnce()).storeIssue(issueCaptor.capture());

		return issueCaptor.getAllValues();
	}

	@Before public void
	setup() {
		jsonBacklog = new JSONObject();
		jsonIssueArray = new JSONArray();
		jsonBacklog.put("issues", jsonIssueArray);

		JiraSourceRepository jira = mock(JiraSourceRepository.class);
		when(jira.getBacklog()).thenReturn(jsonBacklog);

		repository = mock(ModelRepository.class);

		importer = new JiraImporter(jira, repository);
	}
}
