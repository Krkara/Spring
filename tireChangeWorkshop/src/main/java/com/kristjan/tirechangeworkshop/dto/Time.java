package com.kristjan.tirechangeworkshop.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Time {
    public int id;
    public Date time;
    public boolean available;
}
