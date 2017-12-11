package com.android.devartexplore.removeview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.android.devartexplore.BaseActivity;
import com.android.devartexplore.R;

import butterknife.BindView;

/**
 * Created by gj on 2017/12/10.
 */

public class NotificationActivity extends BaseActivity {

    @BindView(R.id.txt_info)
    TextView txt_info;

    @BindView(R.id.txt_info1)
    TextView txt_info1;

    @Override
    protected int proviceContentId() {
        return R.layout.activity_info;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        txt_info.setText("Notification 界面");
    }
}
