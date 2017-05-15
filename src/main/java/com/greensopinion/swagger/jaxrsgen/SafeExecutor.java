/*******************************************************************************
 * Copyright (c) 2017 Tasktop Technologies.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Tasktop EULA
 * which accompanies this distribution, and is available at
 * http://tasktop.com/legal
 *******************************************************************************/

package com.greensopinion.swagger.jaxrsgen;

import java.util.concurrent.Callable;

public class SafeExecutor {

	public interface RunnableWithCheckedException {

		public void run() throws Exception;
	}

	public static void run(RunnableWithCheckedException runnable) {
		try {
			runnable.run();
		} catch (Exception e) {
			propagateAsUncheckedException(e);
		}
	}

	public static <T> T call(Callable<T> callable) {
		try {
			return callable.call();
		} catch (Exception e) {
			throw propagateAsUncheckedException(e);
		}
	}

	private static RuntimeException propagateAsUncheckedException(Exception e) {
		if (e instanceof RuntimeException) {
			throw (RuntimeException) e;
		}
		throw new RuntimeException(e);
	}
}
