package com.android.devartexplore.viewevent;

import android.content.Intent;

import com.android.devartexplore.BaseActivity;
import com.android.devartexplore.R;
import com.android.devartexplore.viewevent.sectino2.Section2Activity;
import com.android.devartexplore.viewevent.section1.Section1Activity;

import butterknife.OnClick;

/**
 * Created by gj on 2017/12/7.
 */

public class ViewEventActivity extends BaseActivity {

    @OnClick(R.id.btn_section1)
    public void toSection1() {
        Intent intent = new Intent(this, Section1Activity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_section2)
    public void toSection2() {
        Intent intent = new Intent(this, Section2Activity.class);
        startActivity(intent);
    }


    @Override
    protected int proviceContentId() {
        return R.layout.activity_view_event;
    }
}
