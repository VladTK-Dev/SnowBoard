package org.example.snowboard.rent.service;


import org.example.snowboard.rent.model.Snowboard;

import java.util.List;
import java.util.Optional;

public interface ISnowboardService {
    List<Snowboard> getAllSnowboards();
    Optional<Snowboard> getSnowboard(int id);
    void updateSnowboard(Snowboard snowboard);
    void deleteSnowboard(Snowboard snowboard);
    void addSnowboard(Snowboard snowboard);
}
