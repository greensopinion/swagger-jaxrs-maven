/*******************************************************************************
 * Copyright (c) 2014 Tasktop Technologies.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/

package greensopinion.swagger.jaxrsgen.model;

import static com.google.common.base.Preconditions.checkState;

import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

// https://github.com/wordnik/swagger-core/wiki/Api-Declaration
public class ServiceApi implements Comparable<ServiceApi> {

	private final String path;

	private final String description;

	private final List<ServiceOperation> operations;

	ServiceApi(String path, String description, List<ServiceOperation> operations) {
		this.path = path;
		this.description = description;
		this.operations = operations;
	}

	public String getPath() {
		return path;
	}

	public List<ServiceOperation> getOperations() {
		return operations;
	}

	public String getDescription() {
		return description;
	}

	public Set<Class<?>> getModelClasses() {
		Set<Class<?>> modelClasses = Sets.newHashSet();
		for (ServiceOperation operation : getOperations()) {
			modelClasses.addAll(operation.getModelClasses());
		}
		return ImmutableSet.copyOf(modelClasses);
	}

	@Override
	public int compareTo(ServiceApi o) {
		int i = path.compareTo(o.path);
		checkState(i != 0, "Two service APIs have the same path");
		return i;
	}
}
