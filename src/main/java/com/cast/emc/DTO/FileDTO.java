package com.cast.emc.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {
    private Integer code;
    private String msg;
    private HashMap<String, String> data;
}
