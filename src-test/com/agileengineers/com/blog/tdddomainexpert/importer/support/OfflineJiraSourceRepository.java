package com.agileengineers.com.blog.tdddomainexpert.importer.support;

import org.json.JSONObject;

import com.agileengineers.com.blog.tdddomainexpert.importer.JiraSourceRepository;

public class OfflineJiraSourceRepository implements JiraSourceRepository {

	private JSONObject jsonBacklog;

	public static OfflineJiraSourceRepository
	with(JSONObject jsonBacklog) {
		return new OfflineJiraSourceRepository(jsonBacklog);
	}

	private OfflineJiraSourceRepository(JSONObject jsonBacklog) {
		this.jsonBacklog = jsonBacklog;
	}

	@Override public JSONObject
	getBacklog() {
		return this.jsonBacklog;
	}

}
