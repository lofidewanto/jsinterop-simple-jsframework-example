package com.github.lofi.client;

import elemental3.Console;
import elemental3.Event;
import elemental3.Global;
import elemental3.indexeddb.IDBDatabase;
import elemental3.indexeddb.IDBFactory;
import elemental3.indexeddb.IDBObjectStore;
import elemental3.indexeddb.IDBOpenDBRequest;
import elemental3.indexeddb.IDBTransaction;
import elemental3.indexeddb.IDBTransactionMode;

public class IndexedDbElemental3 {

	private static final String DBNAME = "mydbtest";

	private static final int DBVERSION = 1;

	private static final String STORENAME = "mystorename";

	private IDBOpenDBRequest openDBRequest;

	private IDBDatabase db;

	public void openDb() {
		IDBFactory indexedDB = Global.indexedDB();

		openDBRequest = indexedDB.open(DBNAME, DBVERSION);
		openDBRequest.onerror = event -> Console.log("Error opening DB: " + event.target());
		openDBRequest.onsuccess = this::addProducts;
		openDBRequest.onupgradeneeded = this::doUpgrade;
	}

	private void doUpgrade(Event event) {
		Console.log("Upgrade DB: " + event.target());

		db = (IDBDatabase) openDBRequest.result();

		String[] paths = { "id" };

		IDBObjectStore store = db.createObjectStore(STORENAME);

		store.createIndex("products_id_unqiue", paths);
	}

	private void addProducts(Event event) {
		Console.log("Success opening DB: " + event.type());

		db = (IDBDatabase) openDBRequest.result();

		IDBTransaction transaction = db.transaction(STORENAME, IDBTransactionMode.readwrite);
		IDBObjectStore store = transaction.objectStore(STORENAME);

		int random = (int) (Math.random() * 50 + 1);
		String key = Integer.valueOf(random).toString();

		Console.log("Random: " + key);

		Product product = new Product();
		product.setId(key);
		product.setName("Lofi " + key);

		store.add(product, key);
	}

}
