package com.kristjan.dto;

import com.kristjan.entity.Pet;
import lombok.Data;

import java.util.List;

@Data
public class OwnerDTO {
    private String name;
    private List<Pet> pets;
}
