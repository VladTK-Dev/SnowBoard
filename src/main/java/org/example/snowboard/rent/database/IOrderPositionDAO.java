package org.example.snowboard.rent.database;

import org.example.snowboard.rent.model.OrderPosition;
import java.util.List;

public interface IOrderPositionDAO {
    void addOrderPosition(OrderPosition orderPosition, int orderId);
    List<OrderPosition> getOrderPositionsByOrderId(int orderId);
}
