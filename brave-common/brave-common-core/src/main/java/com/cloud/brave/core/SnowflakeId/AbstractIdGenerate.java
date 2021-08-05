package com.cloud.brave.core.SnowflakeId;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: AbstractIdGenerate
 * @Description: 分布式雪花分片id生成器
 * @Author: yongchen
 * @Date: 2021/5/24 17:40
 **/
public class AbstractIdGenerate<T extends Serializable> implements IdGenerate<T> {

    /**
     *   |    1bit 		|      2~42bit 	|	  43~59bit 	|     60~64bit      |
     *   |   符合位    	|  时间戳（毫秒）|	 序列号		|	 机器码     |
     *   第1bit固定是0  符号位不动 。
     *   第2bit到第42bit使用时间蹉，精确到毫秒  41bit。 使用年限是69年
     *   第43bit到第59bit使用自增的序列号       17bit  可用序列号最大131071个，说明一毫秒我们可以生成131071个不同的序列号。
     *   第60bit到第64bit使用机器码	5bit   可以使系统可以分布式，最大分布式数量是32台机子。
     **/

    /**
     * 最大17bit的序列号是131071
     */
    private final static int MAX_ORDER_NO = 131071;
    /**
     * 时间戳的掩码41bit
     */
    private final static long TIME_CODE = Long.MAX_VALUE >>> 22;
    /**
     * 20210524
     * 因为我们的生成器可以使用69年，而我们想在这些时间里面，生成出来的id是逐渐自增的。
     * 所以我这里指定了从什么时候开始使用id生成器。
     */
    private final static long START_TIME = 1621849644643L;
    /**
     * 机器码 （0-31）
     */
    private final long MACHINE_CODE;
    /**
     * 用于生成序列号
     */
    private AtomicInteger orderNo;

    public AbstractIdGenerate(final Long machineCode) {
        if (machineCode < 0 || machineCode > 31) {
            throw new IllegalArgumentException("机器码不允许重复器机器码取值应在0~31之间");
        }
        this.MACHINE_CODE = machineCode;
        orderNo = new AtomicInteger(0);
    }

    @Override
    public Long idGenerate() {
        // 1、与基础时间对其得到相对时间
        long currentTimeMillis = System.currentTimeMillis() - START_TIME;
        // 2、保留相对时间的低41bit
        currentTimeMillis = currentTimeMillis & TIME_CODE;
        // 3、将1-41bit移动到高位作为id时间戳（2~42bit）
        currentTimeMillis = currentTimeMillis << 22;

        /*
         * 序列号自增1和获取
         * 注意：先增加再取值。
         */
        int orderNo = this.orderNo.incrementAndGet();
        do {
            if (orderNo > MAX_ORDER_NO) {
                //如果超过最大值则置为0
                if (this.orderNo.compareAndSet(orderNo, 0)){
                    //这里使用cas操作，所以不需要加锁    1、操作失败了   则表示别的线程已经更改了数据，则直接进行自增并获取则可以了
                    orderNo = 0;
                }else {
                    //注意：先增加再取值
                    orderNo = this.orderNo.getAndIncrement();
                }
            }
        } while (orderNo > MAX_ORDER_NO);
        //组装id |1bit(符号位)|时间戳(2~42bit)|机器码(43~59bit)|序列号(60~64bit)|
        return currentTimeMillis | MACHINE_CODE | (orderNo << 5);
    }
}
