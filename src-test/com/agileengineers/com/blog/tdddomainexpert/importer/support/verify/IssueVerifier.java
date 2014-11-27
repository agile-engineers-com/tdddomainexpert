package com.agileengineers.com.blog.tdddomainexpert.importer.support.verify;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matcher;

import com.agileengineers.com.blog.tdddomainexpert.importer.model.Issue;

public class IssueVerifier {

	private Issue issueToVerify;

	public IssueVerifier(Issue issueToVerify) {
		this.issueToVerify = issueToVerify;
	}

	public void
	title(Matcher<String> matcher) {
		assertThat(issueToVerify.getTitle(), matcher);
	}

	public VersionVerifier
	targetVersion() {
		return new VersionVerifier(issueToVerify.getTargetVersion());
	}
}