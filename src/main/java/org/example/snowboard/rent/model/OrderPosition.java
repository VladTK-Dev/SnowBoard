package org.example.snowboard.rent.model;

import javax.persistence.*;

@Entity(name = "t_order_position")
public class OrderPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Snowboard snowboard;

    public OrderPosition() {
    }

    public OrderPosition(int id, Snowboard snowboard) {
        this.id = id;
        this.snowboard = snowboard;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Snowboard getSnowboard() {
        return snowboard;
    }

    public void setSnowboard(Snowboard Snowboard) {
        this.snowboard = snowboard;
    }
}
