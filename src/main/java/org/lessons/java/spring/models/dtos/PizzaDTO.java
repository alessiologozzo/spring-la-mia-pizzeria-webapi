package org.lessons.java.spring.models.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PizzaDTO {
	
	@NotBlank
	@Size(max = 255)
	private String name;
	
	@NotNull
	@Min(0)
	private float price;
	
	public PizzaDTO(String name, float price) {
		setName(name);
		setPrice(price);
	}
	
	@SuppressWarnings("unused")
	private PizzaDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
}
