package com.cast.emc.repository;

import com.cast.emc.model.Label;
import com.cast.emc.model.enums.DataType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabelJPA extends JpaRepository<Label,Long> {
    List<Label> getByType(DataType type);
}
