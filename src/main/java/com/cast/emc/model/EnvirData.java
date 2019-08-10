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
@Table(name = "envir_data")
public class EnvirData extends BasicModel {
    private String standard;
    private String item;
    private String place;
    private String layout;
    private String pol;
    private String frequencyL;
    private String frequencyR;
    private String state;
    private String data;
    private String envelope;
    private String exceed;
    private String report;
    private Long createTime;
}
