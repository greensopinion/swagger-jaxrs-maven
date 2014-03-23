/*******************************************************************************
 * Copyright (c) 2014 Tasktop Technologies.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Tasktop EULA
 * which accompanies this distribution, and is available at
 * http://tasktop.com/legal
 *******************************************************************************/

package greensopinion.swagger.jaxrsgen.mock.noswagger.model;

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
