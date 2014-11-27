package com.agileengineers.com.blog.tdddomainexpert.importer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonArrayIterator implements Iterable<JSONObject>, Iterator<JSONObject> {

	private JSONArray arrayToIterate;
	private int currentIterationPosition = 0;

	public JsonArrayIterator(JSONArray arrayToIterate) {
		this.arrayToIterate = arrayToIterate;
	}

	@Override public Iterator<JSONObject>
	iterator() {
		return this;
	}

	@Override public boolean
	hasNext() {
		return currentIterationPosition < arrayToIterate.length();
	}

	@Override public JSONObject
	next() {
		return arrayToIterate.getJSONObject(currentIterationPosition++);
	}

	public Stream<JSONObject>
	stream() {
		ArrayList<JSONObject> list = new ArrayList<JSONObject>();
		for (JSONObject element : this) {
			list.add(element);
		}
		return list.stream();
	}

}