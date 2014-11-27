package com.agileengineers.com.blog.tdddomainexpert.importer.support.verify;

import java.util.Collection;
import java.util.Optional;

import com.agileengineers.com.blog.tdddomainexpert.importer.model.Issue;

public class SingleIssueVerifier {

	private Collection<Issue> issueList;

	public SingleIssueVerifier(Collection<Issue> issueList) {
		this.issueList = issueList;
	}

	public OptionalVerifier<Issue>
	withTitle(String title) {
		return new OptionalVerifier<Issue>(issueList.stream()
		.filter(issue -> title.equals(issue.getTitle())).findFirst(), String.format(
			"Issue with title '%s'", title));
	}

	public OptionalVerifier<Issue>
	withTargetVersion(String nameOfTargetVersion) {
		return new OptionalVerifier<Issue>(firstIssueWithTargetVersionName(nameOfTargetVersion),
		String.format("Issue with targetVersion '%s'", nameOfTargetVersion));
	}

	private Optional<Issue>
	firstIssueWithTargetVersionName(String nameOfTargetVersion) {
		return issueList.stream()
		.filter(issue -> isIssuesTargetVersionNameEqualsTo(issue, nameOfTargetVersion)).findFirst();
	}

	private boolean
	isIssuesTargetVersionNameEqualsTo(Issue issue, String nameOfTargetVersion) {
		return issue.getTargetVersion() != null
		&& nameOfTargetVersion.equals(issue.getTargetVersion().getName());
	}
}
