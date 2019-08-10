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
@Table(name = "pulse_data")
public class PulseData extends BasicModel{
    private String name;    //信号名称

    private String item;    //项目名称

    private Long createTime;    //采集时间

    private String device;    //采集设备

    private String place;    // 采集地点

    private String data;    //采集数据

    private String remarks;    //备注
}
