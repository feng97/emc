package com.cast.emc.model;

import com.cast.emc.model.enums.OperationTypeEnum;
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
@Table(name = "operation")
public class Operation extends BasicModel{
    private String operationDataName;
    private String operationType;
    private String operationIp;
    private Long operationTime;
    private String operationContent;
}
