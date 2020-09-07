package com.github.lofi.client;

import java.util.logging.Logger;

import elemental2.dom.DomGlobal;
import elemental2.dom.Event;
import elemental2.dom.Window;
import elemental2.indexeddb.IDBDatabase;
import elemental2.indexeddb.IDBFactory;
import elemental2.indexeddb.IDBObjectStore;
import elemental2.indexeddb.IDBObjectStoreParameters;
import elemental2.indexeddb.IDBOpenDBRequest;
import elemental2.indexeddb.IDBTransaction;
import elemental2.indexeddb.IDBVersionChangeEvent;
import elemental2.indexeddb.IndexedDbGlobal;
import jsinterop.base.Js;

public class IndexedDbElemental2 {

	private static Logger logger = Logger.getLogger(IndexedDbElemental2.class.getName());

	private static final String DBNAME = "mydbtest";

	private static final double DBVERSION = 1.0;

	private static final String STORENAME = "mystorename";

	private IDBOpenDBRequest openDBRequest;

	private IDBDatabase db;

	@SuppressWarnings("unchecked")
	public void openDb() {
		Window window = DomGlobal.window;

		if (Js.asPropertyMap(window).has("indexedDB")) {
			logger.info("IndexedDB found 1");
		}
		if (IndexedDbGlobal.indexedDB != null) {
			logger.info("IndexedDB found 2");
		}

		IDBFactory indexedDB = IndexedDbGlobal.indexedDB;
		openDBRequest = indexedDB.open(DBNAME, DBVERSION);

		openDBRequest.onerror = event -> {
			logger.info("Error opening DB: " + event.target.toString());
			return null;
		};

		openDBRequest.onsuccess = event -> {
			addProducts(event);
			return null;
		};

		openDBRequest.onupgradeneeded = event -> {
			doUpgrade(event);
			return null;
		};
	}

	private void doUpgrade(IDBVersionChangeEvent event) {
		logger.info("Upgrade DB: " + event.target.toString());

		db = (IDBDatabase) openDBRequest.result;

		IDBObjectStoreParameters params = IDBObjectStoreParameters.create();
		String[] paths = { "id" };
		params.setKeyPath(paths);

		IDBObjectStore store = db.createObjectStore(STORENAME);

		store.createIndex("products_id_unqiue", paths);
	}

	private void addProducts(Event event) {
		logger.info("Success opening DB: " + event.type);

		db = (IDBDatabase) openDBRequest.result;

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
