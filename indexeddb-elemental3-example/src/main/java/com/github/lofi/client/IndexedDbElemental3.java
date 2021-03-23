package com.github.lofi.client;

import akasha.Console;
import akasha.Event;
import akasha.Global;
import akasha.idb.IDBDatabase;
import akasha.idb.IDBFactory;
import akasha.idb.IDBObjectStore;
import akasha.idb.IDBOpenDBRequest;
import akasha.idb.IDBTransaction;
import akasha.idb.IDBTransactionMode;
import akasha.idb.IDBVersionChangeEvent;
import jsinterop.base.Js;

public class IndexedDbElemental3 {

	private static final String DBNAME = "mydbtest";

	private static final int DBVERSION = 1;

	private static final String STORENAME = "mystorename";

	public void openDb() {
		if (!Js.global().has("indexedDB")) {
			Console.log("IndexedDB not present");
			return;
		}
		IDBFactory indexedDB = Global.indexedDB();

		IDBOpenDBRequest request = indexedDB.open(DBNAME, DBVERSION);
		request.onerror = event -> Console.log("Error opening DB: " + event.target());
		request.onsuccess = this::addProducts;
		request.onupgradeneeded = this::doUpgrade;
	}

	private void doUpgrade(IDBVersionChangeEvent event) {
		Console.log("Upgrade DB: " + event.target());

		IDBObjectStore store = getDatabase(event).createObjectStore(STORENAME);

		store.createIndex("products_id_unqiue", new String[]{ "id" });
	}

	private void addProducts(Event event) {
		Console.log("Success opening DB: " + event.type());

		IDBTransaction transaction = getDatabase(event).transaction(STORENAME, IDBTransactionMode.readwrite);
		IDBObjectStore store = transaction.objectStore(STORENAME);

		int random = (int) (Math.random() * 50 + 1);
		String key = Integer.valueOf(random).toString();

		Console.log("Random: " + key);

		Product product = new Product();
		product.setId(key);
		product.setName("Lofi " + key);

		store.add(product, key);
	}

	private IDBDatabase getDatabase(Event event) {
		return (IDBDatabase) ((IDBOpenDBRequest) event.currentTarget()).result();
	}
}
