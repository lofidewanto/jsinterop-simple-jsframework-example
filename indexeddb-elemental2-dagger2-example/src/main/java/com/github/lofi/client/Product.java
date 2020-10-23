package com.github.lofi.client;

public class Product {

	private final String id;

	private final String name;

	private final String type;

	private final Integer amount;

	private final Integer price;

	private Product(Builder productBuilder) {
		this.id = productBuilder.id;
		this.name = productBuilder.name;
		this.type = productBuilder.type;
		this.amount = productBuilder.amount;
		this.price = productBuilder.price;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public Integer getAmount() {
		return amount;
	}

	public Integer getPrice() {
		return price;
	}

	public Integer getCalculatedPriceWithAmount() {
		if (price == null || amount == null) {
			return 0;
		} else {
			return price * amount;
		}
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", type=" + type + ", amount=" + amount + ", price=" + price
				+ "]";
	}

	public static class Builder {

		private final String id;

		private final String name;

		private String type;

		private Integer amount;

		private Integer price;

		public Builder(String id, String name) {
			this.id = id;
			this.name = name;
		}

		public Builder setType(String type) {
			this.type = type;
			return this;
		}

		public Builder setAmount(Integer amount) {
			this.amount = amount;
			return this;
		}

		public Builder setPrice(Integer price) {
			this.price = price;
			return this;
		}

		public Product build() {
			Product product = new Product(this);
			return product;
		}
	}

}
