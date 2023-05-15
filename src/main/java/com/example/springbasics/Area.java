package com.example.springbasics;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mapbox.geojson.Polygon;

import java.io.IOException;

public class Area {

    public Area(Integer id, Polygon polygon) {
        this.id = id;
        this.polygon = polygon;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    Integer id;
    Polygon polygon;

    public Integer getId() {
        return id;
    }

    @JsonSerialize(using = MapboxPolygonSerializerDeserializer.class)
    public Polygon getPolygon() {
        return polygon;
    }
}

class MapboxPolygonSerializerDeserializer extends JsonSerializer<Polygon> {

    @Override
    public void serialize(Polygon value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(value.toJson());
    }
}

