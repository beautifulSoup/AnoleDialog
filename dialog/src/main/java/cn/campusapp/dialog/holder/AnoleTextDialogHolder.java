package cn.campusapp.dialog.holder;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import cn.campusapp.dialog.R;

import static cn.campusapp.dialog.utils.ViewUtils.getColor;
import static cn.campusapp.dialog.utils.ViewUtils.getColorState;
import static cn.campusapp.dialog.utils.ViewUtils.text;
import static cn.campusapp.dialog.utils.ViewUtils.textColor;
import static cn.campusapp.dialog.utils.ViewUtils.textColorState;
import static cn.campusapp.dialog.utils.ViewUtils.textSize;


/**
 * 中间部分为文字的dialog holder
 * Created by kris on 15/9/30.
 */
public class AnoleTextDialogHolder extends AbstractAnoleDialogHolder {

    String mText;


    TextMiddleViewHolder mMiddleViewHolder = new TextMiddleViewHolder();


    public AnoleTextDialogHolder(Context context) {
        super(context);
        View middleView = LayoutInflater.from(context).inflate(R.layout.dialog_anole_middle_text, null);
        mMiddleGroup.addView(middleView);
    }

    public void setText(String text) {
        TextView contentTv = mMiddleViewHolder.getContentTv();
        if (contentTv != null)
            text(contentTv, text);
    }

    public void setHtml(String html) {
        TextView contentTv = mMiddleViewHolder.getContentTv();
        if (contentTv != null)
            text(contentTv, Html.fromHtml(html));
    }

    public void setTextColor(@ColorRes int colorId, boolean colorState) {
        TextView contentTv = mMiddleViewHolder.getContentTv();
        if (contentTv != null) {
            if (colorState) {
                textColorState(contentTv, getColorState(mContext, colorId));
            } else {
                textColor(contentTv, getColor(mContext, colorId));
            }
        }
    }


    public void setTextSize(int sizeSp) {
        TextView contentTv = mMiddleViewHolder.getContentTv();
        if (contentTv != null)
            textSize(contentTv, sizeSp);
    }

    class TextMiddleViewHolder implements AbstractAnoleDialogHolder.MiddleViewHolder {
        TextView mContentTv;

        @Nullable
        TextView getContentTv() {
            if (mContentTv == null) {
                mContentTv = (TextView) mMiddleGroup.findViewById(R.id.content);
            }
            return mContentTv;
        }
    }
}
