package com.cloud.brave.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.brave.dto.UserDTO;
import com.cloud.brave.dto.UserDetailsDTO;
import com.cloud.brave.dto.UserInfoDTO;
import com.cloud.brave.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author yongchen
 * @since 2021-06-03
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * @Author: yongchen
     * @Description: 获取用户信息
     * @Date: 14:53 2021/6/4
     * @Param: [username]
     * @return: com.cloud.brave.dto.UserInfoDTO
     **/
    UserInfoDTO getUserInfo(String username);

    /**
     * @Author: yongchen
     * @Description: 添加新用户信息
     * @Date: 11:01 2021/6/7
     * @Param: [userDTO]
     * @return: java.lang.Boolean
     **/
    Boolean saveNewUser(UserDTO userDTO);

    /**
     * @Author: yongchen
     * @Description: 修改用户锁定状态
     * @Date: 15:55 2021/6/8
     * @Param: [id, locakStatus]
     * @return: java.lang.Boolean
     **/
    Boolean locking(Long id, String locakStatus);

    /**
     * @Author yongchen
     * @Description 获取用户详细信息
     * @Date 15:07 2021/7/15
     * @param id
     * @return com.cloud.brave.dto.UserDetailsDTO
     **/
    UserDetailsDTO getUserDetailsById(Long id);

    /**
     * @Author yongchen
     * @Description 重置用户密码
     * @Date 10:02 2021/7/19
     * @param id
     * @return boolean
     **/
    boolean resetPassword(Long id);
}
