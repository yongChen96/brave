package com.cloud.brave.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.brave.dto.UserPageDTO;
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
    
    /**
     * @Author yongchen
     * @Description 用户分页查询
     * @Date 17:38 2021/7/13
     * @param page
     * @param data
     * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.cloud.brave.dto.UserPageDTO>
     **/
    Page<UserPageDTO> userPage(@Param("page") Page<Object> page, @Param("data") SysUser data);
}
