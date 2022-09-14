package com.example.demo.DataModels;

import java.util.List;


public class Order {
    private Long order_Id;
    private Long user_Id;
    private List<Long> productIds;
    private List<Integer> quantities;

    public Order(Long order_Id,Long user_Id) {
        this.order_Id = order_Id;
        this.user_Id = user_Id;
    }

    public Order() {
    }

    public Long getOrder_Id() {
        return order_Id;
    }

    public void setOrder_Id(Long order_Id) {
        this.order_Id = order_Id;
    }

    public Long getUser_Id() {
        return user_Id;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    public List<Integer> getQuantities() {
        return quantities;
    }

    public void setQuantities(List<Integer> quantities) {
        this.quantities = quantities;
    }
}
