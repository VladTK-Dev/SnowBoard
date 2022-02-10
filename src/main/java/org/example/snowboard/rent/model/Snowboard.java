package org.example.snowboard.rent.model;

import javax.persistence.*;

@Entity(name = "t_snowboard")
public class Snowboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private double price;

    private boolean rent;

    public Snowboard() {

    }

    public Snowboard(String name, double price) {

        this.name = name;

        this.price = price;

        this.rent = false;
    }

    public Snowboard(int id, String brand, String model, String plate, double price, int year) {
        this.id = id;

        this.name = name;

        this.price = price;

        this.rent = false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean isRent() {
        return rent;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRent(boolean rent) {
        this.rent = rent;
    }
}
