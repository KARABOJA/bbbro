/*
********************************************************************************
 * Copyright (c) 2014, 2018 Orange.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ********************************************************************************

Device : DeviceFoodProbe

A food probe is a device that is used to measure the internal temperature of food.

Created: 2018-06-29 17:19:55
*/

package org.eclipse.om2m.commons.resource.flexcontainerspec;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.om2m.commons.resource.AbstractFlexContainer;

@XmlRootElement(name = DeviceFoodProbeFlexContainer.SHORT_NAME, namespace = "http://www.onem2m.org/xml/protocols/homedomain")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = DeviceFoodProbeFlexContainer.SHORT_NAME, namespace = "http://www.onem2m.org/xml/protocols/homedomain")
public class DeviceFoodProbeFlexContainer extends AbstractFlexContainer {
	
	public static final String LONG_NAME = "deviceFoodProbe";
	public static final String SHORT_NAME = "deFPe";
	
	public DeviceFoodProbeFlexContainer () {
		setContainerDefinition("org.onem2m.home.device." + DeviceFoodProbeFlexContainer.LONG_NAME);
		setLongName(LONG_NAME);
		setShortName(SHORT_NAME);
	}
	
    public void finalizeSerialization() {
		getTemperature();
	}
	
	public void finalizeDeserialization() {
		if (this.temperature != null) {
			setTemperature(this.temperature);
		}
	}

	@XmlElement(name=TemperatureFlexContainer.SHORT_NAME, required=true, type=TemperatureFlexContainer.class, namespace="http://www.onem2m.org/xml/protocols/homedomain")
	private TemperatureFlexContainer temperature;
		
	public void setTemperature(TemperatureFlexContainer temperature) {
		this.temperature = temperature;
		getFlexContainerOrContainerOrSubscription().add(temperature);
	}
	
	public TemperatureFlexContainer getTemperature() {
		this.temperature = (TemperatureFlexContainer) getResourceByName(TemperatureFlexContainer.SHORT_NAME);
		return temperature;
	}
	
}
