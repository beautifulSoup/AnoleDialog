package cn.campusapp.dialog;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StyleRes;
import android.view.View;

import cn.campusapp.dialog.holder.AnoleProgressDialogHolder;
import timber.log.Timber;

/**
 * buildd by kris on 15/9/30.
 */
public class AnoleProgressDialog extends AnoleDialog {

    boolean isUserDefinedView = false;   //是否为用户自定义View 的ProgressDialog

    private AnoleProgressDialog(Context context) {
        super(context);
        mType = DialogType.PROGRESS;
    }

    private AnoleProgressDialog(Context context, @StyleRes int theme) {
        super(context, theme);
        mType = DialogType.PROGRESS;
    }

    public static AnoleProgressDialog build(Context context) {
        return build(context, null);
    }

    public static AnoleProgressDialog build(Context context,@StyleRes int theme) {
        return build(context, theme, null);
    }


    public static AnoleProgressDialog build(Context context, AnoleProgressDialogHolder.ProgressType type){
        return build(context, type, null);
    }

    public static AnoleProgressDialog build(Context context, int theme, AnoleProgressDialogHolder.ProgressType type) {
        return build(context, theme, type, null);
    }



    public static AnoleProgressDialog build(Context context, AnoleProgressDialogHolder.ProgressType type, int [] colorArray){
        AnoleProgressDialog dialog = new AnoleProgressDialog(context);
        dialog.mHolder = new AnoleProgressDialogHolder(context, type, colorArray);
        dialog.setContentView(dialog.mHolder.getRootView());
        return dialog;
    }

    public static AnoleProgressDialog build(Context context, int theme, AnoleProgressDialogHolder.ProgressType type, int[] colorArray) {
        AnoleProgressDialog dialog = new AnoleProgressDialog(context, theme);
        dialog.mHolder = new AnoleProgressDialogHolder(context, type, colorArray);
        dialog.setContentView(dialog.mHolder.getRootView());
        return dialog;
    }



    public static AnoleProgressDialog build(@LayoutRes int layout, Context context){
        AnoleProgressDialog dialog = new AnoleProgressDialog(context);
        dialog.mHolder = new AnoleProgressDialogHolder(context, layout);
        dialog.setContentView(dialog.mHolder.getRootView());
        dialog.isUserDefinedView = true;
        return dialog;
    }

    public static AnoleProgressDialog build(View view, Context context){
        AnoleProgressDialog dialog = new AnoleProgressDialog(context);
        dialog.mHolder = new AnoleProgressDialogHolder(context, view);
        dialog.setContentView(dialog.mHolder.getRootView());
        dialog.isUserDefinedView = true;
        return dialog;
    }





    /**
     *
     * 设置内部动画内容的宽高
     * 如果是自定义的view，则无法控制高宽
     * @param height
     * @param width
     */
    public AnoleProgressDialog setInsideDimension(int height, int width){
        if(!isUserDefinedView)
            getProgressHolder().setProgressDimension(height, width);
        else
            Timber.e("该ProgressDialog使用了自定义的View，无法控制其宽高，请在xml中控制");
        return this;
    }


    /**
     * 设置整个dialog的宽高
     * @param height
     * @param width
     * @return
     */
    public AnoleProgressDialog setDialogDimension(int height, int width){
        if(!isUserDefinedView)
            getProgressHolder().setProgressDialogDimension(height, width);
        else
            Timber.e("该ProgressDialog使用了自定义的View，无法控制其宽高，请在自定义的view中控制");
        return this;
    }

    public AnoleProgressDialog setHeight(int height){
        setInsideDimension(height, -1);
        return this;
    }

    public AnoleProgressDialog setWidth(int width){
        setInsideDimension(-1, width);
        return this;
    }


    public AnoleProgressDialogHolder getProgressHolder(){
        return (AnoleProgressDialogHolder) mHolder;
    }

    @Override
    public AnoleProgressDialog setView(View view){
        return (AnoleProgressDialog)super.setView(view);
    }

    @Override
    public AnoleProgressDialog setLayoutResId(@LayoutRes int resId){
        return (AnoleProgressDialog)super.setLayoutResId(resId);
    }

    @Override
    public AnoleProgressDialog setBackgroundColor(@ColorRes int colorId){
        return (AnoleProgressDialog) super.setBackgroundColor(colorId);
    }

    public AnoleProgressDialog setLoadingText(String text){
        getProgressHolder().setLoadingText(text);
        return this;
    }

    /**
     *
     * @param colorId
     * @return
     */
    @Deprecated
    @Override
    public AnoleProgressDialog setOutsideColor(@ColorRes int colorId){
        return (AnoleProgressDialog) super.setOutsideColor(colorId);
    }

    @Override
    public AnoleProgressDialog setTouchOutsideDismiss(boolean isDismiss){
        return (AnoleProgressDialog) super.setTouchOutsideDismiss(isDismiss);
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
     * @param animationStyle
     * @return
     */
    @Deprecated
    @Override
    public AnoleProgressDialog setAnimation(@StyleRes int animationStyle){
        return (AnoleProgressDialog) super.setAnimation(animationStyle);
    }

    /**
     * @param type
     */
    public AnoleProgressDialog setType(AnoleProgressDialogHolder.ProgressType type){
        getProgressHolder().setType(type);
        return this;
    }


    /**
     * @param type
     * @param colorArray
     */
    @Deprecated
    public AnoleProgressDialog setTypeAndColor(AnoleProgressDialogHolder.ProgressType type, int[] colorArray){
        getProgressHolder().setTypeAndColor(type, colorArray);
        return this;
    }

    public AnoleProgressDialog setDialogCancelable(boolean cancelable){
        super.setDialogCancelable(cancelable);
        return this;
    }
}
