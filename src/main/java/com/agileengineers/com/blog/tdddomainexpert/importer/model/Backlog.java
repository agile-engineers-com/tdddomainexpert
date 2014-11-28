package com.agileengineers.com.blog.tdddomainexpert.importer.model;

import java.util.ArrayList;
import java.util.List;

public class Backlog {

	private final List<Issue> issueList = new ArrayList<>();

	public List<Issue>
	getIssueList() {
		return this.issueList;
	}

}
