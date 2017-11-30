package com.android.devartexplore.intentfilter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.android.devartexplore.BaseActivity;
import com.android.devartexplore.R;

import butterknife.BindView;

/**
 * Created by gj on 2017/11/29.
 */

public class IntentDataActivity extends BaseActivity {

    @BindView(R.id.txt_info)
    TextView txt_info;

    @Override
    protected int proviceContentId() {
        return R.layout.activity_intent_action;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        txt_info.setText("data ---");
    }
}
