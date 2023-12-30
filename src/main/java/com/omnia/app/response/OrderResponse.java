package com.omnia.app.response;

import java.io.Serializable;

public class OrderResponse implements Serializable {
	
	private Integer quantity ;
	private  Long  order_id ;
	private Long recipe_Id ;
	private Long tableID;
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}
	public Long getRecipe_Id() {
		return recipe_Id;
	}
	public void setRecipe_Id(Long recipe_Id) {
		this.recipe_Id = recipe_Id;
	}
	public Long getTableID() {
		return tableID;
	}
	public void setTableID(Long tableID) {
		this.tableID = tableID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((order_id == null) ? 0 : order_id.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((recipe_Id == null) ? 0 : recipe_Id.hashCode());
		result = prime * result + ((tableID == null) ? 0 : tableID.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderResponse other = (OrderResponse) obj;
		if (order_id == null) {
			if (other.order_id != null)
				return false;
		} else if (!order_id.equals(other.order_id))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (recipe_Id == null) {
			if (other.recipe_Id != null)
				return false;
		} else if (!recipe_Id.equals(other.recipe_Id))
			return false;
		if (tableID == null) {
			if (other.tableID != null)
				return false;
		} else if (!tableID.equals(other.tableID))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "OrderResponse [quantity=" + quantity + ", order_id=" + order_id + ", recipe_Id=" + recipe_Id
				+ ", tableID=" + tableID + "]";
	}
	
	

}
