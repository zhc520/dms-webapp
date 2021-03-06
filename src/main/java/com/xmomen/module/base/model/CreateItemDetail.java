package com.xmomen.module.base.model;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import lombok.Data;

import javax.persistence.*;

public @Data class CreateItemDetail extends BaseMybatisModel {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 商品ID
     */
    private Integer cdItemId;

    /**
     * 商品的详细内容
     */
    private String itemDetailContent;
}