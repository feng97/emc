package com.cast.emc.model;

import com.cast.emc.model.enums.SignalType;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "sample_data")
public class SampleData extends BasicModel{

    @Enumerated(EnumType.STRING)
    private SignalType signalType;

    private String type;

    private String data;

    private Long createTime;

}
