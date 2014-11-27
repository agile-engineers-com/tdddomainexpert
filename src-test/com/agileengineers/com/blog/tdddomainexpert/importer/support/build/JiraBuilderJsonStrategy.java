package com.agileengineers.com.blog.tdddomainexpert.importer.support.build;

import org.json.JSONArray;
import org.json.JSONObject;

public class JiraBuilderJsonStrategy implements JiraBuilderStrategy {

	private JSONObject jsonBacklog = new JSONObject();
	private JSONArray jsonIssueArray;
	private JSONArray jsonVersionArray;
	private JSONObject currentIssue;

	public JiraBuilderJsonStrategy() {
		this.jsonIssueArray = provideArray(jsonBacklog, "issues");
		this.jsonVersionArray = provideArray(jsonBacklog, "versions");
	}

	@Override public void
	createIssue() {
		currentIssue = new JSONObject();
		jsonIssueArray.put(currentIssue);
	}

	@Override public void
	setIssuesSummary(String summaryToSet) {
		currentIssue.put("summary", summaryToSet);
	}

	@Override public void
	setIssuesTargetVersion(String versionToPlanFor) {
		JSONObject jsonVersion = new JSONObject();
		int idOfNewVersion = jsonVersionArray.length();
		jsonVersion.put("id", idOfNewVersion);
		jsonVersion.put("name", versionToPlanFor);

		jsonVersionArray.put(jsonVersion);

		currentIssue.put("targetVersion", idOfNewVersion);
	}

	private JSONArray
	provideArray(JSONObject inObject, String withKey) {
		if (inObject.has(withKey))
			return inObject.getJSONArray(withKey);

		return createArray(inObject, withKey);
	}

	private JSONArray
	createArray(JSONObject inObject, String withKey) {
		JSONArray jsonArray = new JSONArray();
		inObject.put(withKey, jsonArray);

		return jsonArray;
	}

	public JSONObject
	backlog() {
		return jsonBacklog;
	}
}
