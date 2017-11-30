package com.android.devartexplore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by gj on 2017/11/28.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract int proviceContentId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(proviceContentId());

        ButterKnife.bind(this);

    }
}
