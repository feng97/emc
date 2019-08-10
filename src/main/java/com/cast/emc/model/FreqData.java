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
@Table(name = "freq_data")
public class FreqData extends BasicModel{
    private String name;    //信号名称

    private String item;    //项目名称

    private Long createTime;    //采集时间

    private String modulation;    //调制方式

    private String place;    // 采集地点

    private String data;    //采集数据

    private String remarks;    //备注
}
