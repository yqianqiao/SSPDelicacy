package cn.com.quickpark.sspdelicacy.utils;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;

/**
 * Created by 11870 on 2016/11/17.
 */

public class ImageUtil {

    /**
     * 渲染图片
     * @param drawable
     * @param colorStateList
     * @return
     */
    public static Drawable tintDrawable(Drawable drawable, ColorStateList colorStateList){
        Drawable tempDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(tempDrawable,colorStateList);
        return tempDrawable;
    }

    public static Drawable tintDrawable(Drawable drawable, int colors) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable).mutate();
        DrawableCompat.setTint(wrappedDrawable, colors);
        return wrappedDrawable;
    }
}
