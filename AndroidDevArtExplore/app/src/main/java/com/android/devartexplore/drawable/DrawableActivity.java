package com.android.devartexplore.drawable;

import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.devartexplore.BaseActivity;
import com.android.devartexplore.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by gj on 2017/12/10.
 */

public class DrawableActivity extends BaseActivity {

    @BindView(R.id.img_level)
    ImageView img_level;

    @BindView(R.id.btn_transition)
    TextView btn_transition;

    @BindView(R.id.img_scale)
    ImageView img_scale;

    @BindView(R.id.test_clip)
    ImageView test_clip;


    @OnClick(R.id.btn_transition)
    public void startTransition() {
        TransitionDrawable drawable = (TransitionDrawable) btn_transition.getBackground();

        drawable.startTransition(3000);
    }

    @Override
    protected int proviceContentId() {
        return R.layout.activity_drawable;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        img_level.setImageLevel(99);

        ScaleDrawable scaleDrawable = (ScaleDrawable) img_scale.getBackground();
        scaleDrawable.setLevel(1);

        ClipDrawable clipDrawable = (ClipDrawable) test_clip.getDrawable();
        clipDrawable.setLevel(2000);
    }
}
