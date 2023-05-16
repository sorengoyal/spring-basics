package com.example.springbasics.controllers.area;

import com.example.springbasics.entities.Area;
import com.example.springbasics.services.area.AreaService;
import com.mapbox.geojson.Point;
import com.mapbox.geojson.Polygon;
import com.example.springbasics.services.area.AreaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("areas")
public class AreaController {

    @PostMapping("/")
    public ResponseEntity<?> post(@RequestBody CreateAreaRequest request) {
        if (request.getCoordinates().size() < 4) {
            return ResponseEntity
                    .badRequest()
                    .body("Length of coordinates should be greater of equal to 4");
        }
        List<Point> points = new ArrayList<>();
        for (List<Double> coordinate: request.getCoordinates()) {
            if (coordinate.size() != 2) {
                return ResponseEntity
                        .badRequest()
                        .body("Each coordinate must have 2 double values");
            }
            points.add(Point.fromLngLat(coordinate.get(0), coordinate.get(1)));
        }

        Polygon polygon = Polygon.fromLngLats(List.of(points));
        Area area = areaService.create(polygon);
        return ResponseEntity
                .created(URI.create("/areas/" + area.getId()))
                .body(area);
    }

    @GetMapping("/")
    public final ResponseEntity<List<Area>> list() {
        List<Area> areas = areaService.getAll();
        return ResponseEntity.ok(areas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Area> get(@PathVariable int id) throws AreaNotFoundException{
        Area area = areaService.getById(id);
        return ResponseEntity.ok(area);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Area> delete(@PathVariable int id) throws AreaNotFoundException{
        Area area = areaService.deleteById(id);
        return ResponseEntity.(area);
    }


    public AreaController(@Autowired AreaService areaService) {
        this.areaService = areaService;
    }

    private AreaService areaService;

    @ExceptionHandler(AreaNotFoundException.class)
    ResponseEntity<ErrorResponse> handleErrors(Exception e) {
        return new ResponseEntity<>(
                new ErrorResponse(e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

}
