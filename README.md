# jsinterop-simple-jsframework-example
Simple Example for Accessing JavaScript Frameworks from Java GWT 
with [**Elemental2**](https://github.com/google/elemental2) 
and very early version of [**Elemental3 Webtack Generator**](https://github.com/realityforge/webtack)

This example uses GWT Boot as Starter Parent: https://github.com/gwtboot/gwt-boot-samples

Following articles are available as guidance:

- Take a look at this article to see the whole story: http://bit.ly/GWTJ2CLIndexedDB
- Also this article for the Design Patterns in Java: https://bit.ly/GwtIndexedDBDesignPatterns

We have three examples to use IndexedDB APIs on the web browser:

**1. indexeddb-elemental2-example**: this example uses Elemental2 lib.

**2. indexeddb-elemental3-example**: this example uses Elemental3 lib from Webtack (very early version).

**3. indexeddb-elemental2-dagger2-example**: this example shows how to use well known Design Patterns in GWT applications with IndexedDB.

[![Build Status](https://travis-ci.com/lofidewanto/jsinterop-simple-jsframework-example.svg?branch=master)](https://travis-ci.com/lofidewanto/jsinterop-simple-jsframework-example)

## Build example
To build the example:
```
mvn clean package
```

To run the example:

1. First: go to the project you want to run: **indexeddb-elemental2-example** or **indexeddb-elemental3-example** or **indexeddb-elemental2-dagger2-example** 

2. In the directory run the GWT Dev Mode: 
```
mvn gwt:generate-module gwt:devmode
```
2. Second: open your browser and go to following address:
```
http://127.0.0.1:8888/calculator/
```
3. GWT Dev Mode supports **automatic transpiling**. You just need to update your Java code in your IDE and **reload your web browser**. Your Java code will be automatically transpiled and your webapp stays uptodate without restarting the web server. If you change the **index.html** you need to push the **restart button** on the Swing GUI.

## Some helps for using IndexedDB

- Comparison of IndexedDB API and IndexedDB Promised library: https://developers.google.com/web/ilt/pwa/working-with-indexeddb#comparison_of_indexeddb_api_and_indexeddb_promised_library
		
- We use IndexedDB API with Elemental2

- Google IndexedDB: https://developers.google.com/web/ilt/pwa/working-with-indexeddb#opening_a_database

- Mozilla IndexedDB: https://developer.mozilla.org/en-US/docs/Web/API/IndexedDB_API

- Medium article IndexedDB for JavaScript: https://medium.com/jspoint/indexeddb-your-second-step-towards-progressive-web-apps-pwa-dcbcd6cc2076
	
- Example IndexedDB in JavaScript: https://github.com/mdn/to-do-notifications/blob/gh-pages/scripts/todo.js

- IndexedDB Elemental2JavaDoc: https://javadoc.io/doc/com.google.elemental2/elemental2-indexeddb/latest/index.html

- Elemental2 examples: https://github.com/google/elemental2/tree/master/samples
		
- Example Elemental2 IndexedDB in GWT Group: https://groups.google.com/g/google-web-toolkit/c/hUmBkKI_6ow/m/m6ppyUDGFwAJ

## Browsing in IndexedDB Chrome Webbrowser

![IndexedDB Chrome](https://github.com/lofidewanto/jsinterop-simple-jsframework-example/raw/master/indexeddb-elemental2-example/src/main/docs/indexeddb-chrome-browser.png)

## Tips for Elemental2

### Return of this event could be null or Js.undefined()

```
	openDBRequest.onsuccess = event -> {
		addProducts(event);
		return null;
	};
		
	openDBRequest.onsuccess = event -> {
		addProducts(event);
		return Js.undefined();
	};
```

### Generation process of JsInterops files (e.g. Elemental2 library)

- The generator for generating the JsInterops files is **JsInterop Generator** (https://github.com/google/jsinterop-generator).
- JsInterop Generator generates Java JsInterops files from Closure Externs. Closure Externs are mostly hand written. Example: 
Someone decided to map Any to a Template value. The Extern Def file is at https://github.com/google/closure-compiler/blob/master/externs/browser/w3c_indexeddb.js#L166 
but really they could map it in a few different ways. 
- **Webtack Generator** (https://github.com/realityforge/webtack) reads the WebIDL and generates Java JsInterop files from that. 
A similar but slightly different set of conventions from Elemental2. Example:  
In the case above Webtack would map it to Any but in some cases it maps it to @DoNotAutobox object.

### Differences beetween JsInterop Generator generated file and Webtack Generator generated file

#### *IDBRequest.java* from JsInterop Generator

```
@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public class IDBRequest<T> implements EventTarget {
  @JsType(isNative = true, name = "?", namespace = JsPackage.GLOBAL)
  public interface ErrorUnionType {
    @JsOverlay
    static IDBRequest.ErrorUnionType of(Object o) {
      return Js.cast(o);
    }

    @JsOverlay
    default DOMError asDOMError() {
      return Js.cast(this);
    }

    @JsOverlay
    default DOMException asDOMException() {
      return Js.cast(this);
    }

    @JsOverlay
    default boolean isDOMError() {
      return (Object) this instanceof DOMError;
    }

    @JsOverlay
    default boolean isDOMException() {
      return (Object) this instanceof DOMException;
    }
  }

  @JsFunction
  public interface OnerrorFn {
    Object onInvoke(Event p0);
  }

  @JsFunction
  public interface OnsuccessFn {
    Object onInvoke(Event p0);
  }

  @JsType(isNative = true, name = "?", namespace = JsPackage.GLOBAL)
  public interface SourceUnionType {
    @JsOverlay
    static IDBRequest.SourceUnionType of(Object o) {
      return Js.cast(o);
    }

    @JsOverlay
    default IDBCursor asIDBCursor() {
      return Js.cast(this);
    }

    @JsOverlay
    default IDBIndex asIDBIndex() {
      return Js.cast(this);
    }

    @JsOverlay
    default IDBObjectStore asIDBObjectStore() {
      return Js.cast(this);
    }

    @JsOverlay
    default boolean isIDBCursor() {
      return (Object) this instanceof IDBCursor;
    }

    @JsOverlay
    default boolean isIDBIndex() {
      return (Object) this instanceof IDBIndex;
    }

    @JsOverlay
    default boolean isIDBObjectStore() {
      return (Object) this instanceof IDBObjectStore;
    }
  }

  public IDBRequest.ErrorUnionType error;
  @Deprecated public double errorCode;
  public IDBRequest.OnerrorFn onerror;
  public IDBRequest.OnsuccessFn onsuccess;
  public String readyState;
  public T result;
  public IDBRequest.SourceUnionType source;
  public IDBTransaction transaction;

  public native void addEventListener(
      String type, EventListener listener, EventTarget.AddEventListenerOptionsUnionType options);

  public native void addEventListener(String type, EventListener listener);

  public native boolean dispatchEvent(Event evt);

  public native void removeEventListener(
      String type, EventListener listener, EventTarget.RemoveEventListenerOptionsUnionType options);

  public native void removeEventListener(String type, EventListener listener);
}
```

#### *IDBRequest.java* from Webtack Generator

```
/**
 * The request object does not initially contain any information about the result of the operation, but once information becomes available, an event is fired on the request, and the information becomes available through the properties of the IDBRequest instance.
 *
 * @see <a href="https://developer.mozilla.org/en-US/docs/Web/API/IDBRequest">IDBRequest - MDN</a>
 */
@Generated("org.realityforge.webtack")
@JsType(
    isNative = true,
    namespace = JsPackage.GLOBAL,
    name = "IDBRequest"
)
public class IDBRequest extends EventTarget {
  /**
   * The following example requests a given record title, onsuccess gets the associated record from the IDBObjectStore (made available as objectStoreTitleRequest.result), updates one property of the record, and then puts the updated record back into the object store. Also included at the bottom is an onerror function that reports what the error was if the request fails. For a full working example, see our To-do Notifications app (view example live.)
   *
   * @see <a href="https://developer.mozilla.org/en-US/docs/Web/API/IDBRequest/onerror">IDBRequest.onerror - MDN</a>
   */
  @Nullable
  public EventHandler onerror;

  /**
   * The following example requests a given record title, onsuccess gets the associated record from the IDBObjectStore (made available as objectStoreTitleRequest.result), updates one property of the record, and then puts the updated record back into the object store. For a full working example, see our To-do Notifications app (view example live.)
   *
   * @see <a href="https://developer.mozilla.org/en-US/docs/Web/API/IDBRequest/onsuccess">IDBRequest.onsuccess - MDN</a>
   */
  @Nullable
  public EventHandler onsuccess;

  IDBRequest() {
  }

  /**
   * A DOMError containing the relevant error. In Chrome 48+/Firefox 58+ this property returns a DOMException because DOMError has been removed from the DOM standard. The following error codes are returned under certain conditions:
   *
   * @see <a href="https://developer.mozilla.org/en-US/docs/Web/API/IDBRequest/error">IDBRequest.error - MDN</a>
   */
  @JsProperty(
      name = "error"
  )
  @Nullable
  public native DOMException error();

  /**
   * The IDBRequestReadyState of the request, which takes one of the following two values:
   *
   * @see <a href="https://developer.mozilla.org/en-US/docs/Web/API/IDBRequest/readyState">IDBRequest.readyState - MDN</a>
   */
  @JsProperty(
      name = "readyState"
  )
  @Nonnull
  public native String readyState();

  /**
   * any
   *
   * @see <a href="https://developer.mozilla.org/en-US/docs/Web/API/IDBRequest/result">IDBRequest.result - MDN</a>
   */
  @JsProperty(
      name = "result"
  )
  @Nullable
  public native Any result();

  /**
   * An object representing the source of the request, such as an IDBIndex, IDBObjectStore or IDBCursor.
   *
   * @see <a href="https://developer.mozilla.org/en-US/docs/Web/API/IDBRequest/source">IDBRequest.source - MDN</a>
   */
  @JsProperty(
      name = "source"
  )
  @Nullable
  public native IDBObjectStoreOrIDBIndexOrIDBCursorUnion source();

  /**
   * An IDBTransaction.
   *
   * @see <a href="https://developer.mozilla.org/en-US/docs/Web/API/IDBRequest/transaction">IDBRequest.transaction - MDN</a>
   */
  @JsProperty(
      name = "transaction"
  )
  @Nullable
  public native IDBTransaction transaction();

  @JsOverlay
  public final void addErrorListener(@Nonnull final EventListener callback,
      @Nonnull final AddEventListenerOptions options) {
    addEventListener( "error", Js.cast( callback ), options );
  }

  @JsOverlay
  public final void addErrorListener(@Nonnull final EventListener callback, final boolean options) {
    addEventListener( "error", Js.cast( callback ), options );
  }

  @JsOverlay
  public final void addErrorListener(@Nonnull final EventListener callback) {
    addEventListener( "error", Js.cast( callback ) );
  }

  @JsOverlay
  public final void removeErrorListener(@Nonnull final EventListener callback,
      @Nonnull final EventListenerOptions options) {
    removeEventListener( "error", Js.cast( callback ), options );
  }

  @JsOverlay
  public final void removeErrorListener(@Nonnull final EventListener callback,
      final boolean options) {
    removeEventListener( "error", Js.cast( callback ), options );
  }

  @JsOverlay
  public final void removeErrorListener(@Nonnull final EventListener callback) {
    removeEventListener( "error", Js.cast( callback ) );
  }

  @JsOverlay
  public final void addSuccessListener(@Nonnull final EventListener callback,
      @Nonnull final AddEventListenerOptions options) {
    addEventListener( "success", Js.cast( callback ), options );
  }

  @JsOverlay
  public final void addSuccessListener(@Nonnull final EventListener callback,
      final boolean options) {
    addEventListener( "success", Js.cast( callback ), options );
  }

  @JsOverlay
  public final void addSuccessListener(@Nonnull final EventListener callback) {
    addEventListener( "success", Js.cast( callback ) );
  }

  @JsOverlay
  public final void removeSuccessListener(@Nonnull final EventListener callback,
      @Nonnull final EventListenerOptions options) {
    removeEventListener( "success", Js.cast( callback ), options );
  }

  @JsOverlay
  public final void removeSuccessListener(@Nonnull final EventListener callback,
      final boolean options) {
    removeEventListener( "success", Js.cast( callback ), options );
  }

  @JsOverlay
  public final void removeSuccessListener(@Nonnull final EventListener callback) {
    removeEventListener( "success", Js.cast( callback ) );
  }
}
```

### Summary JsInterop Generator and Webtack Generator

- Elemental2 which is generated from *JsInterop Generator* is actually more JavaScript oriented since the
source is from hand written Closure Externs.
- *WebTack Generator* is more Java oriented.


