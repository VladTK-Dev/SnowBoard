package org.example.snowboard.rent.service.impl;

import org.example.snowboard.rent.database.IOrderDAO;
import org.example.snowboard.rent.database.ISnowboardDAO;
import org.example.snowboard.rent.model.Order;
import org.example.snowboard.rent.model.OrderPosition;
import org.example.snowboard.rent.model.Snowboard;
import org.example.snowboard.rent.service.IOrderService;
import org.example.snowboard.rent.session.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    @Resource
    SessionObject sessionObject;

    @Autowired
    IOrderDAO orderDAO;

    @Autowired
    ISnowboardDAO snowboardDAO;

    @Override
    public void confirmOrder() {
        Order order = new Order(this.sessionObject.getUser(), new HashSet<>(this.sessionObject.getCart().getOrderPositions()));
        this.orderDAO.addOrder(order);
        for (OrderPosition orderPosition : order.getOrderPositions()) {
            Optional<Snowboard> carBox = this.snowboardDAO.getSnowboardById(orderPosition.getSnowboard().getId());
            if(carBox.isPresent()) {
                carBox.get().setRent(true);
                this.snowboardDAO.updateSnowboard(carBox.get());
            }
        }
        this.sessionObject.getCart().clearOrderPositions();
    }

    @Override
    public List<Order> getOrdersForCurrentUser() {
        return this.orderDAO.getOrdersByUserId(this.sessionObject.getUser().getId());
    }
}
