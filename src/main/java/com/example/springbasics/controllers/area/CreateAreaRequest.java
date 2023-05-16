package com.example.springbasics.controllers.area;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
public class CreateAreaRequest {
    @Getter
    @Setter
    private List<List<Double>> coordinates;
}
