# jsinterop-simple-jsframework-example
Simple Example for Accessing JavaScript Frameworks from Java GWT

## Build example
To build the example:
```
mvn clean package
```

To run the example:
1. First: run the GWT Dev Mode: 
```
mvn gwt:generate-module gwt:devmode
```
2. Second: open your browser and go to following address:
```
http://127.0.0.1:8888/calculator/
```
3. GWT Dev Mode supports **automatic transpiling**. You just need to update your Java code in your IDE and **reload your web browser**. Your Java code will be automatically transpiled and your webapp stays uptodate without restarting the web server. If you change the **index.html** you need to push the **restart button** on the Swing GUI.

## Some helps for using IndexedDB

Examples of IndexedDB
		
- Google IndexedDB: https://developers.google.com/web/ilt/pwa/working-with-indexeddb#opening_a_database

- Mozilla IndexedDB: https://developer.mozilla.org/en-US/docs/Web/API/IndexedDB_API

- Medium article IndexedDB: https://medium.com/jspoint/indexeddb-your-second-step-towards-progressive-web-apps-pwa-dcbcd6cc2076
	
- IndexedDB Elemental2JavaDoc: https://javadoc.io/doc/com.google.elemental2/elemental2-indexeddb/latest/index.html

- Elemental2 examples: https://github.com/google/elemental2/tree/master/samples
		
- Example in GWT Group: https://groups.google.com/g/google-web-toolkit/c/hUmBkKI_6ow/m/m6ppyUDGFwAJ



