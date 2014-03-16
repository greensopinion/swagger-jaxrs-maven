/*******************************************************************************
 * Copyright (c) 2014 Tasktop Technologies.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Tasktop EULA
 * which accompanies this distribution, and is available at
 * http://tasktop.com/legal
 *******************************************************************************/

package greensopinion.swagger.jaxrsgen.model;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.gson.annotations.SerializedName;

public class ServiceOperation implements Comparable<ServiceOperation> {

	@SerializedName("method")
	private final String httpMethod;

	private final String summary;

	private transient final String path;

	private final String notes;

	private final String type;

	private final String nickname;

	private final List<String> produces;

	private final List<Parameter> parameters;

	private final List<ResponseMessage> responseMessages;

	private transient final Class<?> returnType;

	ServiceOperation(String path, String httpMethod, String nickname, String summary, List<String> produces,
			String notes, String type, Class<?> returnType, List<Parameter> parameters,
			List<ResponseMessage> responseMessages) {
		this.path = checkNotNull(path, "Must provide a path");
		this.httpMethod = checkNotNull(httpMethod, "Must provide an httpMethod");
		this.nickname = checkNotNull(nickname, "Must provide nickname");
		this.type = type;
		this.returnType = returnType;
		this.summary = summary;
		this.produces = produces == null ? null : ImmutableList.copyOf(produces);
		this.notes = notes;
		this.parameters = parameters == null ? null : ImmutableList.copyOf(parameters);
		this.responseMessages = responseMessages == null ? null : ImmutableList.copyOf(responseMessages);
	}

	public String getPath() {
		return path;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public String getNickname() {
		return nickname;
	}

	public String getSummary() {
		return summary;
	}

	public String getNotes() {
		return notes;
	}

	public String getType() {
		return type;
	}

	public List<String> getProduces() {
		return produces == null ? Collections.<String> emptyList() : produces;
	}

	public List<Parameter> getParameters() {
		return parameters == null ? Collections.<Parameter> emptyList() : parameters;
	}

	public List<ResponseMessage> getResponseMessages() {
		return responseMessages == null ? Collections.<ResponseMessage> emptyList() : responseMessages;
	}

	public Set<Class<?>> getModelClasses() {
		Set<Class<?>> modelClasses = Sets.newHashSet();
		if (returnType != null && ApiTypes.isModelClass(returnType)) {
			modelClasses.add(returnType);
		}
		for (Parameter parameter : getParameters()) {
			if (parameter.getTypeClass() != null && ApiTypes.isModelClass(parameter.getTypeClass())) {
				modelClasses.add(parameter.getTypeClass());
			}
		}
		for (ResponseMessage responseMessage : getResponseMessages()) {
			if (responseMessage.getTypeClass() != null && ApiTypes.isModelClass(responseMessage.getTypeClass())) {
				modelClasses.add(responseMessage.getTypeClass());
			}
		}
		return ImmutableSet.copyOf(modelClasses);
	}

	public static ServiceOperationBuilder builder() {
		return new ServiceOperationBuilder();
	}

	@Override
	public int compareTo(ServiceOperation o) {
		return compareString().compareTo(o.compareString());
	}

	private String compareString() {
		return Joiner.on(";;").join(httpMethod, path, nickname, summary);
	}

}
