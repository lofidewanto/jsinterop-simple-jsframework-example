package com.github.lofi.client;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class Product {

	private String id;

	private String name;

	@JsOverlay
	public final String getId() {
		return id;
	}

	@JsOverlay
	public final void setId(String id) {
		this.id = id;
	}

	@JsOverlay
	public final String getName() {
		return name;
	}

	@JsOverlay
	public final void setName(String name) {
		this.name = name;
	}
}
