package org.example.snowboard.rent.service;

import org.example.snowboard.rent.model.Order;

import java.util.List;

public interface IOrderService {
    void confirmOrder();
    List<Order> getOrdersForCurrentUser();
}
