/*******************************************************************************
 * Copyright (c) 2014, 2015 Tasktop Technologies.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Tasktop EULA
 * which accompanies this distribution, and is available at
 * http://tasktop.com/legal
 *******************************************************************************/

package greensopinion.swagger.jaxrsgen.model;

import java.util.Map;

import com.google.gson.annotations.SerializedName;

public class Parameter {

	private final String name;

	private final String defaultValue;

	private final boolean required;

	private final boolean allowMultiple;

	private final String type;

	@SerializedName("items")
	private final Map<String, String> arrayItems;

	private final String format;

	private final String paramType;

	private final String description;

	private final String allowableValues;

	private transient final Class<?> typeClass;

	Parameter(String name, String defaultValue, boolean required, boolean allowMultiple, String type,
			Class<?> typeClass, String format, String paramType, Map<String, String> arrayItems, String allowableValues,
			String description) {
		this.name = name;
		this.defaultValue = defaultValue;
		this.required = required;
		this.allowMultiple = allowMultiple;
		this.type = type;
		this.typeClass = typeClass;
		this.format = format;
		this.paramType = paramType;
		this.arrayItems = arrayItems;
		this.allowableValues = allowableValues;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public boolean isRequired() {
		return required;
	}

	public boolean isAllowMultiple() {
		return allowMultiple;
	}

	public String getType() {
		return type;
	}

	public String getFormat() {
		return format;
	}

	public String getParamType() {
		return paramType;
	}

	public String getDescription() {
		return description;
	}

	public String getAllowableValues() {
		return allowableValues;
	}

	public Class<?> getTypeClass() {
		return typeClass;
	}

}
