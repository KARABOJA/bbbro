/*******************************************************************************
 * Copyright (c) 2014, 2016 Orange.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.om2m.sdt.home.devices;

import org.eclipse.om2m.sdt.Domain;
import org.eclipse.om2m.sdt.Module;
import org.eclipse.om2m.sdt.home.modules.PushButton;
import org.eclipse.om2m.sdt.home.types.DeviceType;

public class Switch extends GenericDevice {
	
	private PushButton button;
	
	public Switch(final String id, final String serial, final Domain domain) {
		super(id, serial, DeviceType.deviceSwitch, domain);
	}
	
	public void addModule(Module module) {
		if (module instanceof PushButton)
			addModule((PushButton)module);
		else 
			super.addModule(module);
	}

	public void addModule(PushButton button) {
		this.button = button;
		super.addModule(button);
	}

	public PushButton getPushButton() {
		return button;
	}

}
