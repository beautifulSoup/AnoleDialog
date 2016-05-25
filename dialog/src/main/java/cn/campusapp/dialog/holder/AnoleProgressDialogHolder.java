package cn.campusapp.dialog.holder;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jpardogo.android.googleprogressbar.library.ChromeFloatingCirclesDrawable;
import com.jpardogo.android.googleprogressbar.library.FoldingCirclesDrawable;
import com.jpardogo.android.googleprogressbar.library.GoogleMusicDicesDrawable;
import com.jpardogo.android.googleprogressbar.library.NexusRotationCrossDrawable;

import cn.campusapp.dialog.R;
import timber.log.Timber;

/**
 * Created by kris on 15/9/29.
 */
public class AnoleProgressDialogHolder extends BaseAnoleDialogHolder {

    ViewGroup mProgressDialogLayout;  //放置progressDialog的layout；
    ProgressBar mProgressBar;
    ProgressType mType;


    /**
     * 该构造函数可以用来设置ProgressDialog的样式
     *
     * @param context
     * @param type
     */
    public AnoleProgressDialogHolder(Context context, ProgressType type) {
        this(context, type, null);
    }


    public AnoleProgressDialogHolder(Context context, ProgressType type, int[] colorArray) {
        super(context);
        mRootView = LayoutInflater.from(mContext).inflate(R.layout.dialog_anole_progress, null);
        //将GoogleProgressDialog放到到mRootView中
        mProgressDialogLayout = (ViewGroup) mRootView.findViewById(R.id.dialog_anole_layout);
        if (type == null)
            type = ProgressType.DEFAULT;
        mType = type;
        if (type == ProgressType.DEFAULT) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_progress, null);
            final ImageView progressIv = (ImageView) view.findViewById(R.id.progress_iv);
            AnimationDrawable drawable = getDefaultDrawable();
            if(android.os.Build.VERSION.SDK_INT >= 16){
                progressIv.setBackground(drawable);
            } else {
                progressIv.setBackgroundDrawable(drawable);
            }
            if(drawable != null && !drawable.isRunning()){
                drawable.start();
            }
            mProgressDialogLayout.addView(view);
        } else {
            Drawable drawable = getProgressDrawable(mContext, type, colorArray);
            mProgressBar = new ProgressBar(mContext);
            mProgressBar.setIndeterminateDrawable(drawable);
            //设置ProgressBar在Dialog中居中
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            lp.addRule(RelativeLayout.CENTER_IN_PARENT);
            mProgressBar.setLayoutParams(lp);
            mProgressDialogLayout.addView(mProgressBar);
        }
    }


    /**
     * 返回自定义view的holder
     */
    public AnoleProgressDialogHolder(Context context, @LayoutRes int layout) {
        super(context);
        mRootView = LayoutInflater.from(mContext).inflate(R.layout.dialog_anole_progress, null);
        //将GoogleProgressDialog放到到mRootView中
        mProgressDialogLayout = (ViewGroup) mRootView.findViewById(R.id.dialog_anole_layout);
        View view = LayoutInflater.from(context).inflate(layout, null);
        mProgressDialogLayout.addView(view);
    }

    public AnoleProgressDialogHolder(Context context, View view) {
        super(context);
        mRootView = LayoutInflater.from(mContext).inflate(R.layout.dialog_anole_progress, null);
        //将GoogleProgressDialog放到到mRootView中
        mProgressDialogLayout = (ViewGroup) mRootView.findViewById(R.id.dialog_anole_layout);
        mProgressDialogLayout.addView(view);
    }

    /**
     * 返回自定义view的holder
     *
     * @param context
     * @param type
     * @param colors
     * @return
     */
    Drawable getProgressDrawable(Context context, ProgressType type, int[] colors) {
        if (colors != null)
            Timber.i("Colors length:" + colors.length);

        switch (type) {
            case FoldingCircles:
                if (colors == null)
                    return new FoldingCirclesDrawable.Builder(context).build();
                else
                    return new FoldingCirclesDrawable.Builder(context).colors(colors).build();
            case GoogleMusicDices:
                return new GoogleMusicDicesDrawable.Builder().build();
            case NexusRotationCross:
                if (colors == null)
                    return new NexusRotationCrossDrawable.Builder(context).build();
                else
                    return new NexusRotationCrossDrawable.Builder(context).colors(colors).build();
            case ChromeFloating:
                if (colors == null)
                    return new ChromeFloatingCirclesDrawable.Builder(context).build();
                else
                    return new ChromeFloatingCirclesDrawable.Builder(context).colors(colors).build();
        }

        return new FoldingCirclesDrawable.Builder(context).build();
    }

    /**
     * 设置该ProgressDialog的类型
     *
     * @param type
     */
    public void setType(@Nullable ProgressType type) {
        setTypeAndColor(type, null);
    }

    @TargetApi(16)
    public void setTypeAndColor(@Nullable ProgressType type, @Nullable int[] colors) {
        if (type == null)
            type = ProgressType.DEFAULT;
        mType = type;
        mProgressDialogLayout.removeAllViews();
        if (type == ProgressType.DEFAULT) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_progress, null);
            final ImageView progressIv = (ImageView) view.findViewById(R.id.progress_iv);
            AnimationDrawable drawable = getDefaultDrawable();
            if(android.os.Build.VERSION.SDK_INT >= 16){
                progressIv.setBackground(drawable);
            } else {
                progressIv.setBackgroundDrawable(drawable);
            }
            if(drawable != null && !drawable.isRunning()){
                drawable.start();
            }
            mProgressDialogLayout.addView(view);
        } else {
            Drawable drawable = getProgressDrawable(mContext, type, colors);
            mProgressBar = new ProgressBar(mContext);
            mProgressBar.setIndeterminateDrawable(drawable);
            //设置ProgressBar在Dialog中居中
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            lp.addRule(RelativeLayout.CENTER_IN_PARENT);
            mProgressBar.setLayoutParams(lp);
            mProgressDialogLayout.addView(mProgressBar);
        }
    }

    protected AnimationDrawable getDefaultDrawable(){
        AnimationDrawable mAnimation = new AnimationDrawable();
        mAnimation.addFrame(getDrawable(R.drawable.progress_1),100);
        mAnimation.addFrame(getDrawable(R.drawable.progress_2),100);
        mAnimation.addFrame(getDrawable(R.drawable.progress_3),100);
        mAnimation.addFrame(getDrawable(R.drawable.progress_4),100);
        mAnimation.addFrame(getDrawable(R.drawable.progress_5),100);
        mAnimation.addFrame(getDrawable(R.drawable.progress_6), 100);
        mAnimation.setOneShot(false);
        return mAnimation;
    }


    @TargetApi(21)
    protected Drawable getDrawable(@DrawableRes int drawRes){
        if(android.os.Build.VERSION.SDK_INT >= 21){
            return mContext.getResources().getDrawable(drawRes, null);
        } else {
            return mContext.getResources().getDrawable(drawRes);
        }
    }
    /**
     * 设置ProgressDialog的宽高尺寸
     *
     * @param height -1 keep the default height
     * @param width  -1 keep the default width
     */
    public void setProgressDimension(int height, int width) {
        if (mType != ProgressType.DEFAULT && mProgressBar != null) {
            ViewGroup.LayoutParams lp = mProgressBar.getLayoutParams();
            if (height != -1)
                lp.height = height;
            if (width != -1)
                lp.width = width;
        } else {
            Timber.e("当前类型不支持设置大小");
        }
    }

    public void setProgressDialogDimension(int height, int width) {
        ViewGroup.LayoutParams lp = mProgressDialogLayout.getLayoutParams();
        if (height != -1)
            lp.height = height;
        if (width != -1)
            lp.width = width;
    }


    public void setLoadingText(String text){
        if(mType != ProgressType.DEFAULT){
            Timber.e("当前类型不支持设置加载文字");
        } else {
            TextView tv = (TextView) mRootView.findViewById(R.id.progress_text);
            tv.setText(text);
        }
    }

    /**
     * 四种Progress的样式，参考GoogleProgressBar
     */
    public enum ProgressType {
        FoldingCircles,
        GoogleMusicDices,
        NexusRotationCross,
        ChromeFloating,
        DEFAULT;

        public static ProgressType getValueOf(int i) {
            switch (i) {
                case 0:
                    return FoldingCircles;
                case 1:
                    return GoogleMusicDices;
                case 2:
                    return NexusRotationCross;
                case 3:
                    return ChromeFloating;
                case 4:
                    return DEFAULT;

            }
            return DEFAULT;
        }
    }





}
