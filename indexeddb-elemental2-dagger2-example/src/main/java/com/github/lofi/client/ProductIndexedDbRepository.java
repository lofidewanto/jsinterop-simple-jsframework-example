package com.github.lofi.client;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

import elemental2.dom.DomGlobal;
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

@Singleton
public class ProductIndexedDbRepository {

	private static Logger logger = Logger.getLogger(ProductIndexedDbRepository.class.getName());

	private static final String DBNAME = "mydbtest";

	private static final double DBVERSION = 1.0;

	private static final String STORENAME = "mystorename";

	private IDBOpenDBRequest openDBRequest;

	private IDBDatabase db;

	@Inject
	public ProductIndexedDbRepository() {
		initDb();
	}

	@SuppressWarnings("unchecked")
	private void initDb() {
		logger.info("Init DB... ");

		openDb();

		openDBRequest.onerror = event -> {
			logger.info("Error opening DB: " + event.target.toString());
			return null;
		};

		openDBRequest.onsuccess = event -> {
			logger.info("Success opening DB: " + event.type);
			return null;
		};

		openDBRequest.onupgradeneeded = event -> {
			doUpgrade(event);
			return null;
		};
	}

	public void openDb() {
		logger.info("Open DB... ");

		Window window = DomGlobal.window;
		IDBFactory indexedDB = IndexedDbGlobal.indexedDB;

		if (Js.asPropertyMap(window).has("indexedDB")) {
			logger.info("IndexedDB found 1");
		}
		if (indexedDB != null) {
			logger.info("IndexedDB found 2");
		}

		openDBRequest = indexedDB.open(DBNAME, DBVERSION);
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

	@SuppressWarnings("unchecked")
	public void save(Product product) {
		logger.info("Save data in DB...");

		openDb();

		openDBRequest.onerror = event -> {
			logger.info("Error opening DB: " + event.target.toString());
			return null;
		};

		openDBRequest.onsuccess = event -> {
			logger.info("Success opening DB: " + event.type);

			db = (IDBDatabase) openDBRequest.result;

			IDBTransaction transaction = db.transaction(STORENAME, "readwrite");
			IDBObjectStore store = transaction.objectStore(STORENAME);

			store.add(product, product.getId());

			logger.info("Success data stored!");

			return null;
		};

		openDBRequest.onupgradeneeded = event -> {
			doUpgrade(event);
			return null;
		};
	}

}
