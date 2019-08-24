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
@Table(name = "emc_data")
public class EmcData extends BasicModel {
    private String name;    //信号名称

    private String item;    //项目名称

    private String interferenceType;    //干扰类型

    private Long createTime;    //采集时间

    private String device;    //采集设备

    private String modulation;    //调制方式

    private String snr;    //信噪比

    private String place;    // 采集地点

    private Long centerFreq;    //中心频率

    private Long bandWidth;    //带宽

    private String data;    //采集数据

    private String report;  //采集报告

    private String remarks;    //备注
}
