package com.android.devartexplore.viewevent.sectino2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.devartexplore.BaseActivity;
import com.android.devartexplore.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by gj on 2017/12/7.
 */

public class Section2Activity extends BaseActivity {

    @BindView(R.id.list_view1)
    ListView list_view1;

    @Override
    protected int proviceContentId() {
        return R.layout.activity_section2;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initList();
    }

    private List<String> getData() {

        List<String> data = new ArrayList<String>();

        for (int i = 0; i < 50; i++) {
            data.add("测试数据" + i);
        }

        return data;
    }

    private void initList() {
        list_view1.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, getData()));
    }

}
