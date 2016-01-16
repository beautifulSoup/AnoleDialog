package cn.campusapp.dialog.holder;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import cn.campusapp.dialog.R;
import timber.log.Timber;

/**
 * Created by kris on 15/9/29.
 */
public class AnoleListDialogHolder extends AbstractAnoleDialogHolder {


    ListMiddleViewHolder mMiddleViewHolder = new ListMiddleViewHolder();


    class ListMiddleViewHolder implements MiddleViewHolder{
        ListView mLv;

        @Nullable
        public ListView getLv(){
            if(mLv == null)
                mLv = (ListView) mMiddleGroup.findViewById(R.id.content_lv);
            return mLv;
        }

    }

    public AnoleListDialogHolder(Context context) {
        super(context);
        View middleGroup = LayoutInflater.from(context).inflate(R.layout.dialog_anole_middle_list, null);
        mMiddleGroup.addView(middleGroup);
    }

    public void setAdapter(ListAdapter adapter){
        ListView lv = mMiddleViewHolder.getLv();
        if(lv != null)
            lv.setAdapter(adapter);
    }


    public <T> void setOnItemClickListener(final Dialog dialog, final OnAnoleDialogItemClickLisener<T> listener){
        final ListView lv = mMiddleViewHolder.getLv();
        if(lv.getAdapter() == null){
            Timber.e("请先设置Adapter");
            return;
        }
        if(lv != null) {
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    T item = null;
                    try {
                        item = (T) lv.getAdapter().getItem(position);
                    } catch(ClassCastException e){
                        Timber.e("设置的listener中的类型与adapter获得的类型不相符");
                        throw new RuntimeException();

                    }
                    listener.onItemClick(dialog, view, position, item);
                }
            });
        }
    }


    public void setItems(final Dialog dialog, String[] items, final OnAnoleDialogItemClickLisener<String> listener){
        final DialogAdapter adapter = new DialogAdapter(mContext, items);
        ListView lv = mMiddleViewHolder.getLv();
        if(lv!= null) {
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String item = (String) adapter.getItem(position);
                    listener.onItemClick(dialog, view, position, item);
                }
            });
        }
    }


    class DialogAdapter extends BaseAdapter {

        String[] items = null;

        Context context = null;


        public DialogAdapter(Context context, String[] is){
            this.context = context;
            items = is;
        }

        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public Object getItem(int position) {
            return items[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            String item = items[position];
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_list_item, null);
            TextView tv = (TextView) view.findViewById(R.id.tv);
            tv.setText(item);
            return view;
        }
    }

    public interface  OnAnoleDialogItemClickLisener<T>{
        void onItemClick(Dialog dialog, View view, int position, T item);
    }

}
