package com.cloud.core.SnowflakeId;

import java.io.Serializable;

/**
 * @ClassName: IdGenerate
 * @Description:  <p>主键（id）生成器，用于生成唯一id号</p>
 *  <p>1、注意：在写主键生成器时，请考虑主键不会重复。</p>
 *  <p>2、尤其需要考虑在短时间内大量调用生成器，也保证不要出现重复</p>
 *  <p>3、实现类必须是线程安全 的，因为会应用在多线程环境中去</p>
 * @Author: yongchen
 * @Date: 2021/5/24 17:03
 **/
@FunctionalInterface
public interface IdGenerate<T extends Serializable> {
    /**
     * @Author: yongchen
     * @Description: id生成器
     * @Date: 17:39 2021/5/24
     * @Param:
     * @return: T
     **/
    Long idGenerate();
}
