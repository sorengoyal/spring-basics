package com.example.springbasics;

import com.mapbox.geojson.Point;
import com.mapbox.geojson.Polygon;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("areas")
public class AreaController {

    @GetMapping("/")
    public final Map<Integer, Area> list() {
        return polygonTable;
    }

    @PostMapping("/")
    public Boolean post(@RequestBody Area area){
        area.setId(id_sequence);
        polygonTable.put(id_sequence, area);
        id_sequence++;
        return true;
    }

    @GetMapping("/{id}")
    public Area get(@PathVariable int id){
        return polygonTable.get(id);
    }
    public AreaController() {
        polygonTable = new HashMap<Integer, Area>();
        List<Point> points = List.of(
                Point.fromLngLat(10.0, 10.0),
                Point.fromLngLat(11.0, 11.0),
                Point.fromLngLat(12.0, 12.0),
                Point.fromLngLat(10.0, 10.0)
                );

        Polygon polygon = Polygon.fromLngLats(List.of(points));
        polygonTable.put(1, new Area(1, polygon));
        id_sequence = 3;
    }

    private Map<Integer, Area> polygonTable;
    private Integer id_sequence;

}
