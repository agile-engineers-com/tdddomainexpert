package com.agileengineers.com.blog.tdddomainexpert.importer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.agileengineers.com.blog.tdddomainexpert.importer.model.Issue;
import com.agileengineers.com.blog.tdddomainexpert.importer.model.ModelRepository;

public class JiraImporterTest_No01 {

	@Test public void
	//
	should_import_all_3_issues_from_jira_and_store_them_in_repository() {
		JSONObject jsonBacklog = new JSONObject();
		JSONArray jsonIssueArray = new JSONArray();
		jsonBacklog.put("issues", jsonIssueArray);

		for (int i = 0; i < 3; i++) {
			JSONObject jsonIssue = new JSONObject();
			jsonIssue.put("key", "ASE-00" + (i + 1));
			jsonIssueArray.put(jsonIssue);
		}

		JiraSourceRepository jira = mock(JiraSourceRepository.class);
		when(jira.getBacklog()).thenReturn(jsonBacklog);

		ModelRepository repository = mock(ModelRepository.class);

		JiraImporter importer = new JiraImporter(jira, repository);
		importer.importAndStore();

		ArgumentCaptor<Issue> issueCaptor = ArgumentCaptor.forClass(Issue.class);
		verify(repository, atLeast(0)).storeIssue(issueCaptor.capture());
		List<Issue> storedIssueList = issueCaptor.getAllValues();

		assertThat(storedIssueList.size(), is(3));
	}

}
