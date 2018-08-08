package cn.com.quickpark.sspdelicacy.bean.homepark;

import java.util.List;

/**
 * Created by y on 2018/7/16.
 */

public class ParkCarWaitBean {
    /**
     * area : A库
     * createTime : 1531810973831
     * entrance : B3
     * expectedTime : 60000
     * license : 粤A00010
     * no : PARK_ORDER_201807170000000014
     * orderNum : 17
     * parkAppointment : 1531810973832
     * parkStatus : 0
     * parkingNo : 3
     * pwdType : 1
     * state : ["等待入库","请入库","入库成功"]
     * type : 轿车
     * userId : SSP20170420000554
     */

    private String area;
    private long createTime;
    private String entrance;
    private int expectedTime;
    private String license;
    private String no;
    private int orderNum;
    private long parkAppointment;
    private int parkStatus;//parkStatus：0等待入库 1请入库 2入库成功，当等于>=1的时候不能取消预约
    private String parkingNo;
    private int pwdType;
    private String type;
    private String userId;
    private List<String> state;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getEntrance() {
        return entrance;
    }

    public void setEntrance(String entrance) {
        this.entrance = entrance;
    }

    public int getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(int expectedTime) {
        this.expectedTime = expectedTime;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public long getParkAppointment() {
        return parkAppointment;
    }

    public void setParkAppointment(long parkAppointment) {
        this.parkAppointment = parkAppointment;
    }

    public int getParkStatus() {
        return parkStatus;
    }

    public void setParkStatus(int parkStatus) {
        this.parkStatus = parkStatus;
    }

    public String getParkingNo() {
        return parkingNo;
    }

    public void setParkingNo(String parkingNo) {
        this.parkingNo = parkingNo;
    }

    public int getPwdType() {
        return pwdType;
    }

    public void setPwdType(int pwdType) {
        this.pwdType = pwdType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getState() {
        return state;
    }

    public void setState(List<String> state) {
        this.state = state;
    }


}
