package cn.campusapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StyleRes;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;

import cn.campusapp.dialog.holder.AbstractAnoleDialogHolder;
import cn.campusapp.dialog.holder.AnoleListDialogHolder;
import cn.campusapp.dialog.holder.AnoleTextDialogHolder;
import cn.campusapp.dialog.holder.BaseAnoleDialogHolder;
import timber.log.Timber;


/**
 * Created by kris on 15/10/8.
 */
public class AnoleAlertDialog extends AnoleDialog {


    protected AnoleAlertDialog(Context context) {
        super(context, R.style.Theme_dialog);
    }

    protected AnoleAlertDialog(Context context, @StyleRes int theme) {
        super(context, theme);
    }

    public static AnoleAlertDialog build(Context context, DialogType type) {
        AnoleAlertDialog dialog = new AnoleAlertDialog(context);
        dialog.mType = type;
        dialog.mHolder = initHolder(context, type);
        dialog.setContentView(dialog.mHolder.getRootView());
        dialog.initDialog(dialog);
        return dialog;
    }

    public static AnoleAlertDialog build(Context context, @StyleRes int theme, DialogType type) {
        AnoleAlertDialog dialog = new AnoleAlertDialog(context, theme);
        dialog.mType = type;
        dialog.mHolder = initHolder(context, type);
        dialog.setContentView(dialog.mHolder.getRootView());
        dialog.initDialog(dialog);
        return dialog;
    }

    public static AnoleAlertDialog build(Context context, View view) {
        AnoleAlertDialog dialog = new AnoleAlertDialog(context);
        dialog.mHolder = new BaseAnoleDialogHolder(view);
        dialog.mType = DialogType.USERDEFINED;
        dialog.setContentView(dialog.mHolder.getRootView());
        dialog.initDialog(dialog);
        return dialog;
    }

    public static AnoleAlertDialog build(Context context, @LayoutRes int layout) {
        AnoleAlertDialog dialog = new AnoleAlertDialog(context);
        dialog.mHolder = new BaseAnoleDialogHolder(context, layout);
        dialog.mType = DialogType.USERDEFINED;
        dialog.setContentView(dialog.mHolder.getRootView());
        dialog.initDialog(dialog);
        return dialog;
    }

    protected static void initDialog(AnoleAlertDialog dialog) {
        dialog.setDialogWidth(ViewGroup.LayoutParams.MATCH_PARENT);
    }

    private static BaseAnoleDialogHolder initHolder(Context context, DialogType type) {
        BaseAnoleDialogHolder holder = null;
        switch (type) {
            case TEXT:
                holder = new AnoleTextDialogHolder(context);
                break;
            case LIST:
                holder = new AnoleListDialogHolder(context);
                break;
            default:
                holder = new AnoleTextDialogHolder(context);
        }
        return holder;
    }

    /**
     * 设置View的宽度，默认View左右各有30的Margin所以即使设置全MatchParent 还是会有左右30dp的空余
     *
     * @param widthPx
     */
    public void setDialogWidth(int widthPx) {
        try {
            Window window = getWindow();
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.width = widthPx;
            getWindow().setAttributes(layoutParams);
        } catch (Throwable t) {
            Timber.wtf(t, "wtf");
        }
    }

    /**
     * 设置弹出框的标题
     *
     * @param title
     * @return
     */
    public AnoleAlertDialog setTitle(String title) {
        ((AbstractAnoleDialogHolder) mHolder).setTitle(title);
        return this;
    }

    public AnoleAlertDialog setTitleSize(float sizeSp) {
        ((AbstractAnoleDialogHolder) mHolder).setTitleSize(sizeSp);
        return this;
    }


    /**
     * 类型为TEXT的AlertDialog设置中间文字的内容
     *
     * @return
     */
    public AnoleAlertDialog setText(String text) {
        if (mType == DialogType.TEXT) {
            ((AnoleTextDialogHolder) mHolder).setText(text);
        }
        return this;
    }

    /**
     * 类型为TEXT的AlertDialog设置
     */
    public AnoleAlertDialog setHtml(String html) {
        if (mType == DialogType.TEXT) {
            ((AnoleTextDialogHolder) mHolder).setHtml(html);
        }
        return this;
    }


    /**
     * 设置左边按钮以及点击事件
     */
    public AnoleAlertDialog setLeftBtn(String text, final OnAnoleBtnClickListener listener) {
        ((AbstractAnoleDialogHolder) mHolder).setLeftBtn(text, new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onClick(AnoleAlertDialog.this);
            }
        });
        return this;
    }

    /**
     * 设置右边的按钮以及点击事件
     */
    public AnoleAlertDialog setRightBtn(String text, final OnAnoleBtnClickListener listener) {
        ((AbstractAnoleDialogHolder) mHolder).setRightBtn(text, new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onClick(AnoleAlertDialog.this);
            }
        });
        return this;
    }


    @Override
    public AnoleAlertDialog setView(View view) {
        return (AnoleAlertDialog) super.setView(view);
    }

    @Override
    public AnoleAlertDialog setLayoutResId(@LayoutRes int resId) {
        return (AnoleAlertDialog) super.setLayoutResId(resId);
    }


    /**
     * backgroundColor设置的承载Dialog的容器的颜色，所以当Dialog的颜色不为透明且占满整个Dialog的时候这个方法设置之后的效果其实是看不出来的。
     * 但是如果是自定义View，而且View有部分还是透明的，则这个方法可以起作用。= =说了那么多，发现这个方法确实没什么用。
     *
     * @param colorId
     * @return
     */
    @Override
    public AnoleAlertDialog setBackgroundColor(@ColorRes int colorId) {
        return (AnoleAlertDialog) super.setBackgroundColor(colorId);
    }

    /**
     * 本来写这个方法是为了能够让Dialog的整个背景呈现一种颜色,但是发现Dialog设置的ConvertView被局限在一个比较小的框里面，并没有占满整个屏幕，
     * 而屏幕外围是黑色半透明的背景色。因此这个方法没有成功，需要改变思路。尝试二：getWindow().getDecorView.setBackground 表现与上述一致。需要进一步思考，怀疑只能通过改变dialog的theme来达到该方法的目的。
     *
     * @param colorId
     * @return
     */
    @Override
    public AnoleAlertDialog setOutsideColor(@ColorRes int colorId) {

        return (AnoleAlertDialog) super.setOutsideColor(colorId);

    }

    /**
     * 点击Dialog的外部区域是否消失
     *
     * @param isDismiss
     * @return
     */
    @Override
    public AnoleAlertDialog setTouchOutsideDismiss(boolean isDismiss) {
        return (AnoleAlertDialog) super.setTouchOutsideDismiss(isDismiss);
    }

    /**
     * 貌似没有成功，不知道什么原因，先不要用这个吧
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
    @Deprecated
    @Override
    public AnoleAlertDialog setAnimation(@StyleRes int animationStyle) {
        return (AnoleAlertDialog) super.setAnimation(animationStyle);
    }


    /**
     * 与setOutsideBackground 类似的问题，设置的Convertview只占整个屏幕的一部分，所以即使设置了左右margin也只是对于这部分ui来说，所以
     * 这里找不到好的解决方案，就先注释掉了。
     */
//    public AnoleAlertDialog setMarginLeftAndRight(int left, int right){
//        ((AbstractAnoleDialogHolder) mHolder).setHorizontalMargin(left, right);
//        return this;
//    }


    /**
     * 下面是一些AlertDialog特有的方法
     */
    public AnoleAlertDialog setHeaderView(View view) {
        ((AbstractAnoleDialogHolder) mHolder).setHeaderView(view);
        return this;
    }

    public AnoleAlertDialog setHeaderLayout(@LayoutRes int resId) {
        ((AbstractAnoleDialogHolder) mHolder).setHeaderLayout(resId);
        return this;
    }


    public AnoleAlertDialog setMiddleView(View view) {
        ((AbstractAnoleDialogHolder) mHolder).setMiddleView(view);
        return this;
    }


    public AnoleAlertDialog setMiddleLayout(@LayoutRes int resId) {
        ((AbstractAnoleDialogHolder) mHolder).setMiddleLayout(resId);
        return this;
    }

    public AnoleAlertDialog setFootView(View view) {
        ((AbstractAnoleDialogHolder) mHolder).setFootView(view);
        return this;
    }

    public AnoleAlertDialog setFootLayout(@LayoutRes int resId) {
        ((AbstractAnoleDialogHolder) mHolder).setFootLayout(resId);
        return this;
    }


    public AnoleAlertDialog setTitleColor(@ColorRes int colorId) {
        ((AbstractAnoleDialogHolder) mHolder).setTitleColor(colorId);
        return this;
    }


    public AnoleAlertDialog setHeaderBackground(@ColorRes int colorId) {
        ((AbstractAnoleDialogHolder) mHolder).setHeaderBackground(colorId);
        return this;
    }

    public AnoleAlertDialog setHeaderHeight(int height) {
        ((AbstractAnoleDialogHolder) mHolder).setHeaderHeight(height);
        return this;
    }

    public AnoleAlertDialog setMiddleBackground(@ColorRes int colorId) {
        ((AbstractAnoleDialogHolder) mHolder).setMiddleBackground(colorId);
        return this;
    }

    public AnoleAlertDialog setMiddleHeight(int height) {
        ((AbstractAnoleDialogHolder) mHolder).setMiddleHeight(height);
        return this;
    }


    /**
     * 慎用
     *
     * @param colorId
     * @return
     */
    public AnoleAlertDialog setFootBackground(@ColorRes int colorId) {
        ((AbstractAnoleDialogHolder) mHolder).setFootBackground(colorId);
        return this;
    }

    /**
     * 慎用
     *
     * @param height
     * @return
     */
    public AnoleAlertDialog setFootHeight(int height) {
        ((AbstractAnoleDialogHolder) mHolder).setFootHeight(height);
        return this;
    }

    public AnoleAlertDialog setTextColor(@ColorRes int colorId) {
        if (mType == DialogType.TEXT)
            ((AnoleTextDialogHolder) mHolder).setTextColor(colorId, false);
        return this;
    }

    public AnoleAlertDialog setTextColorState(@ColorRes int colorId){
        if (mType == DialogType.TEXT)
            ((AnoleTextDialogHolder) mHolder).setTextColor(colorId, true);
        return this;
    }

    public AnoleAlertDialog setTextSize(int sizeSp) {
        if (mType == DialogType.TEXT)
            ((AnoleTextDialogHolder) mHolder).setTextSize(sizeSp);
        return this;
    }

    public AnoleAlertDialog setLeftBtnTextColor(@ColorRes int colorId, boolean colorState) {
        ((AbstractAnoleDialogHolder) mHolder).setLeftBtnTextColor(colorId, colorState);
        return this;
    }

    public AnoleAlertDialog setRightBtnTextColor(@ColorRes int colorId, boolean colorState) {
        ((AbstractAnoleDialogHolder) mHolder).setRightBtnTextColor(colorId, colorState);
        return this;
    }


    public AnoleAlertDialog setLeftBtnTextColor(@ColorRes int colorId) {
        return setLeftBtnTextColor(colorId, false);
    }

    public AnoleAlertDialog setRightBtnTextColor(@ColorRes int colorId) {
        return setRightBtnTextColor(colorId, false);
    }


    public AnoleAlertDialog setAdapter(BaseAdapter adapter) {
        if (mType == DialogType.LIST)
            ((AnoleListDialogHolder) mHolder).setAdapter(adapter);
        return this;
    }


    public AnoleAlertDialog setItems(String[] items, AnoleListDialogHolder.OnAnoleDialogItemClickLisener<String> lisener) {
        if (mType == DialogType.LIST)
            ((AnoleListDialogHolder) mHolder).setItems(this, items, lisener);
        return this;
    }


    public <T> AnoleAlertDialog setOnListItemClickListener(AnoleListDialogHolder.OnAnoleDialogItemClickLisener<T> listener) {
        if (mType == DialogType.LIST)
            ((AnoleListDialogHolder) mHolder).setOnItemClickListener(this, listener);
        return this;
    }


    public BaseAnoleDialogHolder getHolder() {
        return mHolder;
    }


    public interface OnAnoleBtnClickListener {
        void onClick(Dialog dialog);
    }

    public AnoleAlertDialog setDialogCancelable(boolean cancelable){
        super.setDialogCancelable(cancelable);
        return this;
    }
}
