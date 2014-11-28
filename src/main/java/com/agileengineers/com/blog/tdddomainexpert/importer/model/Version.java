package com.agileengineers.com.blog.tdddomainexpert.importer.model;

import java.util.Date;

public class Version {

	private String name;

	private Date releaseDate;

	public String
	getName() {
		return this.name;
	}

	public void
	setName(String nameToSet) {
		this.name = nameToSet;
	}

	public Date
	getReleaseDate() {
		return this.releaseDate;
	}

	public void
	setReleaseDate(Date releaseDateToSet) {
		this.releaseDate = releaseDateToSet;
	}

}
