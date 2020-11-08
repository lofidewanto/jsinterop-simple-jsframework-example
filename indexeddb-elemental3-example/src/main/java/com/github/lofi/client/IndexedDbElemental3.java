package com.github.lofi.client;

import java.util.logging.Logger;

import elemental3.Event;
import elemental3.Global;
import elemental3.IDBDatabase;
import elemental3.IDBFactory;
import elemental3.IDBObjectStore;
import elemental3.IDBObjectStoreParameters;
import elemental3.IDBOpenDBRequest;
import elemental3.IDBTransaction;
import elemental3.Window;
import jsinterop.base.Js;

public class IndexedDbElemental3 {

	private static Logger logger = Logger.getLogger(IndexedDbElemental3.class.getName());

	private static final String DBNAME = "mydbtest";

	private static final int DBVERSION = 1;

	private static final String STORENAME = "mystorename";

	private IDBOpenDBRequest openDBRequest;

	private IDBDatabase db;

	public void openDb() {
		Window window = Global.globalThis();
		IDBFactory indexedDB = Global.globalThis().indexedDB();

		if (Js.asPropertyMap(window).has("indexedDB")) {
			logger.info("IndexedDB found 1");
		}

		openDBRequest = indexedDB.open(DBNAME, DBVERSION);

		openDBRequest.onerror = event -> {
			logger.info("Error opening DB: " + event.target().toString());
		};

		openDBRequest.onsuccess = event -> {
			addProducts(event);
		};

		openDBRequest.onupgradeneeded = event -> {
			doUpgrade(event);
		};
	}

	private void doUpgrade(Event event) {
		logger.info("Upgrade DB: " + event.target().toString());

		db = (IDBDatabase) openDBRequest.result();

		IDBObjectStoreParameters params = IDBObjectStoreParameters.create();
		String[] paths = { "id" };
		params.setKeyPath(paths);

		IDBObjectStore store = db.createObjectStore(STORENAME);

		store.createIndex("products_id_unqiue", paths);
	}

	private void addProducts(Event event) {
		logger.info("Success opening DB: " + event.type());

		db = (IDBDatabase) openDBRequest.result();

		IDBTransaction transaction = db.transaction(STORENAME, "readwrite");
		IDBObjectStore store = transaction.objectStore(STORENAME);

		int random = (int) (Math.random() * 50 + 1);
		String key = Integer.valueOf(random).toString();

		logger.info("Random: " + key);

		Product product = new Product();
		product.setId(key);
		product.setName("Lofi " + key);

		store.add(product, key);
	}

}
