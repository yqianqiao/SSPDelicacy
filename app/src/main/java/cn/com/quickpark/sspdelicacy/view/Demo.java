package cn.com.quickpark.sspdelicacy.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.constraint.solver.widgets.Rectangle;
import android.util.AttributeSet;
import android.view.View;

import cn.com.quickpark.sspdelicacy.R;

/**
 * Created by y on 2018/8/1.
 */

public class Demo extends View {
    int dimensionPixelSize;

    public Demo(Context context) {
        super(context);
    }

    public Demo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Demo);
        dimensionPixelSize = a.getDimensionPixelSize(R.styleable.Demo_default_size, 100);
        a.recycle();
    }

    public Demo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Demo(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getSize(widthMeasureSpec);
        int height = getSize(heightMeasureSpec);
        if (width > height) {
            height = width;
        } else {
            width = height;
        }
        setMeasuredDimension(width, height);
    }

    private int getSize(int measureSpec) {
        int mSize = dimensionPixelSize;
        int size = MeasureSpec.getSize(measureSpec);
        int mode = MeasureSpec.getMode(measureSpec);

        switch (mode) {
            case MeasureSpec.EXACTLY:
                mSize = size;
                break;
            case MeasureSpec.AT_MOST:
                mSize = size;
                break;
            case MeasureSpec.UNSPECIFIED:
                mSize = 100;
                break;
        }
        return mSize;
    }
}
