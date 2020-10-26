package com.github.lofi.client;

import static org.jboss.elemento.Elements.button;
import static org.jboss.elemento.Elements.input;
import static org.jboss.elemento.Elements.label;
import static org.jboss.elemento.EventType.click;
import static org.jboss.elemento.EventType.keydown;
import static org.jboss.elemento.InputType.text;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.elemento.HtmlContentBuilder;
import org.jboss.elemento.InputBuilder;

import elemental2.dom.DomGlobal;
import elemental2.dom.Element;
import elemental2.dom.HTMLInputElement;
import elemental2.dom.HTMLLabelElement;

@Singleton
public class ProductComposite {

	private static Logger logger = Logger.getLogger(ProductComposite.class.getName());

	private ProductService productService;

	private Element panelElement;

	@Inject
	public ProductComposite(ProductService productService) {
		this.productService = productService;
		createScreen();
	}

	private void createScreen() {
		panelElement = DomGlobal.document.getElementById("panel");

		HtmlContentBuilder<HTMLLabelElement> label = label().textContent("Try this to add data... ")
				.add(button().textContent("Click to add data!").on(click, event -> createProduct()));

		panelElement.appendChild(label.element());
	}

	private void createProduct() {
		Product createdProduct = productService.createProduct();

		if (createdProduct.getCalculatedPriceWithAmount() >= 350) {
			logger.info("getCalculatedPriceWithAmount >= 350");

			InputBuilder<HTMLInputElement> input = input(text);
			HtmlContentBuilder<HTMLLabelElement> label = label().textContent("Input: ").add(input).on(keydown,
					event -> keyDown());

			input.element().value = createdProduct.getCalculatedPriceWithAmount().toString();

			panelElement.appendChild(label.element());
		}
	}

	private void keyDown() {
		logger.info("keydown...");
	}

}
