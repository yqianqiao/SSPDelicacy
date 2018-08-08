package cn.com.quickpark.sspdelicacy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * 画进度条
 * Created by y on 2018/7/3.
 */

public class ScheduleLine extends View {
    private Paint redPaint;
    private Paint grayPaint;
    //文字数组
    private List<String> textList;
    //当前进度
    private int position = 2;

    public ScheduleLine(Context context) {
        super(context);
        initPaint();
    }

    public ScheduleLine(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public ScheduleLine(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initPaint() {
        redPaint = new Paint();
        redPaint.setColor(Color.RED);
        redPaint.setStyle(Paint.Style.FILL);
        redPaint.setAntiAlias(true);
        redPaint.setStrokeWidth(2);

        grayPaint = new Paint();
        grayPaint.setColor(Color.GRAY);
        grayPaint.setStyle(Paint.Style.FILL);
        redPaint.setAntiAlias(true);
        grayPaint.setStrokeWidth(10f);
        grayPaint.setTextSize(40);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();


        if (textList == null || textList.size() < 0) {
            return;
        }
        int size = textList.size();
        int space = width / size / 2;

        for (int i = 1; i <= size; i++) {
            if (i <= position) {
                redPaint.setStyle(Paint.Style.FILL);
                canvas.drawCircle(width / size * i - space, height / 3, 20, redPaint);
            } else {
                redPaint.setStyle(Paint.Style.STROKE);
                canvas.drawCircle(width / size * i - space, height / 3, 20, redPaint);
            }
            canvas.drawText(textList.get(i - 1), width / size * i - space - getTextWidth(textList.get(i - 1)) / 2, height / 3 + 80, grayPaint);
            if (i > 1)
                // Logger.e("height " + width / size * i);
                canvas.drawLine(width / size * (i - 1) - space + 20, height / 3, width / size * i - space - 20, height / 3, redPaint);
        }


    }

    public void setTextList(List<String> list) {
        textList = list;
        invalidate();
    }

    public void setPosition(int position) {
        //TODO 后续优化
        this.position = position + 1;
    }

    /**
     * 获取文本的宽度
     *
     * @param text 文本
     * @return 宽度
     */
    private float getTextWidth(String text) {
        return grayPaint.measureText(text);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec),
                measureHeight(heightMeasureSpec));

    }

    public int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    public int measureHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

}
