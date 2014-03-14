/*******************************************************************************
 * Copyright (c) 2014 Tasktop Technologies.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Tasktop EULA
 * which accompanies this distribution, and is available at
 * http://tasktop.com/legal
 *******************************************************************************/

package greensopinion.swagger.jaxrsgen.model;

public class ResponseMessage {

	private final int code;

	private final String message;

	private final String response;

	ResponseMessage(int code, String message, String response) {
		this.code = code;
		this.message = message;
		this.response = response;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public String getResponse() {
		return response;
	}

}
