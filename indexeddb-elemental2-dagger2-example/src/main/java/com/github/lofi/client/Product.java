package com.github.lofi.client;

public class Product {

	private final String id;

	private final String name;

	private final String type;

	private final Integer amount;

	private Product(Builder productBuilder) {
		this.id = productBuilder.id;
		this.name = productBuilder.name;
		this.type = productBuilder.type;
		this.amount = productBuilder.amount;
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

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", type=" + type + ", amount=" + amount + "]";
	}

	public static class Builder {

		private final String id;

		private final String name;

		private String type;

		private Integer amount;

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

		public Product build() {
			Product product = new Product(this);
			return product;
		}
	}

}
