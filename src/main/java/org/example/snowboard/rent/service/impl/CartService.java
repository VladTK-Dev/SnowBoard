package org.example.snowboard.rent.service.impl;

import org.example.snowboard.rent.database.ISnowboardDAO;
import org.example.snowboard.rent.model.OrderPosition;
import org.example.snowboard.rent.model.Snowboard;
import org.example.snowboard.rent.service.ICartService;
import org.example.snowboard.rent.session.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class CartService implements ICartService {

    @Autowired
    ISnowboardDAO snowboardDAO;

    @Resource
    SessionObject sessionObject;

    public void addCarToCart(int snowBoardId) {
        Optional<Snowboard> carBox = this.snowboardDAO.getSnowboardById(snowBoardId);

        if(carBox.isEmpty()) {
            return;
        }

        Snowboard snowboard = carBox.get();
        if(snowboard.isRent()) {
            return;
        }

        OrderPosition orderPosition = new OrderPosition(0, snowboard);
        this.sessionObject.getCart().getOrderPositions().add(orderPosition);
    }
}
