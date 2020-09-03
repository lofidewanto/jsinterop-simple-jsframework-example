package com.github.lofi.client;

import java.util.logging.Logger;

import elemental2.dom.DomGlobal;
import elemental2.dom.Window;
import elemental2.indexeddb.IDBFactory;
import elemental2.indexeddb.IDBOpenDBRequest;
import elemental2.indexeddb.IDBVersionChangeEvent;
import elemental2.indexeddb.IndexedDbGlobal;
import jsinterop.base.Js;

public class IndexedDb {

	private static Logger logger = Logger.getLogger(IndexedDb.class.getName());

	private IDBOpenDBRequest openDBRequest;

	private Object db;

	public void createDb() {
		Window window = DomGlobal.window;

		if (Js.asPropertyMap(window).has("indexedDB")) {
			logger.info("IndexedDB found 1");
		}

		if (IndexedDbGlobal.indexedDB != null) {
			logger.info("IndexedDB found 2");
		}

		IDBFactory indexedDB = IndexedDbGlobal.indexedDB;
		openDBRequest = indexedDB.open("mydbtest", 1.0);

		//
		// openDBRequest.onupgradeneeded = new OnupgradeneededFn() {
		// @Override
		// public Object onInvoke(IDBVersionChangeEvent p0) {
		// doUpgrade();
		// return Js.undefined(); // that value will likely be ignored anyway
		// }
		// };

		openDBRequest.onerror = event -> {
			logger.info("Error opening DB: " + event.type);

			return Js.undefined();
		};

		openDBRequest.onsuccess = event -> {
			logger.info("OK opening DB: " + event.type);

			this.db = this.openDBRequest.result;

			// Object options;
			// this.openDBRequest.createObjectStore("storeName", options);

			return Js.undefined();
		};

		openDBRequest.onupgradeneeded = event -> {
			doUpgrade(event);
			return Js.undefined();
		};

	}

	private void doUpgrade(IDBVersionChangeEvent event) {
		logger.info("Upgrade... " + event.target.toString());
	}

}
