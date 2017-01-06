/*******************************************************************************
 * Copyright (c) 2014, 2016 Orange.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.onem2m.sdt.home.mocked.devices;

import java.util.List;

import org.onem2m.home.devices.SmokeDetector;
import org.onem2m.home.modules.SmokeSensor;
import org.onem2m.sdt.Domain;
import org.onem2m.sdt.Event;
import org.onem2m.sdt.datapoints.BooleanDataPoint;
import org.onem2m.sdt.datapoints.IntegerDataPoint;
import org.onem2m.sdt.home.mocked.module.MockedFaultDetection;
import org.onem2m.sdt.impl.DataPointException;
import org.osgi.framework.ServiceRegistration;

public class MockedSmokeDetector extends SmokeDetector implements MockedDevice {

	private List<ServiceRegistration> serviceRegistrations;
	private boolean smokeAlarm = false;
	private boolean running = false;
	private SmokeSensor smokeSensor;
	private int detectedTime;

	public MockedSmokeDetector(String id, String serial, Domain domain) {
		super(id, serial, domain);
		
		smokeSensor = new SmokeSensor("smokeSensor_" + id, domain, 
			new BooleanDataPoint("alarm") {
				@Override
				public Boolean doGetValue() throws DataPointException {
					return smokeAlarm;
				}
			});
		
		smokeSensor.setDetectedTime(new IntegerDataPoint("detectedTime") {
			@Override
			protected Integer doGetValue() throws DataPointException {
				return detectedTime;
			}
			@Override
			protected void doSetValue(Integer v) throws DataPointException {
				detectedTime = v;
			}
		});
		addModule(smokeSensor);
		
		addModule(new MockedFaultDetection("faultDetection_" + id, domain));
	}

	public void registerDevice() {
		running = true;
		if (! ((serviceRegistrations == null) || serviceRegistrations.isEmpty())) {
			// already registered
			return;
		}
		serviceRegistrations = Activator.register(this);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (running) {
					try {
						Thread.sleep((int) (180000 + 5000 * Math.random()));
						Activator.logger.info("Generating Smoke Alarm event");
						Event evt = new Event("ALARM");
						smokeAlarm = ! smokeAlarm;
						evt.addDataPoint(smokeSensor.getDataPoint("alarm"));
						evt.setValue(smokeAlarm);
						smokeSensor.addEvent(evt);
						if (smokeAlarm)
							detectedTime = (int)(System.currentTimeMillis() / 1000);
					} catch (Throwable e) {
						Activator.logger.warning("Error generating event", e);
					}
				}
			}
		}).start();
	}

	public void unregisterDevice() {
		running = false;
		if (serviceRegistrations == null)
			return;
		for (ServiceRegistration reg : serviceRegistrations) {
			reg.unregister();
		}
		serviceRegistrations.clear();
	}

}
