package com.github.lofi.client;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(namespace = JsPackage.GLOBAL, name = "Calculator", isNative = true)
public class Calculator {

    public int x;

    public int y;

    public native int sum();
}