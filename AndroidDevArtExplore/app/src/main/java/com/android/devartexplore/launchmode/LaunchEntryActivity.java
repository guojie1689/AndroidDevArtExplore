package com.android.devartexplore.launchmode;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.devartexplore.BaseActivity;
import com.android.devartexplore.R;

import butterknife.OnClick;

public class LaunchEntryActivity extends BaseActivity {

    @OnClick(R.id.btn_standard)
    public void toStartTest() {
        StandardAActivity.startActivity(this);
    }

    @OnClick(R.id.btn_singletop)
    public void toSingleTop() {
        SingleTopAActivity.startActivity(this);
    }

    @OnClick(R.id.btn_singletask)
    public void toSingleTask() {
        SingleTaskAActivity.startActivity(this);
    }

    @OnClick(R.id.btn_singleinstance)
    public void toSingleInstance() {
        SingleInstanceAActivity.startActivity(this);
    }

    @Override
    protected int proviceContentId() {
        return R.layout.activity_launch_entry;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, LaunchEntryActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
