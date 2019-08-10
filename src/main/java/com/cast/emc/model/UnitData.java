package com.cast.emc.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "unit_data")
public class UnitData extends BasicModel {
    private String spacecraft;
    private String batch;
    private String standard;
    private String model;
    private String equName;
    private String equNumber;
    private String subsys;
    private String stage;
    private String item;
    private String place;
    private String layout;
    private String pol;
    private String testObj;
    private String frequencyL;
    private String frequencyR;
    private String state;
    private String data;
    private String envelope;
    private String exceed;
    private String report;
    private Long createTime;
}
