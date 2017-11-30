package com.android.devartexplore.launchmode;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;

import com.android.devartexplore.BaseActivity;
import com.android.devartexplore.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by gj on 2017/11/28.
 */

public class StandardBActivity extends BaseActivity {


    @BindView(R.id.txt_info)
    TextView txt_info;

    @BindView(R.id.btn_launch)
    Button btn_launch;

    @Override
    protected int proviceContentId() {
        return R.layout.activity_info_with_button;
    }


    @OnClick(R.id.btn_launch)
    public void gotoAActivity() {
        StandardAActivity.startActivity(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        txt_info.setText("界面B");

    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, StandardBActivity.class);

        context.startActivity(intent);
    }

}
