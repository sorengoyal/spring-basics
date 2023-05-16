package com.example.springbasics.repositories.area;

import com.example.springbasics.entities.Area;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AreaRepository {
    public Area add(Area area) {
        if (area == null) {
            throw new IllegalArgumentException("area cannot be null");
        }
        if (area.getId() != null) {
            throw new IllegalArgumentException("cannot add an Area object with a client-specified id");
        }
        Area element = new Area(id_sequence, area.getGeometry());
        polygonTable.put(id_sequence, element);
        id_sequence++;
        return element;
    }

    public List<Area> list() {
        return polygonTable.values().stream().toList();
    }

    public Area read(int id) {
        return polygonTable.get(id);
    }

    public Area delete(int id) {
        return polygonTable.remove(id);
    }

    public AreaRepository() {
        polygonTable = new HashMap<>();
        id_sequence = 1;
    }

    private Map<Integer, Area> polygonTable;
    private Integer id_sequence;
}
