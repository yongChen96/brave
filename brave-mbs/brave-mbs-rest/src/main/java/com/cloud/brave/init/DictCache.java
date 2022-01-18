package com.cloud.brave.init;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cloud.brave.core.constant.CommonConstants;
import com.cloud.brave.entity.SysDict;
import com.cloud.brave.service.SysDictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yongchen
 * @description: 添加日志缓存
 * @date 2022/1/18 10:07
 */
@Slf4j
@Component
public class DictCache {

    @Resource
    private RedisTemplate template;
    @Resource
    private SysDictService sysDictService;

    /**
     * @description: 缓存字典信息到redis
     * @param
     * @return: void
     * @author yongchen
     * @date: 2022/1/18 10:27
     */
    @PostConstruct
    public void dictCache(){
        //获取未删除且启动状态的字典集合
        LambdaQueryWrapper<SysDict> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysDict::getDelState, CommonConstants.NOT_DELETED)
                        .eq(SysDict::getStatus, CommonConstants.ENABLE);
        List<SysDict> list = sysDictService.list(wrapper);
        //字典分组
        Map<String, List<SysDict>> collect = list.stream().collect(Collectors.groupingBy(SysDict::getDictCode));
        Iterator<Map.Entry<String, List<SysDict>>> iterator = collect.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, List<SysDict>> map = iterator.next();
            template.opsForValue().set(map.getKey(), map.getValue());
        }
    }
}
