package com.github.lofi.client;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ProductRepository {

	private IndexedDbElemental2 indexedDbElemental2;

	@Inject
	public ProductRepository(IndexedDbElemental2 indexedDbElemental2) {
		this.indexedDbElemental2 = indexedDbElemental2;
		initDb();
	}

	private void initDb() {
		indexedDbElemental2.initDb();
	}

	public void save(Product product) {
		indexedDbElemental2.saveData(product);
	}

}
