/*******************************************************************************
 * Copyright (c) 2007, 2008 David Green and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     David Green - initial API and implementation
 *******************************************************************************/

package greensopinion.swagger.jaxrsgen.mock.model;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import com.google.common.collect.Iterables;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "A representation of an error, providing an error code and message.")
public class ServerError {

	@ApiModelProperty(value = "The error code, which can be used to identify the type of error.", required = true)
	private final String code;

	@ApiModelProperty("The message describing the error.")
	private final String message;

	@ApiModelProperty(hidden = true)
	private final String detail;

	public ServerError(Throwable throwable) {
		checkNotNull(throwable, "Must provide throwable");
		code = throwable.getClass().getSimpleName();
		message = computeMessage(throwable);
		detail = Throwables.getStackTraceAsString(throwable);
	}

	private String computeMessage(Throwable t) {
		Optional<Throwable> found = Iterables.tryFind(Throwables.getCausalChain(t), new Predicate<Throwable>() {

			@Override
			public boolean apply(Throwable input) {
				return input != null && !Strings.isNullOrEmpty(input.getMessage());
			}
		});
		if (found.isPresent()) {
			return found.get().getMessage();
		}
		return null;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public String getDetail() {
		return detail;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(ServerError.class).add("code", code).add("message", message).toString();
	}
}
