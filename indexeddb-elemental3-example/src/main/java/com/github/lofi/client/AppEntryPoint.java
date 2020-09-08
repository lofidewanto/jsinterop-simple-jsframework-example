package com.github.lofi.client;

import com.google.gwt.core.client.EntryPoint;

public class AppEntryPoint implements EntryPoint {

	@Override
	public void onModuleLoad() {
		new IndexedDbElemental3().openDb();
	}

}
