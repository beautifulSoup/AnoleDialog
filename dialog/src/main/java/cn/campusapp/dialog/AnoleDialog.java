package cn.campusapp.dialog;


import android.app.Dialog;
import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StyleRes;
import android.view.View;
import android.view.Window;

import cn.campusapp.dialog.holder.BaseAnoleDialogHolder;


/**
 * 一个高度可定制的Dialog
 * Created by kris on 15/9/29.
 */
public abstract class AnoleDialog extends Dialog {
    Context mContext;
    DialogType mType;
    BaseAnoleDialogHolder mHolder;

    protected AnoleDialog(Context context) {
        super(context, R.style.Theme_dialog);
        mContext = context;
    }

    protected AnoleDialog(Context context, @StyleRes int theme) {
        super(context, theme);
        mContext = context;
    }

    public View getView(int resId) {
        return mHolder.getViewFromRoot(resId);
    }

    public AnoleDialog setView(View view) {
        mType = DialogType.USERDEFINED;
        mHolder.setView(view);
        return this;
    }

    public AnoleDialog setLayoutResId(@LayoutRes int resId) {
        mType = DialogType.USERDEFINED;
        mHolder.setLayoutResId(mContext, resId);
        return this;
    }

    public AnoleDialog setBackgroundColor(@ColorRes int colorId) {
        mHolder.setBackgroundColor(colorId);
        return this;
    }

    @Deprecated
    public AnoleDialog setOutsideColor(@ColorRes int colorId) {
        getWindow().getDecorView().setBackgroundColor(mContext.getResources().getColor(colorId));
        return this;
    }

    public AnoleDialog setTouchOutsideDismiss(boolean isDismiss) {
        setCanceledOnTouchOutside(isDismiss);
        return this;
    }

    /**
     * 设置弹出和隐去的动画
     * 示例：
     * <?xml version="1.0" encoding="utf-8"?>
     * <resources>
     * <style
     * name="toast_anim"
     * parent="@android:style/Animation.Dialog"
     * mce_bogus="1">
     * <item name="android:windowEnterAnimation">@anim/dialog_enter_action</item>
     * <item name="android:windowExitAnimation">@anim/dialog_enter_action</item>
     * </style>
     * </resources>
     *
     * @param animationStyle
     * @return
     */
    public AnoleDialog setAnimation(@StyleRes int animationStyle) {
        Window window = getWindow();
        window.setWindowAnimations(animationStyle);
        return this;
    }

    public enum DialogType {
        TEXT,
        LIST,
        //        GRID,
        PROGRESS,
        USERDEFINED;
    }

    public AnoleDialog setDialogCancelable(boolean calcelable){
        setCancelable(calcelable);
        return this;
    }

}
