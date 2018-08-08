package cn.com.quickpark.sspdelicacy.httpvo;

/**
 * Created by y on 2018/7/20.
 */

public class AddCarVo {
    private String license;
    private String color;
    private String username;
    private String carseries;
    private String token;
    private String carseriespic;//车系图片

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCarseries() {
        return carseries;
    }

    public void setCarseries(String carseries) {
        this.carseries = carseries;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCarseriespic() {
        return carseriespic;
    }

    public void setCarseriespic(String carseriespic) {
        this.carseriespic = carseriespic;
    }
}
