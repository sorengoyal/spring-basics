package com.example.springbasics.services.area;

import com.example.springbasics.entities.Area;
import com.example.springbasics.repositories.area.AreaRepository;
import com.mapbox.geojson.Point;
import com.mapbox.geojson.Polygon;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class AreaServiceTest {
    @Test
    void test_createArea_successful(){
        // Arrange
        CreateAreaRequest request = new CreateAreaRequest(SAMPLE_POLYGON);
        Area successfullyAddedArea = new Area(1, SAMPLE_POLYGON);
        when(mockAreaRepository.add(any(Area.class))).thenReturn(successfullyAddedArea);

        // Act
        CreateAreaResponse result = classUnderTest.create(request);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getArea().getId());
        assertEquals(SAMPLE_POLYGON, result.getArea().getGeometry());

    }

    List<Point> points = List.of(
            Point.fromLngLat(10.0, 10.0),
            Point.fromLngLat(11.0, 11.0),
            Point.fromLngLat(12.0, 12.0),
            Point.fromLngLat(10.0, 10.0)
    );

    @InjectMocks
    private AreaService classUnderTest;
    @Mock
    private AreaRepository mockAreaRepository;
    final Polygon SAMPLE_POLYGON = Polygon.fromLngLats(List.of(points));
}

