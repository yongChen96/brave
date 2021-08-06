package com.cloud.brave.mybatisplus.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.cloud.brave.core.SnowflakeId.IdGenerate;
import com.cloud.brave.mybatisplus.generator.entity.BaseEntity;
import com.cloud.brave.mybatisplus.generator.entity.BaseSuperEntuty;
import com.cloud.brave.core.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @ClassName: DatebaseSqlMetaObjectHandler
 * @Description: 数据库字段填充配置类
 * @Author: yongchen
 * @Date: 2021/5/26 9:41
 **/
@Slf4j
@Component
public class DatebaseSqlMetaObjectHandler implements MetaObjectHandler {
    /**
     * id生成器
     **/
    private final IdGenerate<Long> idGenerate;
    /**
     * 主键id类型
     */
    private static final String FIELD_TYPE_STRING = "java.lang.String";
    /**
     * 实体类型判断符
     */
    private static final String ET = "et";
    /**
     * 公共字段信息
     */
    private static final String ID = "id";
    private static final String CREATE_TIME = "createTime";
    private static final String CREATE_USER = "createUser";
    private static final String UPDATE_TIME = "updateTime";
    private static final String UPDATE_USER = "updateUser";

    public DatebaseSqlMetaObjectHandler(IdGenerate<Long> idGenerate) {
        super();
        this.idGenerate = idGenerate;
    }

    /**
     * @Author: yongchen
     * @Description: 数据插入填充
     * @Date: 11:40 2021/5/26
     * @Param: [metaObject]
     * @return: void
     **/
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill............");
        boolean flag = false;
        if (metaObject.getOriginalObject() instanceof BaseSuperEntuty) {
            BaseSuperEntuty baseSuperEntuty = (BaseSuperEntuty) metaObject.getOriginalObject();
            // 判断是否需要填充主键id
            if (baseSuperEntuty.getId() == null) {
                flag = true;
            }
            // 填充创建时间及创建用户,默认如果该属性无值是填充
            if (baseSuperEntuty.getCreateTime() == null) {
                this.setFieldValByName(CREATE_TIME, LocalDateTime.now(), metaObject);
            }
            if (baseSuperEntuty.getCreateUser() == null) {
                if (StringUtils.equals(FIELD_TYPE_STRING, metaObject.getGetterType(CREATE_USER).getName())) {
                    this.setFieldValByName(CREATE_USER, String.valueOf(JwtUtils.getUserId()), metaObject);
                } else {
                    this.setFieldValByName(CREATE_USER,  JwtUtils.getUserId(), metaObject);
                }
            }
        }

        // 填充主键id
        if (flag) {
            Long id = idGenerate.idGenerate();
            if (StringUtils.equals(FIELD_TYPE_STRING, metaObject.getGetterType(ID).getName())) {
                this.setFieldValByName(ID, String.valueOf(id), metaObject);
            } else {
                this.setFieldValByName(ID, id, metaObject);
            }
        }

        // 插入时填充更新用户及更新时间
        if (metaObject.getOriginalObject() instanceof BaseEntity) {
            BaseEntity baseEntity = (BaseEntity) metaObject.getOriginalObject();
            fillUpdate(metaObject, baseEntity, "");
        }
    }

    /**
     * @Author: yongchen
     * @Description: 数据更新填充
     * @Date: 11:40 2021/5/26
     * @Param: [metaObject]
     * @return: void
     **/
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill............");
        if (metaObject.getOriginalObject() instanceof BaseEntity) {
            BaseEntity baseEntity = (BaseEntity) metaObject.getOriginalObject();
            fillUpdate(metaObject, baseEntity, "");
        } else {
            Object et = metaObject.getValue(ET);
            if (null != et && et instanceof BaseEntity) {
                BaseEntity baseEntity = (BaseEntity) et;
                fillUpdate(metaObject, baseEntity, ET + ".");
            }
        }
    }

    /**
     * @Author: yongchen
     * @Description: 填充更新时间及更新用户信息
     * @Date: 11:16 2021/5/26
     * @Param: [metaObject, baseEntity, et]
     * @return: void
     **/
    private void fillUpdate(MetaObject metaObject, BaseEntity baseEntity, String et) {
        if (baseEntity.getUpdateTime() == null) {
            this.strictUpdateFill(metaObject, UPDATE_TIME, LocalDateTime.class, LocalDateTime.now());
        }
        if (baseEntity.getUpdateUser() == null) {
            if (StringUtils.equals(FIELD_TYPE_STRING, metaObject.getGetterType(et + UPDATE_USER).getName())) {
                this.strictUpdateFill(metaObject, UPDATE_USER, String.class, String.valueOf(JwtUtils.getUserId()));
            } else {
                this.strictUpdateFill(metaObject, UPDATE_USER, Long.class, JwtUtils.getUserId());
            }
        }
    }
}
