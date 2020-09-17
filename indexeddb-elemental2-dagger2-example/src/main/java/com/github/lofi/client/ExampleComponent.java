package com.github.lofi.client;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { ExampleModule.class })
interface ExampleComponent {

	ExampleWebApp getExampleWebApp();

}
