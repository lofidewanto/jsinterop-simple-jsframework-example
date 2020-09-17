package com.github.lofi.client;

import static org.jboss.elemento.Elements.button;
import static org.jboss.elemento.Elements.label;
import static org.jboss.elemento.EventType.click;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.elemento.HtmlContentBuilder;

import elemental2.dom.DomGlobal;
import elemental2.dom.Element;
import elemental2.dom.HTMLButtonElement;
import elemental2.dom.HTMLLabelElement;

@Singleton
public class ExampleWebApp {

	private ProductService productService;

	@Inject
	public ExampleWebApp(ProductService productService) {
		this.productService = productService;
		createScreen();
	}

	private void createScreen() {
		Element element = DomGlobal.document.getElementById("panel");

		HtmlContentBuilder<HTMLLabelElement> label = label().textContent("Try this to add data... ");
		HtmlContentBuilder<HTMLButtonElement> button = button();
		button.textContent("Click to add data!");
		button.on(click, event -> createProduct());

		label.add(button);

		element.appendChild(label.element());
	}

	private void createProduct() {
		productService.createProduct();
	}

}
