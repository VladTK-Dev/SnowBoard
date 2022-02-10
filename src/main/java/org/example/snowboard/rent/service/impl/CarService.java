package org.example.snowboard.rent.service.impl;

import org.example.snowboard.rent.database.ISnowboardDAO;
import org.example.snowboard.rent.model.Snowboard;
import org.example.snowboard.rent.service.ISnowboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService implements ISnowboardService {

    @Autowired
    ISnowboardDAO snowboardDAO;

    public List<Snowboard> getAllSnowboards() {
        return this.snowboardDAO.getSnowboards();
    }

    @Override
    public void addSnowboard(Snowboard snowboard) {
        this.snowboardDAO.addSnowboard(snowboard);
    }

    @Override
    public void deleteSnowboard(Snowboard snowboard) {
        this.snowboardDAO.deleteSnowboard(snowboard);
    }

    @Override
    public Optional<Snowboard> getSnowboard(int id) {
        return this.snowboardDAO.getSnowboardById(id);
    }

    @Override
    public void updateSnowboard(Snowboard snowboard) {
        this.snowboardDAO.updateSnowboard(snowboard);
    }
}
