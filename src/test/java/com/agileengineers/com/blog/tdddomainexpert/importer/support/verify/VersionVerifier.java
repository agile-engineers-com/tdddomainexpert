package com.agileengineers.com.blog.tdddomainexpert.importer.support.verify;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matcher;

import com.agileengineers.com.blog.tdddomainexpert.importer.model.Version;

public class VersionVerifier {

	private Version versionToVerify;

	public VersionVerifier(Version versionToVerify) {
		this.versionToVerify = versionToVerify;
	}

	public void
	name(Matcher<String> matcher) {
		assertThat(versionToVerify.getName(), matcher);
	}

}
