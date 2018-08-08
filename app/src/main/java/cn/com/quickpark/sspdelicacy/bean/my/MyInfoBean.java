package cn.com.quickpark.sspdelicacy.bean.my;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by y on 2018/7/17.
 */

public class MyInfoBean implements Serializable {


    private UserBean user;
    private ArrayList<CarListBean> carList;


    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public ArrayList<CarListBean> getCarList() {
        return carList;
    }

    public void setCarList(ArrayList<CarListBean> carList) {
        this.carList = carList;
    }


    public static class UserBean implements Serializable {
        /**
         * birthDay : 2000-01-01
         * createTime : 1531128550000
         * id : 1526
         * mobile : 13648471837
         * nickName : 悬架
         * password : 126cf6df47fc75f8f729b44d0efe7724
         * signature : 吃饭睡觉打豆豆
         * updateTime : 1531201605000
         * userId : UserId20180709000000
         */

        private String birthDay;
        private long createTime;
        private int id;
        private String mobile;
        private String nickName;
        private String password;
        private String signature;
        private long updateTime;
        private String userId;
        private String avatarPic;
        private String sex;

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAvatarPic() {
            return avatarPic;
        }

        public void setAvatarPic(String avatarPic) {
            this.avatarPic = avatarPic;
        }

        public String getBirthDay() {
            return birthDay;
        }

        public void setBirthDay(String birthDay) {
            this.birthDay = birthDay;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

    }

    public static class CarListBean implements Serializable {
        /**
         * carseries : changan
         * id : 2688
         * license : 台A12345Z
         * username : UserId20180709000000
         */

        private String carseries; //车系
        private String id;
        private String license;
        private String username;
        private String colorName;
        private String carbgurl;//背景
        private String carseriespic; //车系图片
        private int isDefaultCar;//是否为默认车辆 0不是,1是',
        private String colourCard;//车身颜色

        public String getColorName() {
            return colorName;
        }

        public void setColorName(String colorName) {
            this.colorName = colorName;
        }

        public String getCarseries() {
            return carseries;
        }

        public void setCarseries(String carseries) {
            this.carseries = carseries;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLicense() {
            return license;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getCarbgurl() {
            return carbgurl;
        }

        public void setCarbgurl(String carbgurl) {
            this.carbgurl = carbgurl;
        }

        public String getCarseriespic() {
            return carseriespic;
        }

        public void setCarseriespic(String carseriespic) {
            this.carseriespic = carseriespic;
        }

        public int getIsDefaultCar() {
            return isDefaultCar;
        }

        public void setIsDefaultCar(int isDefaultCar) {
            this.isDefaultCar = isDefaultCar;
        }

        public String getColourCard() {
            return colourCard;
        }

        public void setColourCard(String colourCard) {
            this.colourCard = colourCard;
        }

    }
}
