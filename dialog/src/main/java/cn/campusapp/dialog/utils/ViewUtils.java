package cn.campusapp.dialog.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import timber.log.Timber;

/**
 * 与 View 相关的支持
 * <p/>
 * Created by nius on 5/22/15.
 */
public class ViewUtils {

    private static final String TAG = "VIEWUTIL";
    /**
     * 由于不用横屏，所以可以重复使用
     */
    static Point WINDOW_SIZE = null;
    private static String PRIMARY_COLOR_HEX = null;

    /**
     * @param b 如果是真，则显示
     */
    public static void toggle(boolean b, View... vs) {
        if (b) {
            show(vs);
        } else {
            gone(vs);
        }
    }

    public static void show(View... vs) {
        for (View v : vs) {
            if (v != null) {
                v.setVisibility(View.VISIBLE);
            } else {
                Timber.tag(TAG).w("SHOW VIEW FAIL");
            }
        }
    }

    public static void invisible(View... vs) {
        for (View v : vs) {
            if (null != v) {
                v.setVisibility(View.INVISIBLE);
            } else {
                Timber.tag(TAG).w("HIDE VIEW FAIL");
            }
        }
    }

    public static void gone(View... vs) {
        for (View v : vs) {
            if (v != null) {
                v.setVisibility(View.GONE);
            } else {
                Timber.tag(TAG).w("HIDE VIEW FAIL");
            }
        }
    }

    public static void showDialog(Dialog dialog) {
        try {
            if (dialog != null && !dialog.isShowing()) {
                dialog.show();
            }
        } catch (Exception e) {
            Timber.tag(TAG).e(e, "showDialog: error");
        }
    }

    public static void cancelDialog(Dialog dialog) {
        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.cancel();
            }
        } catch (Exception e) {
            Timber.tag(TAG).e(e, e.getMessage());
        }
    }


    public static void textColor(TextView v, int color) {
        if (v != null) {
            v.setTextColor(color);
        } else {
            Timber.w("v is null");
        }
    }

    public static void textColorState(TextView v, ColorStateList colorList) {
        if (v != null) {
            v.setTextColor(colorList);
        } else {
            Timber.w("v is null");
        }
    }


    /**
     * 设置一个TextView 的字体大小,以sp为单位
     *
     * @param v        被修改的 TextView 对象
     * @param textSize 字体大小(sp)
     */
    public static void textSize(TextView v, float textSize) {
        if (v != null) {
            try {
                v.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
            } catch (Exception e) {
                Timber.tag(TAG).e(e, e.getMessage());
            }
        }
    }

    public static void enable(View v) {
        if (v != null) {
            v.setEnabled(true);
        } else {
            Timber.w("v is null");
        }
    }

    public static void disable(View v) {
        if (v != null) {
            v.setEnabled(false);
        } else {
            Timber.w("v is null");
        }
    }

    public static void text(@Nullable TextView v, @NonNull CharSequence text) {
        if (v != null) {
            v.setText(text);
        } else {
            Timber.w("v is null");
        }
    }


    public static void html(TextView v, String html) {
        text(v, Html.fromHtml(html));
    }


    public static void longClick(View v, View.OnLongClickListener listener) {
        if (v == null || listener == null) {
            Timber.tag(TAG).w("BIND LONG CLICK FAIL");
            return;
        }

        v.setOnLongClickListener(listener);
    }

    public static void longClick(View.OnLongClickListener listener, View... vs) {
        if (null == vs || vs.length == 0 || null == listener) {
            Timber.tag(TAG).w("BIND CLICK FAIL");
            return;
        }
        for (View v : vs) {
            if (null != v) {
                v.setOnLongClickListener(listener);
            }
        }
    }

    public static void click(View v, View.OnClickListener listener) {
        if (v == null || listener == null) {
            Timber.tag(TAG).w("BIND CLICK FAIL");
            return;
        }
        v.setOnClickListener(listener);
    }

    public static void click(View.OnClickListener listener, View... vs) {
        if (null == vs || vs.length == 0 || null == listener) {
            Timber.tag(TAG).w("BIND CLICK FAIL");
            return;
        }
        for (View v : vs) {
            if (null != v)
                v.setOnClickListener(listener);
        }
    }

    public static void touch(View v, View.OnTouchListener listener) {
        if (v == null || listener == null) {
            Timber.tag(TAG).w("BIND TOUCH FAIL");
            return;
        }
        v.setOnTouchListener(listener);
    }

    public static void touch(View.OnTouchListener listener, View... vs) {
        if (vs == null || null == listener) {
            Timber.tag(TAG).w("BIND TOUCH FAIL");
            return;
        }
        for (View v : vs) {
            if (null != v) {
                v.setOnTouchListener(listener);
            }
        }
    }


    /**
     * 开启软键盘
     * 有空转移到KeyboardManager的实现
     */
    public static void openSoftKeyboard(EditText et) {
        InputMethodManager inputManager = (InputMethodManager) et.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(et, 0);
    }

    /**
     * 关闭软键盘
     * 有空转移到KeyboardManager的实现
     */
    public static void closeSoftKeyboard(Activity context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && context.getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(context.getCurrentFocus()
                    .getWindowToken(), 0);
        }
    }

    public static void height(View v, int height) {
        ViewGroup.LayoutParams p = v.getLayoutParams();
        p.height = height;
        v.setLayoutParams(p);
    }

    public static void width(View v, int width) {
        ViewGroup.LayoutParams p = v.getLayoutParams();
        p.width = width;
        v.setLayoutParams(p);
    }

    public static void gravity(TextView v, int... gravities) {
        if (v != null && gravities.length > 0) {
            int finalGravity = gravities[0];
            for (int i = 1; i < gravities.length; i++) {
                finalGravity |= gravities[i];
            }
            v.setGravity(finalGravity);
        }
    }


    public static void background(View v, Drawable drawable) {
        int pTop = v.getPaddingTop();
        int pBottom = v.getPaddingBottom();
        int pLeft = v.getPaddingLeft();
        int pRight = v.getPaddingRight();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            v.setBackground(drawable);
        } else {
            v.setBackgroundDrawable(drawable);
        }
        v.setPadding(pLeft, pTop, pRight, pBottom);
    }

    public static void backgroundColor(View v, int color) {
        v.setBackgroundColor(color);
    }

    @TargetApi(23)
    public static int getColor(Context context, @ColorRes int colorId) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getColor(colorId);
        } else {
            return context.getResources().getColor(colorId);
        }
    }

    @TargetApi(23)
    public static ColorStateList getColorState(Context context, @ColorRes int colorId) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getColorStateList(colorId);
        } else {
            return context.getResources().getColorStateList(colorId);
        }
    }

}
