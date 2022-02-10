package org.example.snowboard.rent.database;

import org.example.snowboard.rent.model.Snowboard;

import java.util.List;
import java.util.Optional;

public interface ISnowboardDAO {
    List<Snowboard> getSnowboards();
    Optional<Snowboard> getSnowboardById(int snowboardId);
    void updateSnowboard(Snowboard snowboard);
    void addSnowboard(Snowboard snowboard);
    void deleteSnowboard(Snowboard snowboard);
}
