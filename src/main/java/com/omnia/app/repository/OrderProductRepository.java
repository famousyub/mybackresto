package com.omnia.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.omnia.app.model.OrderProduct;
import com.omnia.app.model.OrderProductPK;
import com.omnia.app.response.OrderResponse;


@Repository
public interface OrderProductRepository extends CrudRepository<OrderProduct, OrderProductPK> {

//
//@Query("SELECT order.quantity,order.tableId,order.product.id,order.order.id FROM OrderProduct order  WHERE order.order_id=?1")
//List<OrderResponse>  getAllresponseorderbyOrderId(Long order_id);


//@Query("SELECT order.quantity,order.tableId,order.pk.getProduct().getId(),order.pk.getOrder().getId() FROM OrderProduct order")
//List<OrderResponse>  getAllresponseorderbyOrders();


	
	
	@Query(value="SELECT * FROM order_product t ",nativeQuery=true)
	List<OrderResponse>  getAllresponseorderbyOrders();





}