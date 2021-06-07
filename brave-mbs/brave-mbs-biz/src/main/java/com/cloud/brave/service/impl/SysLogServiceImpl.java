package com.cloud.brave.service.impl;

import com.cloud.brave.entity.SysLog;
import com.cloud.brave.mapper.SysLogMapper;
import com.cloud.brave.service.SysLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统日志 服务实现类
 * </p>
 *
 * @author yongchen
 * @since 2021-06-07
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

}
