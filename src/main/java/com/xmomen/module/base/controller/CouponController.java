package com.xmomen.module.base.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.base.entity.CdActivityAddress;
import com.xmomen.module.base.entity.CdCoupon;
import com.xmomen.module.base.mapper.CouponMapper;
import com.xmomen.module.base.model.CouponModel;
import com.xmomen.module.base.model.CreateCoupon;
import com.xmomen.module.base.model.UpdateCoupon;
import com.xmomen.module.base.service.CouponService;
import com.xmomen.module.logger.Log;

/**
 * Created by Jeng on 2016/3/30.
 */
@RestController
public class CouponController {

    @Autowired
    CouponService couponService;

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 卡券列表
     * @param limit
     * @param offset
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/coupon", method = RequestMethod.GET)
    @Log(actionName = "查询卡券列表")
    public Page<CouponModel> getUserList(@RequestParam(value = "limit") Integer limit,
                                  @RequestParam(value = "offset") Integer offset,
                                  @RequestParam(value = "couponNumber", required = false) String couponNumber,
                                  @RequestParam(value = "couponType",required = false) String couponType,
                                  @RequestParam(value = "isSend",required = false) Integer isSend,
                                  @RequestParam(value = "keyword", required = false) String keyword){
   	    Map map = new HashMap<String,Object>();
        map.put("keyword", keyword);
        map.put("couponNumber", couponNumber);
        map.put("couponType",couponType);
        map.put("isSend",isSend);
        return (Page<CouponModel>) mybatisDao.selectPage(CouponMapper.CouponMapperNameSpace + "getCouponList", map, limit, offset);
    }

    /**
     *  卡券列表
     * @param id
     */
    @RequestMapping(value = "/coupon/{id}", method = RequestMethod.GET)
    @Log(actionName = "查询卡券")
    public CdCoupon getUserList(@PathVariable(value = "id") Integer id){
        return couponService.getCoupon(id);
    }

    /**
     * 新增卡券
     * @param createCoupon
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/coupon", method = RequestMethod.POST)
    @Log(actionName = "新增卡券")
    public CdCoupon createCoupon(@RequestBody @Valid CreateCoupon createCoupon, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        CdCoupon cdCoupon = new CdCoupon();
        cdCoupon.setCouponType(createCoupon.getCouponType());
        cdCoupon.setCouponCategory(createCoupon.getCouponCategory());
        cdCoupon.setCouponDesc(createCoupon.getCouponDesc());
        cdCoupon.setCouponNumber(createCoupon.getCouponNumber());
        cdCoupon.setCouponPassword(createCoupon.getCouponPassword());
        cdCoupon.setBeginTime(createCoupon.getBeginTime());
        cdCoupon.setEndTime(createCoupon.getEndTime());
        cdCoupon.setCouponValue(createCoupon.getCouponValue());
        cdCoupon.setIsGift(createCoupon.getIsGift());
        cdCoupon.setIsUsed(createCoupon.getIsUsed());
        cdCoupon.setIsUseful(createCoupon.getIsUseful());
        cdCoupon.setNotes(createCoupon.getNotes());
        return couponService.createCoupon(cdCoupon);
    }

    /**
     * 更新卡券
     * @param id
     * @param updateCoupon
     * @param bindingResult
     * @throws ArgumentValidException
     */
    @RequestMapping(value = "/coupon/{id}", method = RequestMethod.PUT)
    @Log(actionName = "更新卡券")
    public void updateCoupon(@PathVariable(value = "id") Integer id,
                           @RequestBody @Valid UpdateCoupon updateCoupon, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        CdCoupon cdCoupon = new CdCoupon();
        cdCoupon.setId(id);
        cdCoupon.setCouponCategory(updateCoupon.getCouponCategory());
        cdCoupon.setCouponType(updateCoupon.getCouponType());
        cdCoupon.setCouponDesc(updateCoupon.getCouponDesc());
        cdCoupon.setCouponNumber(updateCoupon.getCouponNumber());
        cdCoupon.setCouponPassword(updateCoupon.getCouponPassword());
        cdCoupon.setBeginTime(updateCoupon.getBeginTime());
        cdCoupon.setEndTime(updateCoupon.getEndTime());
        cdCoupon.setCouponValue(updateCoupon.getCouponValue());
        cdCoupon.setIsGift(updateCoupon.getIsGift());
        cdCoupon.setIsUsed(updateCoupon.getIsUsed());
        cdCoupon.setIsUseful(updateCoupon.getIsUseful());
        cdCoupon.setNotes(updateCoupon.getNotes());
        couponService.updateCoupon(cdCoupon);
    }

    /**
     *  删除卡券
     * @param id
     */
    @RequestMapping(value = "/coupon/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "删除卡券")
    public void deleteCoupon(@PathVariable(value = "id") Long id){
        mybatisDao.deleteByPrimaryKey(CdCoupon.class, id);
    }

    /**
     * @param id
     */
    @RequestMapping(value = "/coupon/sendOneCoupon", method = RequestMethod.GET)
    @Log(actionName = "发放单卡")
    public void sendOneCoupon(
    		@RequestParam(value = "id") Integer id,
    		@RequestParam(value="companyId") Integer companyId,
    		@RequestParam(value="customerMangerId") Integer customerMangerId,
    		@RequestParam(value="couponNumber") String couponNumber){
    	couponService.sendOneCoupon(id,companyId,customerMangerId,couponNumber);
    }
    
    /**
     * @param id
     */
    @RequestMapping(value = "/coupon/sendMoreCoupon", method = RequestMethod.GET)
    @Log(actionName = "批量发放卡")
    public void sendMoreCoupon(
    		@RequestParam(value="companyId") Integer companyId,
    		@RequestParam(value="customerMangerId")Integer customerMangerId,
    		@RequestParam(value="couponNumberList") String couponNumberList){
    	String[] couponNumbers = couponNumberList.split(",");
    	for(int i = 0,length = couponNumbers.length;i < length; i++){
    		String couponNumber = couponNumbers[i];
    		CdCoupon coupon = new CdCoupon();
    		coupon.setCouponNumber(couponNumber);
//    		coupon.setCouponType(1);
    		coupon.setIsSend(0);
    		coupon.setIsUseful(0);
    		coupon = mybatisDao.selectOneByModel(coupon);
    		if(coupon != null)
    		couponService.sendOneCoupon(coupon.getId(),companyId,customerMangerId,coupon.getCouponNumber());
    	}
    }
    
    /**
     * @param id
     */
    @RequestMapping(value = "/coupon/activityAddress", method = RequestMethod.GET)
    @Log(actionName = "活动送货地址信息")
    public void activityAddress(
    		@RequestParam(value="couponNumber") String couponNumber,
    		@RequestParam(value="consignmentName", required = false)String consignmentName,
    		@RequestParam(value="consignmentPhone", required = false) String consignmentPhone,
    		@RequestParam(value="consignmentAddress", required = false) String consignmentAddress){
    	CdActivityAddress activityAddress = new CdActivityAddress();
    	activityAddress.setCouponNumber(couponNumber);
    	activityAddress = mybatisDao.selectOneByModel(activityAddress);
    	if(activityAddress == null){
    		activityAddress = new CdActivityAddress();
    		activityAddress.setConsignmentAddress(consignmentAddress);
    		activityAddress.setConsignmentPhone(consignmentPhone);
    		activityAddress.setConsignmentName(consignmentName);
    		activityAddress.setCouponNumber(couponNumber);
    		mybatisDao.save(activityAddress);
    	}else{
    		activityAddress.setConsignmentAddress(consignmentAddress);
    		activityAddress.setConsignmentPhone(consignmentPhone);
    		activityAddress.setConsignmentName(consignmentName);
    		mybatisDao.update(activityAddress);
    	}
    }
}