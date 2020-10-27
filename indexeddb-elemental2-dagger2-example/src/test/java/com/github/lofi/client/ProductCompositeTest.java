package com.github.lofi.client;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.jboss.elemento.InputBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import elemental2.dom.HTMLInputElement;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class ProductCompositeTest {

	@Mock(answer = Answers.RETURNS_DEEP_STUBS)
	private Product product;

	@Mock(answer = Answers.RETURNS_DEEP_STUBS)
	private InputBuilder<HTMLInputElement> input;

	private ProductComposite productComposite;

	@BeforeEach
	void setUp(@Mock(answer = Answers.RETURNS_DEEP_STUBS) ProductService productService) throws Exception {
		productComposite = spy(new ProductComposite(productService));

		when(productService.createProduct()).thenReturn(product);
	}

	@Test
	void on_ProductCreated_amount_bigger_than_350() {
		when(product.getCalculatedPriceWithAmount()).thenReturn(500);
		doReturn(input).when(productComposite).renderInputElements();

		productComposite.onProductCreated();

		verify(productComposite, times(1)).renderInputElements();
		verify(input, times(1)).element();
	}

	@Test
	void on_ProductCreated_amount_smaller_than_350() {
		when(product.getCalculatedPriceWithAmount()).thenReturn(200);

		productComposite.onProductCreated();

		verify(productComposite, times(0)).renderInputElements();
		verify(input, times(0)).element();
	}

}