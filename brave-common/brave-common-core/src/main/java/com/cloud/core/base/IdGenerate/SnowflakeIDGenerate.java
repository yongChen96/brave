package com.cloud.core.base.IdGenerate;

/**
 * @ClassName: SnowflakeIDGenerate
 * @Description: ID生成器
 * @Author: yongchen
 * @Date: 2021/5/24 17:40
 **/
public class SnowflakeIDGenerate extends AbstractIdGenerate<Long> {

    public SnowflakeIDGenerate(Long machineCode) {
        super(machineCode);
    }

    @Override
    public Long idGenerate() {
        return super.idGenerate();
    }
}
