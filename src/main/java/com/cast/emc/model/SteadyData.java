package com.cast.emc.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @创建人 feng
 * @创建时间 2019/8/24
 * @描述
 */
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "steady_data")
public class SteadyData extends BasicModel {
    private String name;    //信号名称

    private String item;    //项目名称

    private String place;    // 采集地点

    private String data;    //采集数据

    private String report;  //采集报告

    private String remarks;    //备注

    private Long createTime;
}
