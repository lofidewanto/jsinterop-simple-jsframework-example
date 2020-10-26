package com.github.lofi.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;

public class AppEntryPoint implements EntryPoint {

	private static Logger logger = Logger.getLogger(AppEntryPoint.class.getName());

	@Override
	public void onModuleLoad() {
		logger.info("Create component Dagger2");
		DaggerExampleComponent.create().getProductComposite();
	}

}
