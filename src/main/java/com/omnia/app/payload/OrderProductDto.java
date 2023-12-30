package com.omnia.app.payload;

import com.omnia.app.model.Recipe;

public class OrderProductDto {

    private Recipe product;
    private Integer quantity;
    private Long tabId;

    public Recipe getProduct() {
        return product;
    }

    public Long getTabId() {
		return tabId;
	}

	public void setTabId(Long tabId) {
		this.tabId = tabId;
	}

	public void setProduct(Recipe product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
}
