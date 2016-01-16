package cn.campusapp.dialog.holder;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import cn.campusapp.dialog.R;

import static cn.campusapp.dialog.utils.ViewUtils.backgroundColor;
import static cn.campusapp.dialog.utils.ViewUtils.getColor;


/**
 *
 * 这个Holder拥有默认的Dialog的整体布局的View
 * 提供接口将自定义的子View注入到该View中，如注入自定义的包含Title的View
 * Created by kris on 15/9/29.
 * 整个默认Dialog布局分为三部分，header，middle和foot
 * 可以分别对这三部分注入自己定义的view
 */
public class BaseAnoleDialogHolder {
    Context mContext;
    View mRootView;   //这个是最外面的View

    protected BaseAnoleDialogHolder(Context context){
        mContext = context;
    }


    public BaseAnoleDialogHolder(View view){
        this.mRootView = view;
    }

    public BaseAnoleDialogHolder(Context context, @LayoutRes int layout){
         mRootView = LayoutInflater.from(context).inflate(layout, null);
    }


    public View getRootView(){
        return mRootView;
    }

    public @Nullable
    View getViewFromRoot(@IdRes int viewId){
        View view = mRootView.findViewById(viewId);
        return view;
    }

    public void setView(View view){
        mRootView = view;
    }

    public void setLayoutResId(Context context, @LayoutRes int resId){
        View view = LayoutInflater.from(context).inflate(resId, null);
        mRootView = view;
    }

    public void setBackgroundColor(@ColorRes int colorId){
        View view = mRootView.findViewById(R.id.dialog_anole_layout);
        backgroundColor(view, getColor(mContext, colorId));
    }

    /**
     * 好像这个方法不起作用，Dialog会默认加上一个黑色半透明的背景色
     * @param colorId
     */
    @Deprecated
    public void setOutsideColor(@ColorRes int colorId){
        backgroundColor(mRootView, getColor(mContext, colorId));
    }


}
