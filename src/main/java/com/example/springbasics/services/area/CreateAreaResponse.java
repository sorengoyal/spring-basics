package com.example.springbasics.services.area;

import com.example.springbasics.entities.Area;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class CreateAreaResponse {
    @Getter
    private Area area;
}
