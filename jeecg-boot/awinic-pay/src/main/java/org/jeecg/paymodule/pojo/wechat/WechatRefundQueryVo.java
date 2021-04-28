package org.jeecg.paymodule.pojo.wechat;


import org.jeecg.paymodule.pojo.Pojo;

public class WechatRefundQueryVo extends Pojo {

    //商户号
    private String merId;
    //授权交易方
    private String plantformNo;
    //商户退款订单号
    private String orderId;
    //平台退款订单号
    private String cmbOrderId;
    //收银员
    private String userId;

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    public String getPlantformNo() {
        return plantformNo;
    }

    public void setPlantformNo(String plantformNo) {
        this.plantformNo = plantformNo;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCmbOrderId() {
        return cmbOrderId;
    }

    public void setCmbOrderId(String cmbOrderId) {
        this.cmbOrderId = cmbOrderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
