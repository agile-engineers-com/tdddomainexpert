package com.agileengineers.com.blog.tdddomainexpert.importer.support.verify;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

public class ListVerifier<T> {

	private List<T> listToVerify;

	public ListVerifier(List<T> listToVerify) {
		this.listToVerify = listToVerify;
	}

	public void counts(int expectedCount) {
		assertThat(listToVerify.size(), is(expectedCount));
	}

}