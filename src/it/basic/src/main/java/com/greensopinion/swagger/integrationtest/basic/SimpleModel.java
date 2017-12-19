package com.greensopinion.swagger.integrationtest.basic;

public class SimpleModel {
	private String id;
	private String name;

	public SimpleModel(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
