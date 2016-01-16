package cn.campusapp.dialog.holder;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.campusapp.dialog.R;

import static cn.campusapp.dialog.utils.ViewUtils.backgroundColor;
import static cn.campusapp.dialog.utils.ViewUtils.getColor;
import static cn.campusapp.dialog.utils.ViewUtils.getColorState;
import static cn.campusapp.dialog.utils.ViewUtils.gone;
import static cn.campusapp.dialog.utils.ViewUtils.height;
import static cn.campusapp.dialog.utils.ViewUtils.show;
import static cn.campusapp.dialog.utils.ViewUtils.text;
import static cn.campusapp.dialog.utils.ViewUtils.textColor;
import static cn.campusapp.dialog.utils.ViewUtils.textColorState;
import static cn.campusapp.dialog.utils.ViewUtils.textSize;


/**
 * Created by kris on 15/9/29.
 * 默认的Dialog的样式，包括header middle 和 bottom
 * 最好不要直接实例化这个类，如果要对三部分view都做自定义，则可以直接使用AnoleDialogHolder对整个Dialog中的View都做自定义
 * 如果需要对某一部分做自定义，则可以选择使用它的三个子类中的任意一个
 */
public class AbstractAnoleDialogHolder extends BaseAnoleDialogHolder {
    RelativeLayout mHeaderGroup;
    RelativeLayout mMiddleGroup;
    RelativeLayout mFootGroup;

    public ViewGroup getHeaderGroup(){
        return mHeaderGroup;
    }

    public ViewGroup getMiddleGroup(){
        return mMiddleGroup;
    }

    public ViewGroup getFootGroup(){
        return mFootGroup;
    }

    ViewGroup mContainerLayout;

    HeaderViewHolder mHeaderHolder = new HeaderViewHolder();
    FooterViewHolder mFootHolder = new FooterViewHolder();

    boolean isLeftBtnShow = false;
    boolean isRightBtnShow = false;

    public AbstractAnoleDialogHolder(Context context){
        super(context);
        init(context);
        View headView = LayoutInflater.from(context).inflate(R.layout.dialog_anole_head, null);
        View footView = LayoutInflater.from(context).inflate(R.layout.dialog_anole_foot, null);
        mHeaderGroup.addView(headView);
        mFootGroup.addView(footView);
    }



    public void init(Context context){
        mRootView = LayoutInflater.from(context).inflate(R.layout.dialog_anole, null);
        mContainerLayout = (LinearLayout) mRootView.findViewById(R.id.dialog_anole_layout);
        mHeaderGroup = (RelativeLayout) mContainerLayout.findViewById(R.id.dialog_anole_head);
        mMiddleGroup = (RelativeLayout) mContainerLayout.findViewById(R.id.dialog_anole_content);
        mFootGroup = (RelativeLayout) mContainerLayout.findViewById(R.id.dialog_anole_foot);
    }


    public void setHeaderView(View headerView){
        mHeaderGroup.removeAllViews();
        mHeaderGroup.addView(headerView);
    }

    public void setHeaderLayout(@LayoutRes int resId){
        mHeaderGroup.removeAllViews();
        View view = LayoutInflater.from(mContext).inflate(resId, null);
        mHeaderGroup.addView(view);
    }

    public void setMiddleView(View middleView){
        mMiddleGroup.removeAllViews();
        mMiddleGroup.addView(middleView);
    }

    public void setMiddleLayout(@LayoutRes int resId){
        mMiddleGroup.removeAllViews();
        View view = LayoutInflater.from(mContext).inflate(resId, null);
        mMiddleGroup.addView(view);
    }

    public void setFootView(View footView){
        mFootGroup.removeAllViews();
        mFootGroup.addView(footView);
    }

    public void setFootLayout(@LayoutRes int resId){
        mFootGroup.removeAllViews();
        View view = LayoutInflater.from(mContext).inflate(resId, null);
        mFootGroup.addView(view);
    }

    /**
     * 只有在使用默认的head的时候才使用该方法，否则用了也没用
     * @param title
     */
    public void setTitle(String title){
        showHeader();
        TextView titleTv = mHeaderHolder.getTitleTv();
        if(titleTv != null)
            text(titleTv, title);
    }


    /**
     * 设置标题的字体颜色，同样只有使用默认heade时该方法才生效
     */
    public void setTitleColor(@ColorRes int colorId){
        TextView titleTv = mHeaderHolder.getTitleTv();
        if(titleTv != null)
            textColor(titleTv, getColor(mContext, colorId));
    }

    public void setTitleSize(float sizeSp){
        TextView titleTv = mHeaderHolder.getTitleTv();
        if(titleTv != null)
            textSize(titleTv, sizeSp);

    }

    /**
     * 设置头部的背景颜色,默认为奇怪的蓝色
     */
    public void setHeaderBackground(@ColorRes int colorId){
        backgroundColor(mHeaderGroup, getColor(mContext, colorId));
    }

    /**
     * 设置头部高度
     */
    public void setHeaderHeight(int heightPx){
        height(mHeaderGroup, heightPx);
    }

    /**
     * 默认情况下是隐藏头部的，该方法显示头部
     */
    public void showHeader(){
        show(mHeaderGroup);
    }

  //---------- 以下为对middle部分的操作


    /**
     * 设置中间
     * @param colorId
     */
    public void setMiddleBackground(@ColorRes int colorId){
        backgroundColor(mMiddleGroup, getColor(mContext, colorId));
    }

    /**
     * 设置中间部分的高度
     * @param heightPx
     */
    public void setMiddleHeight(int heightPx){
        height(mMiddleGroup, heightPx);
    }


    //---------- 以下为对foot部分的操作  --------------


    /**
     * 隐去头部
     */
    public void goneHeader(){
        gone(mHeaderGroup);
    }

    /**
     *
     * @param heigthPx
     */
    public void setFootHeight(int heigthPx){
        height(mFootGroup, heigthPx);
    }

    /**
     *
     * @param colorId
     */
    public void setFootBackground(@ColorRes int colorId){
        backgroundColor(mFootGroup, getColor(mContext, colorId));
    }



    /**
     * 对第一个按钮的操作
     */
    public void setLeftBtn(@Nullable String text, View.OnClickListener click){
        show(mFootGroup);
        TextView leftBtn = mFootHolder.getLeftBtnTv();
        show(leftBtn);
        isLeftBtnShow = true;
        if(leftBtn != null){
            show(leftBtn);
            if(text != null)
                text(leftBtn, text);
            leftBtn.setOnClickListener(click);
        }

        if(isRightBtnShow){
            View vDivider = (View) mFootGroup.findViewById(R.id.v_divider);
            show(vDivider);
        }

    }

    /**
     * 设置第一个按钮文字的颜色
     */
    public void setLeftBtnTextColor(@ColorRes int colorId, boolean colorState){
        TextView leftBtn = mFootHolder.getLeftBtnTv();
        if(leftBtn != null) {
            if (colorState) {
                textColorState(leftBtn, getColorState(mContext, colorId));
            } else {
                textColor(leftBtn, getColor(mContext, colorId));
            }
        }
    }

    /**
     * 隐去第一个按钮
     */
    public void goneLeftBtn(){
        TextView leftBtn = mFootHolder.getLeftBtnTv();
        if(leftBtn != null){
            gone(leftBtn);
        }
    }

    /**
     * 对第二个按钮的操作
     */
    public void setRightBtn(@Nullable String text, View.OnClickListener click){
        show(mFootGroup);
        TextView rightBtn = mFootHolder.getRightBtnTv();
        show(rightBtn);
        isRightBtnShow = true;
        if(rightBtn != null){
            show(rightBtn);
            if(text != null){
                text(rightBtn, text);
            }
            rightBtn.setOnClickListener(click);
        }
        if(isLeftBtnShow){
            View view = mFootGroup.findViewById(R.id.v_divider);
            show(view);
        }
    }



    /**
     *
     * @param colorId
     * @param colorState
     */
    public void setRightBtnTextColor(@ColorRes int colorId, boolean colorState){
        TextView rightBtn = mFootHolder.getRightBtnTv();
        if(rightBtn != null) {
            if (colorState) {
                textColorState(rightBtn, getColorState(mContext, colorId));
            } else {
                textColor(rightBtn, getColor(mContext, colorId));
            }
        }
    }

    public void goneRightBtn(){
        TextView rightBtn = mFootHolder.getRightBtnTv();
        if(rightBtn != null)
            gone(rightBtn);

    }

    /**
     * 隐去竖直分割线
     */
    public void gonevDivider(){
        View v = mFootHolder.getvDivider();
        if(v!= null)
            v.setVisibility(View.GONE);
    }

    /**
     * 隐去水平分割线
     */
    public void gonehDivider(){
        View v = mFootHolder.gethDivider();
        if(v != null)
            gone(mFootGroup);
    }

    /**
     * 隐去底部按钮区域
     */
    public void goneFoot(){
        gone(mFootGroup);
    }


//    /**
//     * 设置Dialog的左右Margin
//     */
//    public void setHorizontalMargin(int left, int right){
//        ViewUtils.setHorizontalMargin(mRootView, left, right);
//    }

    class HeaderViewHolder{
        TextView mTitleTv;
        public @Nullable
        TextView getTitleTv(){
            if(mTitleTv == null){
                mTitleTv = (TextView) mHeaderGroup.findViewById(R.id.title_tv);
            }
            return mTitleTv;
        }
    }

    class FooterViewHolder{
        TextView mLeftBtnTv;
        TextView mRightBtnTv;
        View vDivider;
        View hDivider;
        public @Nullable
        TextView getLeftBtnTv(){
            if(mLeftBtnTv == null){
                mLeftBtnTv = (TextView) mFootGroup.findViewById(R.id.left_btn);
            }
            return mLeftBtnTv;
        }

        public @Nullable
        TextView getRightBtnTv(){
            if(mRightBtnTv == null){
                mRightBtnTv = (TextView) mFootGroup.findViewById(R.id.right_btn);
            }
            return mRightBtnTv;
        }

        public @Nullable
        View getvDivider(){
            if(vDivider == null){
                vDivider = mFootGroup.findViewById(R.id.v_divider);
            }
            return vDivider;
        }

        public @Nullable
        View gethDivider(){
            if(hDivider == null){
                hDivider = mFootGroup.findViewById(R.id.h_divider);
            }
            return hDivider;
        }


    }




    public interface MiddleViewHolder{
    }


}
