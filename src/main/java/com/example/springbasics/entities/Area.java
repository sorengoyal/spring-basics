package com.example.springbasics.entities;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.gson.GsonBuilder;
import com.mapbox.geojson.Point;
import com.mapbox.geojson.Polygon;
import com.mapbox.geojson.gson.GeoJsonAdapterFactory;

import java.io.IOException;

public class Area {

    public Area(Integer id, Polygon geometry) {
        this.id = id;
        this.geometry = geometry;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    Integer id;
    Polygon geometry;

    public Integer getId() {
        return id;
    }

    @JsonSerialize(using = MapboxPolygonSerializer.class)
    @JsonDeserialize(using = MapboxPolygonDeserializer.class)
    public Polygon getGeometry() {
        return geometry;
    }
}

class MapboxPolygonSerializer extends JsonSerializer<Polygon> {

    @Override
    public void serialize(Polygon value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeFieldName("type");
        gen.writeString(value.type());
        gen.writeFieldName("coordinates");
        gen.writeStartArray();
        for (Point point: value.coordinates().get(0)) {
            gen.writeStartArray();
            gen.writeNumber(point.longitude());
            gen.writeNumber(point.latitude());
            gen.writeEndArray();
        }
        gen.writeEndArray();

        gen.writeEndObject();
    }
}

class MapboxPolygonDeserializer extends JsonDeserializer<Polygon> {

    @Override
    public Polygon deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        return Polygon.fromJson(p.readValueAsTree().toString());
    }
}
