/*******************************************************************************
 * Copyright (c) 2014, 2016 Orange.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.onem2m.home.modules;

import org.onem2m.home.types.ModuleType;
import org.onem2m.sdt.Domain;
import org.onem2m.sdt.Module;
import org.onem2m.sdt.datapoints.BooleanDataPoint;
import org.onem2m.sdt.impl.AccessException;
import org.onem2m.sdt.impl.DataPointException;

public class HotWaterSupply extends Module {
	
	private BooleanDataPoint status;
	private BooleanDataPoint bath;

	public HotWaterSupply(final String name, final Domain domain, BooleanDataPoint status) {
		super(name, domain, ModuleType.hotWaterSupply.getDefinition());
		
		this.status = status;
		this.status.setWritable(false);
		this.status.setDoc("The status of watering operation");
		addDataPoint(this.status);
	}

	public boolean getStatus() throws DataPointException, AccessException {
		return status.getValue();
	}

	public void setBath(BooleanDataPoint dp) {
		bath = dp;
		bath.setDoc("The status of filling bath tub");
		bath.setOptional(true);
		addDataPoint(bath);
	}

	public boolean getBath() throws DataPointException, AccessException {
		if (bath == null)
			throw new DataPointException("Not implemented");
		return bath.getValue();
	}

	public void setBath(boolean v) throws DataPointException, AccessException {
		if (bath == null)
			throw new DataPointException("Not implemented");
		bath.setValue(v);
	}

}
