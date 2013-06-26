package org.lunifera.examples.vaaclipse.demo1.e4.views.model;

public class ItemDTO {

	private String number;
	private String name;
	private double weight;
	private double price;
	private int imageIndex;

	public ItemDTO(String number, String name, double weight, double price,
			int imageIndex) {
		super();
		this.number = number;
		this.name = name;
		this.weight = weight;
		this.price = price;
		this.imageIndex = imageIndex;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getImageIndex() {
		return imageIndex;
	}

	public void setImageIndex(int imageIndex) {
		this.imageIndex = imageIndex;
	}

}
