package org.example.snowboard.rent.database;

import org.example.snowboard.rent.model.Order;

import java.util.List;

public interface IOrderDAO {
    void addOrder(Order order);
    List<Order> getOrdersByUserId(int userId);
}
