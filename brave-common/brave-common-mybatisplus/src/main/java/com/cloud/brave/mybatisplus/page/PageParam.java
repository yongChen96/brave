package com.cloud.brave.mybatisplus.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @ClassName: PageParam
 * @Description: 分页参数对象
 * @Author: yongchen
 * @Date: 2021/6/2 17:29
 **/
@Getter
@Setter
@ApiModel(value = "PageParam", description = "分页入数对象")
public class PageParam<T> implements Serializable {
    private static final long serialVersionUID = 4646978429403182998L;

    /**
     * 初始默认开始页码
     **/
    private static final int INIT_PAGE_NO = 1;
    /**
     * 初始默认每页条数
     **/
    private static final int INIT_PAGE_SIZE = 10;
    /**
     * 初始默认最大每页条数
     **/
    private static final int INIT_MAX_PAGE_SIZE = 10000;

    /**
     * 页码
     **/
    @ApiModelProperty(value = "页码")
    private long pageNo;
    /**
     * 每页条数
     **/
    @ApiModelProperty(value = "每页条数")
    private long pageSize;

    /**
     * 分页查询条件
     **/
    @ApiModelProperty(value = "分页查询条件")
    private T data;

    public Long getPageNo() {
        if (this.pageNo <= 0) {
            this.pageNo = INIT_PAGE_NO;
        }
        return this.pageNo;
    }

    public Long getPageSize() {
        if (this.pageSize <= 0) {
            this.pageSize = INIT_PAGE_SIZE;
        }
        if (this.pageSize > INIT_MAX_PAGE_SIZE) {
            this.pageSize = INIT_MAX_PAGE_SIZE;
        }
        return this.pageSize;
    }

    public int getLimit() {
        return Integer.valueOf(String.valueOf(getPageNo()));
    }

    public int getOffset() {
        long offset = (getPageNo() - 1) * getPageNo();
        return getPageNo() > 0 ? Integer.valueOf(String.valueOf(offset)) : 0;
    }

    @JsonIgnore
    public <T> Page<T> getPage() {
        return new Page<>(this.getPageNo(), this.getPageSize());
    }
}
