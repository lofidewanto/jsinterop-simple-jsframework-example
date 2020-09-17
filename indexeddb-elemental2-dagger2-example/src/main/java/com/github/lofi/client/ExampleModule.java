package com.github.lofi.client;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ExampleModule {

	@Provides
	@Singleton
	public ProductService provideProductService(ProductRepository productRepository) {
		return new ProductService(productRepository);
	}

	@Provides
	@Singleton
	public ProductRepository provideProductRepository(IndexedDbElemental2 indexedDbElemental2) {
		return new ProductRepository(indexedDbElemental2);
	}

	@Provides
	@Singleton
	public IndexedDbElemental2 provideIndexedDbElemental2() {
		return new IndexedDbElemental2();
	}

}
