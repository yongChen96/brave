package com.cloud.brave.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.brave.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author yongchen
 * @since 2021-06-03
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {
    
}
