package cn.com.quickpark.sspdelicacy.bean.my;

/**
 * Created by 11870 on 2016/12/30.
 * 车品牌下面的型号
 */

public class CarModelType {

    private int id ;

    private String cartypename ;

    private String brandtype ;

    private int parentid ;

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

    public String getBrandtype() {
        return brandtype;
    }

    public void setBrandtype(String brandtype) {
        this.brandtype = brandtype;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }
}
