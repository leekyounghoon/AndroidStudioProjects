package com.leekh13.exerciserecord;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by leekh on 2015-05-21.
 */
public class RecordActivity extends Activity {

    public ListView mListView_Record;
    public ListViewAdapter mAdapter_Record;


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record);

        //ListView
        mListView_Record = (ListView) findViewById(R.id.listView2);
        mAdapter_Record = new ListViewAdapter(this);

        Refresh();

       mListView_Record.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListData mData = mAdapter_Record.mListData.get(position);
                Toast.makeText( getApplicationContext() , mData.mDate + ":" + mData.mName, Toast.LENGTH_SHORT).show();
            }
        });

        // ListView 끝
    }

    /**
     *
     */
    @Override
    protected void onResume(){
        super.onResume();

        Refresh();
    }

    public void Refresh()
    {

        mAdapter_Record = new ListViewAdapter(this);
        mListView_Record.setAdapter(mAdapter_Record);

        //데이터 추가
        ArrayList<ListData> arrayList = new ArrayList<ListData>();
        MainActivity.dbManager.select_EXER_RECORD(arrayList);

        for(int i = 0;arrayList.size() > i ; i++)
        {
            mAdapter_Record.addItem( getResources().getDrawable(R.drawable.listviewicon, getApplication().getTheme() ),
                    arrayList.get(i).mName, arrayList.get(i).mDate,  Integer.parseInt( arrayList.get(i).mKg ), Integer.parseInt(arrayList.get(i).mCount) );

        }
    }

    private class ViewHolder{
        //운동강도
        public ImageView mIntensity;

        // 운동이름
        public TextView mName;

        // 무게
        public TextView mKg;

        // 횟수
        public TextView mCount;

        //날짜
        public TextView mDate;


    }


    private class ListViewAdapter extends BaseAdapter {
        private Context mContext = null;
        private ArrayList<ListData> mListData = new ArrayList<ListData>();

        public ListViewAdapter( Context mContext){
            super();
            this.mContext = mContext;
        }

        @Override
        public int getCount(){
            return mListData.size();
        }

        @Override
        public Object getItem(int position) {
            return mListData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            ViewHolder holder;
            if( convertView == null){
                holder = new ViewHolder();

                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.listview_record, null);

                holder.mIntensity = (ImageView) convertView.findViewById(R.id.imageView);
                holder.mName = (TextView) convertView.findViewById(R.id.textView9);

                holder.mDate = (TextView) convertView.findViewById(R.id.textView10);
                holder.mKg = (TextView) convertView.findViewById(R.id.textView11);
                holder.mCount = (TextView) convertView.findViewById(R.id.textView12);

                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }

            ListData mData = mListData.get(position);

            if( mData.mIntenSity != null )
            {
                holder.mIntensity.setVisibility(View.VISIBLE);
                holder.mIntensity.setImageDrawable(mData.mIntenSity);

            }
            else
            {
                holder.mIntensity.setVisibility(View.GONE);
            }

            holder.mCount.setText(mData.mCount);
            holder.mDate.setText(mData.mDate);
            holder.mKg.setText(mData.mKg);
            holder.mName.setText(mData.mName);

            return convertView;


        }

        public void addItem( Drawable icon, String Name, String r_Date, int Kg, int Count ){
            ListData addInfo = null;
            addInfo = new ListData();
            addInfo.mIntenSity = icon;
            addInfo.mDate = r_Date;
            addInfo.mName = Name;
            addInfo.mKg = String.valueOf(Kg);
            addInfo.mCount = String.valueOf(Count);

            mListData.add(addInfo);
        }


        public void remove(int position){
            mListData.remove(position);
            dataChange();
        }

        public void sort(){
            Collections.sort(mListData, ListData.ALPHA_COMPARATOR);
            dataChange();
        }

        public void dataChange(){
            mAdapter_Record.notifyDataSetChanged();
        }

    }

}
