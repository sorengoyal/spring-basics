package com.example.springbasics.services.area;

import com.example.springbasics.entities.Area;
import com.example.springbasics.repositories.area.AreaRepository;
import com.mapbox.geojson.Polygon;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaService {

    public Area create(Polygon polygon) {
        Area area = new Area(null, polygon);
        Area areaWithId = areaRepository.add(area);
        return areaWithId;
    }

    public List<Area> getAll() {
        List<Area> areas = areaRepository.list();
        return areas;
    }
    public Area update(){
        throw new UnsupportedOperationException();
    }

    public Area getById(int id) throws AreaNotFoundException {
        Area area = areaRepository.read(id);
        if (area == null) {
            String msg = String.format("id: %d not found", id);
            throw new AreaNotFoundException(msg);
        }
        return area;
    }

    public Area deleteById(int id) throws AreaNotFoundException {
        Area area = areaRepository.delete(id);
        if (area == null) {
            String msg = String.format("id: %d not found", id);
            throw new AreaNotFoundException(msg);
        }
        return area;
    }

    public void deleteTask(int id) {
        throw new UnsupportedOperationException();
    }
    public Area add(Area area) {
        throw new UnsupportedOperationException();
    }

    public AreaService(@Autowired AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    private AreaRepository areaRepository;
}