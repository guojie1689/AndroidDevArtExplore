package com.android.devartexplore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.android.devartexplore.intentfilter.IntentEntryActivity;
import com.android.devartexplore.ipc.messenger.MessengerClientActivity;
import com.android.devartexplore.launchmode.LaunchEntryActivity;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

}
