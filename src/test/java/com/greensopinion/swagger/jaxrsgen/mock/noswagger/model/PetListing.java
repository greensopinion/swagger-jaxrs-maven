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

package com.greensopinion.swagger.jaxrsgen.mock.noswagger.model;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import com.google.common.collect.ImmutableList;

public class PetListing {

	private final int start;

	private final int count;

	private final int total;

	private final List<PetHandle> pets;

	public PetListing(int start, int count, int total, List<PetHandle> pets) {
		this.start = start;
		this.count = count;
		this.total = total;
		this.pets = ImmutableList.copyOf(checkNotNull(pets));
	}

	public int getStart() {
		return start;
	}

	public int getCount() {
		return count;
	}

	public int getTotal() {
		return total;
	}

	public List<PetHandle> getPets() {
		return pets;
	}
}
