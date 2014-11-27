package com.agileengineers.com.blog.tdddomainexpert.importer.model;


public abstract class Issue {

	private long key;

	private String title;

	private String description;

	private Version targetVersion;

	public long
	getKey() {
		return this.key;
	}

	public void
	setKey(long keyToSet) {
		this.key = keyToSet;
	}

	public String
	getTitle() {
		return this.title;
	}

	public void
	setTitle(String titleToSet) {
		this.title = titleToSet;
	}

	public String
	getDescription() {
		return this.description;
	}

	public void
	setDescription(String descriptionToSet) {
		this.description = descriptionToSet;
	}

	public Version
	getTargetVersion() {
		return this.targetVersion;
	}

	public void
	setTargetVersion(Version targetVersionToSet) {
		this.targetVersion = targetVersionToSet;
	}

}
