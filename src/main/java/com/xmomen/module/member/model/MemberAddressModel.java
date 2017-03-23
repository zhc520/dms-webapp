package com.xmomen.module.member.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xmomen.module.member.entity.MemberAddress;
import lombok.Data;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import org.springframework.beans.BeanUtils;

import java.lang.Integer;
import java.lang.String;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-3-23 14:57:22
 * @version 1.0.0
 */
@ExcelTarget(value = "MemberAddressModel")
public @Data class MemberAddressModel implements Serializable {

    /**  */
    @NotBlank(message = "为必填项")
    @Length(max = 32, message = "字符长度限制[0,32]")
    private String id;
    /** 客户ID */
    @Excel(name = "客户ID")
    @NotBlank(message = "客户ID为必填项")
    @Range(max = 999999999, min = -999999999, message = "客户ID数值范围[999999999,-999999999]")
    private Integer cdMemberId;
    /**  */
    @Excel(name = "")
    @NotBlank(message = "为必填项")
    @Range(max = 999999999, min = -999999999, message = "数值范围[999999999,-999999999]")
    private Integer province;
    /** 城市 */
    @Excel(name = "城市")
    @NotBlank(message = "城市为必填项")
    @Range(max = 999999999, min = -999999999, message = "城市数值范围[999999999,-999999999]")
    private Integer city;
    /** 区域 */
    @Excel(name = "区域")
    @NotBlank(message = "区域为必填项")
    @Range(max = 999999999, min = -999999999, message = "区域数值范围[999999999,-999999999]")
    private Integer area;
    /** 详细地址 */
    @Excel(name = "详细地址")
    @NotBlank(message = "详细地址为必填项")
    @Length(max = 256, message = "详细地址字符长度限制[0,256]")
    private String address;
    /** 完整地址 */
    @Excel(name = "完整地址")
    @NotBlank(message = "完整地址为必填项")
    @Length(max = 256, message = "完整地址字符长度限制[0,256]")
    private String fullAddress;

    /**
    * Get MemberAddress Entity Object
    * @return
    */
    @JsonIgnore
    public MemberAddress getEntity(){
        MemberAddress memberAddress = new MemberAddress();
        BeanUtils.copyProperties(this, memberAddress);
        return memberAddress;
    }


}
