package com.android.devartexplore.viewevent.section1;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

/**
 * Created by gj on 2017/12/7.
 */

public class HorScroller extends HorizontalScrollView {

    public HorScroller(Context context) {
        super(context);
    }

    public HorScroller(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HorScroller(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public HorScroller(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private float lastX = 0;
    private float lastY = 0;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {

        boolean interceptTouch = false;

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                interceptTouch = false;
                break;

            case MotionEvent.ACTION_MOVE:
                float curX = event.getX();
                float curY = event.getY();

                if (Math.abs(curX - lastX) > Math.abs(curY - lastY)) {//横向
                    interceptTouch = true;
                } else {
                    interceptTouch = false;
                }
                break;

            case MotionEvent.ACTION_UP:
                interceptTouch = false;
                break;
        }

        lastX = event.getX();
        lastY = event.getY();

        return interceptTouch;
    }
}
