package com.agileengineers.com.blog.tdddomainexpert.importer;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.agileengineers.com.blog.tdddomainexpert.importer.model.Issue;
import com.agileengineers.com.blog.tdddomainexpert.importer.model.ModelRepository;
import com.agileengineers.com.blog.tdddomainexpert.importer.model.Story;
import com.agileengineers.com.blog.tdddomainexpert.importer.model.Version;

public class JiraImporter {

	private ModelRepository repository;
	private JiraSourceRepository jira;
	private Map<Integer, Version> idToVersionMap = new HashMap<>();
	private JSONObject backlog;

	public JiraImporter(JiraSourceRepository jira, ModelRepository repository) {
		this.jira = jira;
		this.repository = repository;
	}

	public void
	importAndStore() {

		backlog = jira.getBacklog();
		mapJsonVersions();

		JSONArray jsonIssueArray = backlog.getJSONArray("issues");

		new JsonArrayIterator(jsonIssueArray).stream().map(this::jsonToIssue)
		.forEach(this::storeIssue);
	}

	public void
	mapJsonVersions() {
		if (versionsNotDefinied())
			return;

		JSONArray jsonVersionArray = backlog.getJSONArray("versions");
		new JsonArrayIterator(jsonVersionArray).forEach(this::mapToVersion);
	}

	private boolean
	versionsNotDefinied() {
		return !backlog.has("versions");
	}

	private Issue
	jsonToIssue(JSONObject jsonIssue) {
		Issue issue = new Story();
		issue.setTitle(jsonIssue.optString("summary"));
		associateIssuesTargetVersion(jsonIssue, issue);

		return issue;
	}

	public void
	associateIssuesTargetVersion(JSONObject jsonIssue, Issue issue) {
		int idOfTargetVersion = jsonIssue.optInt("targetVersion", -1);
		if (idOfTargetVersion == -1)
			return;

		Version version = idToVersionMap.get(idOfTargetVersion);
		issue.setTargetVersion(version);
	}

	private void
	storeIssue(Issue issue) {
		repository.storeIssue(issue);
	}

	private void
	mapToVersion(JSONObject jsonVersion) {
		Version version = new Version();
		version.setName(jsonVersion.getString("name"));
		idToVersionMap.put(jsonVersion.getInt("id"), version);
	}
}
