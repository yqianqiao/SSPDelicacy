package cn.com.quickpark.sspdelicacy.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import cn.com.quickpark.sspdelicacy.R;

/**
 * Created by chenguojun on 16/8/18.
 */
public class CustomPlateView extends LinearLayout implements KeyboardView.OnKeyboardActionListener, View.OnTouchListener {
    private static final String TAG = "CustomPlateView";
    EditText plate_num1, plate_num2, plate_num3, plate_num4, plate_num5, plate_num6, plate_num7, plate_numX;

    private KeyboardView mKeyboardView;
    private PopupWindow mKeyboardWindow;
    private LinearLayout layoutX;

    private boolean isN;//是不是7.0系统
    private boolean isNewEnergyCar; //是不是能源车
    private boolean needcustomkeyboard = true; // 是否启用自定义键盘
    private int scrolldis = 50; // 输入框在键盘被弹出时，要被推上去的距离
    public static int screenw = -1;// 未知宽高
    public static int screenh = -1;
    public static int screenh_nonavbar = -1; // 不包含导航栏的高度
    public static int real_scontenth = -1; // 实际内容高度， 计算公式:屏幕高度-导航栏高度-电量栏高度
    public static float density = 1.0f;
    public static int densityDpi = 160;
    private Keyboard province_keyboard;// 省份键盘
    private Keyboard alphanumeric_keyboard;// 数字字母键盘

    private Window mWindow;
    private View mDecorView;
    private View mContentView;
    private StringBuilder mLicense;
    private String[] provinceShort = new String[]{"京", "津", "冀", "鲁", "晋", "蒙", "辽", "吉", "黑"
            , "沪", "苏", "浙", "皖", "闽", "赣", "豫", "鄂", "湘"
            , "粤", "桂", "渝", "川", "贵", "云", "藏", "陕", "甘"
            , "青", "琼", "新", "港", "澳", "台", "宁"};

    private String[] alphanumeric = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
            , "Q", "W", "E", "R", "T", "Y", "U", "P", "港", "澳"
            , "A", "S", "D", "F", "G", "H", "J", "K", "L", "警"
            , "Z", "X", "C", "V", "B", "N", "M", "学"};
    private int currenPosition = 0;
    public KeyboardFinish mKeyboardFinish;

    public CustomPlateView(Context context) {
        super(context);
    }

    public CustomPlateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        isN = Build.VERSION.SDK_INT == Build.VERSION_CODES.N;
        View view = LayoutInflater.from(context).inflate(R.layout.custom_plate_view, this, true);
        plate_num1 = view.findViewById(R.id.plate_num1);
        plate_num2 = view.findViewById(R.id.plate_num2);
        plate_num3 = view.findViewById(R.id.plate_num3);
        plate_num4 = view.findViewById(R.id.plate_num4);
        plate_num5 = view.findViewById(R.id.plate_num5);
        plate_num6 = view.findViewById(R.id.plate_num6);
        plate_num7 = view.findViewById(R.id.plate_num7);
        plate_numX = view.findViewById(R.id.plate_numX);
        layoutX = view.findViewById(R.id.layout_x);
        plate_num1.setOnTouchListener(this);
        plate_num2.setOnTouchListener(this);
        plate_num3.setOnTouchListener(this);
        plate_num4.setOnTouchListener(this);
        plate_num5.setOnTouchListener(this);
        plate_num6.setOnTouchListener(this);
        plate_num7.setOnTouchListener(this);
        plate_numX.setOnTouchListener(this);
        initAttributes(context);
        initKeyboard(context, attrs);
        mLicense = new StringBuilder();
    }

    private void initAttributes(Context context) {

        province_keyboard = new Keyboard(context, R.xml.province_short_keyboard);
        alphanumeric_keyboard = new Keyboard(context, R.xml.lettersanddigit_keyboard);

        initScreenParams(context);
        this.setLongClickable(false);

        this.setOnFocusChangeListener(new OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if (!hasFocus) {
                    hideKeyboard();
                }
            }
        });

    }

    private void initKeyboard(Context context, AttributeSet attrs) {

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.keyboard);

        if (a.hasValue(R.styleable.keyboard_xml)) {
            needcustomkeyboard = true;
            mKeyboardView = (KeyboardView) LayoutInflater.from(context)
                    .inflate(R.layout.mykeyboardview, null);

            mKeyboardView.setKeyboard(province_keyboard);
            mKeyboardView.setEnabled(true);
            mKeyboardView.setPreviewEnabled(false);
            mKeyboardView.setOnKeyboardActionListener(this);

            mKeyboardWindow = new PopupWindow(mKeyboardView,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            mKeyboardWindow.setAnimationStyle(R.style.AnimationFade);
            // mKeyboardWindow.setBackgroundDrawable(new BitmapDrawable());
            // mKeyboardWindow.setOutsideTouchable(true);
            mKeyboardWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

                @Override
                public void onDismiss() {
                    // TODO Auto-generated method stub
//                    if (scrolldis > 0) {
//                        int temp = scrolldis;
//                        scrolldis = 0;
//                        if (null != mContentView) {
//                            mContentView.scrollBy(0, -temp);
//                        }
//                    }
                }
            });
        } else {
            needcustomkeyboard = false;
        }

        a.recycle();

    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();

        this.mWindow = ((Activity) getContext()).getWindow();
        this.mDecorView = this.mWindow.getDecorView();
        this.mContentView = this.mWindow.findViewById(Window.ID_ANDROID_CONTENT);

        hideSysInput();
    }

    //    @TargetApi(11)
//    private void removeCopyAbility() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//            this.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
//
//                @Override
//                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
//                    return false;
//                }
//
//                @Override
//                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
//                    return false;
//                }
//
//                @Override
//                public void onDestroyActionMode(ActionMode mode) {
//
//                }
//
//                @Override
//                public boolean onActionItemClicked(ActionMode mode,
//                                                   MenuItem item) {
//                    return false;
//                }
//            });
//        }
//    }
    public void showKeyboard() {
        if (null != mKeyboardWindow) {
            if (!mKeyboardWindow.isShowing()) {
                currenPosition = 0;
                mLicense.delete(0, mLicense.length());
                plate_num1.setText("");
                plate_num2.setText("");
                plate_num3.setText("");
                plate_num4.setText("");
                plate_num5.setText("");
                plate_num6.setText("");
                plate_numX.setText("");
                plate_num7.setText("");
                mKeyboardView.setKeyboard(province_keyboard);

                if (isN) {
                    mKeyboardWindow.showAtLocation(this.mContentView, Gravity.BOTTOM,
                            0, mContentView.getBottom() - 410);
                } else {
                    mKeyboardWindow.showAtLocation(this.mContentView, Gravity.BOTTOM,
                            0, 0);
                }
                mKeyboardWindow.update();

//                if (null != mDecorView && null != mContentView) {
//                    int[] pos = new int[2];
//                    // 计算弹出的键盘的尺寸
//                    getLocationOnScreen(pos);
//                    float height = dpToPx(getContext(), 240);
//
//                    // int []hsmlpos=new int[2];
//                    // mDecorView.getLocationOnScreen(hsmlpos);
//
//                    Rect outRect = new Rect();
//                    // 然后该View有个getWindowVisibleDisplayFrame()方法可以获取到程序显示的区域，
//                    // * 包括标题栏，但不包括状态栏。
//                    mDecorView.getWindowVisibleDisplayFrame(outRect);// 获得view空间，也就是除掉标题栏
//                    // outRect.top表示状态栏（通知栏)
//                    int screen = real_scontenth;
//                    scrolldis = (int) ((pos[1] + getMeasuredHeight() - outRect.top) - (screen - height));
//
//                    if (scrolldis > 0) {
//                        mContentView.scrollBy(0, scrolldis);
//                    }
//                }

            } else {
                currenPosition = 0;
                mLicense.delete(0, mLicense.length());
                plate_num1.setText("");
                plate_num2.setText("");
                plate_num3.setText("");
                plate_num4.setText("");
                plate_num5.setText("");
                plate_num6.setText("");
                plate_numX.setText("");
                plate_num7.setText("");
                mKeyboardView.setKeyboard(province_keyboard);
                mKeyboardWindow.update();
            }
        }
    }

    public void hideKeyboard() {
        if (null != mKeyboardWindow) {
            if (mKeyboardWindow.isShowing()) {
                mKeyboardWindow.dismiss();
            }
        }
    }

    private void hideSysInput() {
        if (this.getWindowToken() != null) {
            InputMethodManager imm = (InputMethodManager) getContext()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(this.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void reShowKeyboard() {
        hideSysInput();
        showKeyboard();
    }

    /**
     * 密度转换为像素值
     *
     * @param dp
     * @return
     */
    public static int dpToPx(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {

    }

    private EditText getEditText() {
        switch (currenPosition) {
            case 0:
                return plate_num1;
            case 1:
                return plate_num2;
            case 2:
                return plate_num3;
            case 3:
                return plate_num4;
            case 4:
                return plate_num5;
            case 5:
                return plate_num6;
            case 6:
                return plate_numX;
            case 7:
                return plate_num7;
            default:
                return plate_num7;
        }
    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        // TODO Auto-generated method stub
        EditText editText = getEditText();
        if (primaryCode == 112) { //删除key

            if (currenPosition > 0) {
                currenPosition--;
            }
            if (!isNewEnergyCar && currenPosition == 6) {
                currenPosition--;
            }
            getEditText().setText("");
            mLicense.deleteCharAt(currenPosition);
            if (currenPosition == 0) {//如果当前为第一位则切换为省份键盘
                Log.d(TAG, "切换省份键盘");
                mKeyboardView.setKeyboard(province_keyboard);
            }

        } else if (primaryCode == 66) { //完成按钮
            //   hideKeyboard();
//            if (currenPosition<7) {
//                Toast.makeText(this.getContext(),"请输入完整的车牌", Toast.LENGTH_SHORT).show();
//            } else if (currenPosition==7) {
//                // TODO: 16/8/20 拼接车牌
//                hideKeyboard();
//                if (mKeyboardFinish!=null) {
//                    mKeyboardFinish.onKeyboardFinish();
//                }
//            }
            Toast.makeText(this.getContext(), "请输入完整的车牌", Toast.LENGTH_SHORT).show();
        } else {
            if (currenPosition == 0) {
                mKeyboardView.setKeyboard(alphanumeric_keyboard);
                currenPosition++;
                mLicense.append(provinceShort[primaryCode]);
                editText.setText(provinceShort[primaryCode]);
            } else if (currenPosition > 0 && currenPosition < 8) {
                if (!isNewEnergyCar && currenPosition == 5) {
                    currenPosition += 2;
                } else {
                    currenPosition++;
                }
                mLicense.append(alphanumeric[primaryCode]);
                editText.setText(alphanumeric[primaryCode]);

                if (currenPosition == 8) {
                    if (mKeyboardFinish != null) {
                        mKeyboardFinish.onKeyboardFinish();
                    }
                    hideKeyboard();
                }
            } else if (currenPosition == 8) {
                hideKeyboard();
//                mLicense.replace(currenPosition - 1, currenPosition, alphanumeric[primaryCode]);
//                editText.setText(alphanumeric[primaryCode]);
            }
        }
    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.plate_num1:
            case R.id.plate_num2:
            case R.id.plate_num3:
            case R.id.plate_num4:
            case R.id.plate_num5:
            case R.id.plate_num6:
            case R.id.plate_num7:
            case R.id.plate_numX:
                if (needcustomkeyboard) {
                    hideSysInput();
                    showKeyboard();
                }
                break;
        }
        return true;
    }

    private void initScreenParams(Context context) {
        DisplayMetrics dMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dMetrics);

        screenw = dMetrics.widthPixels;
        screenh = dMetrics.heightPixels;
        density = dMetrics.density;
        densityDpi = dMetrics.densityDpi;

        screenh_nonavbar = screenh;

        int ver = Build.VERSION.SDK_INT;

        // 新版本的android 系统有导航栏，造成无法正确获取高度
        if (ver == 13) {
            try {
                Method mt = display.getClass().getMethod("getRealHeight");
                screenh_nonavbar = (Integer) mt.invoke(display);
            } catch (Exception e) {
            }
        } else if (ver > 13) {
            try {
                Method mt = display.getClass().getMethod("getRawHeight");
                screenh_nonavbar = (Integer) mt.invoke(display);
            } catch (Exception e) {
            }
        }

        real_scontenth = screenh_nonavbar - getStatusBarHeight(context);

    }

    /**
     * 电量栏高度
     *
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return sbar;
    }

    public void setKeyboardFinishListener(KeyboardFinish keyboardFinish) {
        this.mKeyboardFinish = keyboardFinish;
    }

    public String getLicense() {
        return mLicense.toString();
    }

    public void changeNewEnergyCar(boolean isNewEnergyCar) {
        this.isNewEnergyCar = isNewEnergyCar;
        if (isNewEnergyCar) { //8位
            layoutX.setVisibility(VISIBLE);
        } else { //7位
            layoutX.setVisibility(GONE);
        }
    }

    public interface KeyboardFinish {
        void onKeyboardFinish();
    }
}

