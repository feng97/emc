package com.cast.emc.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @创建人 feng
 * @创建时间 2019/8/18
 * @描述
 */
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "used_freq_point")
public class UsedFreqPoint extends BasicModel {
    private String freqL;
    private String freqR;
    private String bizName;
    private Long createTime;
}
