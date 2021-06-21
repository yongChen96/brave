package com.cloud.brave.mapper;

import com.cloud.brave.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 *
 * @author yongchen
 * @since 2021-06-17
 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * @Author: yongchen
     * @Description: 根据用户获取用户角色
     * @Date: 17:12 2021/6/18
     * @Param: [userId]
     * @return: java.util.List<com.cloud.brave.entity.SysRole>
     **/
    List<SysRole> findRolesByUserId(@Param("userId") Long userId);
}
