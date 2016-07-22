/*******************************************************************************
 * Copyright (c) 2014, 2016 Orange.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.onem2m.sdt.datapoints;

import org.onem2m.sdt.types.DataType;

public abstract class DateTimeDataPoint extends AbstractDateDataPoint {

	public DateTimeDataPoint(String name) {
		super(name, DataType.Datetime);
	}

}
