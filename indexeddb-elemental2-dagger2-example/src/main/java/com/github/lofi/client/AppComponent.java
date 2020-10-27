package com.github.lofi.client;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { AppModule.class })
interface AppComponent {

	ProductComposite getProductComposite();

}
