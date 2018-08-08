package cn.com.quickpark.sspdelicacy.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by songdongliang on 2016/8/19.
 */

public class VerifyUtil {

    /**
     * 手机号验证
     *
     * @param  str
     * @return 验证通过返回true
     */
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    /**
     * 验证短信验证码
     * @param code
     * @return
     */
    public static boolean isSmsCode(String code){
        Pattern pattern = Pattern.compile("^[0-9]{4}$");
        Matcher matcher = pattern.matcher(code);
        return matcher.matches();
    }

    /**
     * 校验邮箱
     * @param email
     * @return
     */
    public static boolean isEmail(String email){
        if (TextUtils.isEmpty(email)) {
            return false;
        }
        String str="^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 检验是不是颜色字符串
     * @param color
     * @return
     */
    public static boolean isColor(String color){
        if (color == null) return false;
        Pattern pattern = Pattern.compile("^#[0-9a-fA-F]{6,}$");
        return pattern.matcher(color).matches();
    }
}
