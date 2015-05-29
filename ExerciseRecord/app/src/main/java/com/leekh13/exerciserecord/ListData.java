package com.leekh13.exerciserecord;

import android.graphics.drawable.Drawable;

import java.text.Collator;
import java.util.Comparator;

/**
 * Created by leekh on 2015-05-26.
 */
public class ListData {

    // ID
    public Integer ID;

    // 아이콘(강도
    public Drawable mIntenSity;

    // 운동이름
    public String mName;

    // 무게
    public String mKg;

    // 횟수
    public String mCount;

    //날짜
    public String mDate;


    // 날짜 순으로 정렬;

    /**
     *
     */
    public static final Comparator<ListData> ALPHA_COMPARATOR = new Comparator<ListData>() {
        private final Collator sCollator = Collator.getInstance();

        @Override
        public int compare(ListData mListData_1, ListData mListData_2) {
            return sCollator.compare(mListData_1.mDate, mListData_2.mDate);
        }
    };


}
