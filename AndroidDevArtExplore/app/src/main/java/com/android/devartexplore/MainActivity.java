package com.android.devartexplore;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.android.devartexplore.drawable.DrawableActivity;
import com.android.devartexplore.intentfilter.IntentEntryActivity;
import com.android.devartexplore.ipc.messenger.MessengerClientActivity;
import com.android.devartexplore.launchmode.LaunchEntryActivity;
import com.android.devartexplore.removeview.RemoteViewActivity;
import com.android.devartexplore.view.ViewMeasureActivity;
import com.android.devartexplore.viewevent.ViewEventActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.btn_launchmode)
    Button btn_launchmode;

    @BindView(R.id.btn_intent_filter)
    Button btn_intent_filter;

    @BindView(R.id.btn_ipc)
    Button btn_ipc;

    @Override
    protected int proviceContentId() {
        return R.layout.activity_main;
    }

    @OnClick(R.id.btn_launchmode)
    public void tolaunchMode(View view) {
        LaunchEntryActivity.startActivity(this);
    }

    @OnClick(R.id.btn_intent_filter)
    public void toIntentFilterActivity() {
        IntentEntryActivity.startActivity(this);
    }

    @OnClick(R.id.btn_ipc)
    public void toIpcActivity() {
        MessengerClientActivity.startActivity(this);
    }

    @OnClick(R.id.btn_view_event)
    public void toViewEventActivity() {
        Intent intent = new Intent(this, ViewEventActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_view_work_impl)
    public void toViewActivity() {
        Intent intent = new Intent(this, ViewMeasureActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_view_remote_view)
    public void openRemoteView() {
        Intent intent = new Intent(this, RemoteViewActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.btn_drawable)
    public void openDrawableView() {
        Intent intent = new Intent(this, DrawableActivity.class);
        startActivity(intent);

    }

}
