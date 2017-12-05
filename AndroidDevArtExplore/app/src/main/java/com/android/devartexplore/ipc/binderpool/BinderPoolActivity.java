package com.android.devartexplore.ipc.binderpool;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;

import com.android.devartexplore.BaseActivity;
import com.android.devartexplore.R;
import com.blankj.utilcode.util.LogUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by gj on 2017/12/5.
 */

public class BinderPoolActivity extends BaseActivity {

    @BindView(R.id.txt_info)
    TextView txt_info;

    @BindView(R.id.btn_launch)
    Button btn_launch;

    private BinderPool binderPool = null;

    @Override
    protected int proviceContentId() {
        return R.layout.activity_info_with_button;
    }

    @OnClick(R.id.btn_launch)
    public void launch() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    if (binderPool != null) {
                        IBinder binder = binderPool.queryBinder(BinderPool.BINDER_CODE_COMPUTE);

                        ICompute compute = (ICompute) ComputeImpl.asInterface(binder);

                        LogUtils.d("compute:" + compute.add(10, 20));
                    }

                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Thread(new Runnable() {
            @Override
            public void run() {
                binderPool = BinderPool.getInstance(BinderPoolActivity.this);
            }
        }).start();

        txt_info.setText("Binder 连接池");
    }
}
