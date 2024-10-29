package com.ecom.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScrReportDod implements Serializable{

    private String material;
    private String day15;
    private String day14;
    private String day13;
    private String day12;
    private String day11;
    private String day10;
    private String day9;
    private String day8;
    private String day7;
    private String day6;
    private String day5;
    private String day4;
    private String day3;
    private String day2;
    private String day1;
    private String today;
    private String dod;

}
