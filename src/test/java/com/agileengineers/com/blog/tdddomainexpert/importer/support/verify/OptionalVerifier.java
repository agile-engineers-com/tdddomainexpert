package com.agileengineers.com.blog.tdddomainexpert.importer.support.verify;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Optional;

public class OptionalVerifier<T> {

	private final Optional<T> optionalToVerify;
	private final String description;

	public OptionalVerifier(Optional<T> optionalToVerify, String description) {
		this.optionalToVerify = optionalToVerify;
		this.description = description;
	}

	public void
	isPresent() {
		assertThat(description.concat(" is not present"), optionalToVerify.isPresent(),
			equalTo(true));
	}
}
