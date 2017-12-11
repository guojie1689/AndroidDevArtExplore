package com.android.devartexplore.view;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.android.devartexplore.BaseActivity;
import com.android.devartexplore.R;
import com.blankj.utilcode.util.LogUtils;

import butterknife.BindView;

/**
 * Created by gj on 2017/12/9.
 */

public class ViewMeasureActivity extends BaseActivity {

    @BindView(R.id.txt_info)
    TextView txt_info;

    @Override
    protected int proviceContentId() {
        return R.layout.activity_info;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LogUtils.d("onCreate -- width:" + txt_info.getMeasuredWidth() + " height:" + txt_info.getMeasuredHeight());

        ViewTreeObserver observer = txt_info.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                txt_info.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                LogUtils.d("observer -- width:" + txt_info.getMeasuredWidth() + " height:" + txt_info.getMeasuredHeight());
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        txt_info.post(new Runnable() {
            @Override
            public void run() {
                LogUtils.d("post -- width:" + txt_info.getMeasuredWidth() + " height:" + txt_info.getMeasuredHeight());
            }
        });

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus) {
            LogUtils.d("onWindowFocusChanged -- width:" + txt_info.getMeasuredWidth() + " height:" + txt_info.getMeasuredHeight());
        }
    }


}
