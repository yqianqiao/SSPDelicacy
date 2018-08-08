package cn.com.quickpark.sspdelicacy.httpvo;

/**
 * Created by y on 2018/7/13.
 */

public class SaveCarVo {
    private String license;  //车牌
    private String parkingNo;  //车场编号
    private String area;  //所属库
    private int pwdType;  //密码类型   0：自设，1：随机
    private String pwd; //密码
    private String type; //车辆类型
    private String userId; //用户id
    private String colorCard;//色卡
    private String parkingName; //车场明称

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getParkingNo() {
        return parkingNo;
    }

    public void setParkingNo(String parkingNo) {
        this.parkingNo = parkingNo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getPwdType() {
        return pwdType;
    }

    public void setPwdType(int pwdType) {
        this.pwdType = pwdType;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
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

    public String getColorCard() {
        return colorCard;
    }

    public void setColorCard(String colorCard) {
        this.colorCard = colorCard;
    }

    public String getParkingName() {
        return parkingName;
    }

    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }
}
