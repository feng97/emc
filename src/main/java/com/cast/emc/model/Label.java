package com.cast.emc.model;

import com.cast.emc.model.enums.DataType;
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
@Table(name = "label")
public class Label extends BasicModel {
    private String name;
    private String content;
    @Enumerated(EnumType.STRING)
    private DataType type;
}
