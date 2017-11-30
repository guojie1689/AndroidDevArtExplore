package com.android.devartexplore.intentfilter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.widget.Button;
import android.widget.Toast;

import com.android.devartexplore.BaseActivity;
import com.android.devartexplore.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by gj on 2017/11/29.
 */

public class IntentEntryActivity extends BaseActivity {

    @BindView(R.id.btn_launchmode)
    Button btn_launchmode;

    @BindView(R.id.btn_datatest)
    Button btn_datatest;

    @OnClick(R.id.btn_datatest)
    public void dataTest() {
        Intent intent = new Intent();
        intent.setData(Uri.parse("dev://com.android.data:3006/pre/info"));
        ResolveInfo resolveInfo = getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (resolveInfo != null)
            startActivity(intent);
        else
            Toast.makeText(this, "无效Activity", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_launchmode)
    public void launchModeClick() {
        Intent intent = new Intent();
        intent.setAction("com.android.action.c");

        ResolveInfo resolveInfo = getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (resolveInfo != null)
            startActivity(intent);
        else
            Toast.makeText(this, "无效Activity", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected int proviceContentId() {
        return R.layout.activity_intent_entry;
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, IntentEntryActivity.class);
        context.startActivity(intent);

    }
}
