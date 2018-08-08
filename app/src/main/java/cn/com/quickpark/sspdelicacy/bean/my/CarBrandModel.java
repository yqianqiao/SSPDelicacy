package cn.com.quickpark.sspdelicacy.bean.my;

import java.util.ArrayList;

import cn.com.quickpark.sspdelicacy.view.indexlib.IndexBar.bean.BaseIndexPinyinBean;


/**
 * Created by 11870 on 2016/12/30.
 */

public class CarBrandModel extends BaseIndexPinyinBean {
    private int id ;

    private String cartypename;

    private String brandlogourl;

    private String logoname;

    private String initials;

    private ArrayList<CarModelType> btiList;

    private boolean top ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCartypename() {
        return cartypename;
    }

    public void setCartypename(String cartypename) {
        this.cartypename = cartypename;
    }

    public String getBrandlogourl() {
        return brandlogourl;
    }

    public void setBrandlogourl(String brandlogourl) {
        this.brandlogourl = brandlogourl;
    }

    public String getLogoname() {
        return logoname;
    }

    public void setLogoname(String logoname) {
        this.logoname = logoname;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public ArrayList<CarModelType> getBtiList() {
        return btiList;
    }

    public void setBtiList(ArrayList<CarModelType> btiList) {
        this.btiList = btiList;
    }

    @Override
    public String getTarget() {
        return cartypename;
    }

    public boolean isTop() {
        return top;
    }

    public CarBrandModel setTop(boolean top) {
        this.top = top;
        return this;
    }

    @Override
    public boolean isNeedToPinyin() {
        return !top;
    }


    @Override
    public boolean isShowSuspension() {
        return !top;
    }
}
