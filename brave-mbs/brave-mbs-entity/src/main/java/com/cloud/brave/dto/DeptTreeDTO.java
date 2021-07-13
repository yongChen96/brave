package com.cloud.brave.dto;

import com.cloud.brave.entity.SysDept;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author admin
 * @version 1.0
 * @description: 部门树结构DTO
 * @date 2021/7/13 11:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptTreeDTO extends SysDept {
    private static final long serialVersionUID = 6344395452105438509L;

    List<DeptTreeDTO> children;

}
