package com.android.devartexplore.viewevent.sectino2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.blankj.utilcode.util.LogUtils;

/**
 * Created by gj on 2017/12/7.
 */

public class VerticalScroller extends LinearLayout {

    private float lastX = 0;
    private float lastY = 0;

    private float moveLastY = 0;

    private int headHeight = 100;

    private Scroller mScroller = null;

    private void init(Context context) {
        mScroller = new Scroller(context);
    }

    public VerticalScroller(Context context) {
        super(context);

        init(context);
    }

    public VerticalScroller(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public VerticalScroller(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    public VerticalScroller(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(context);

    }

    public void setHeadHeight(int headHeight) {
        this.headHeight = headHeight;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {

        boolean interceptTouch = false;

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                LogUtils.d("onInterceptTouchEvent -- entry ACTION_DOWN");
                interceptTouch = false;
                break;

            case MotionEvent.ACTION_MOVE:
                float curY = event.getY();

                LogUtils.d("curY:" + curY + " lastY" + lastY);

                if (curY - lastY > 0) {//向下滑动，向上滚动

                } else if (curY - lastY < 0) {//向上滑动，向下滚动
                    int curScroolY = getScrollY();

                    LogUtils.d("curScroolY ----" + curScroolY);

                    if (curScroolY < headHeight) {//head显示，则由父容器处理滑动，直到隐藏
                        interceptTouch = true;
                    }
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;

            case MotionEvent.ACTION_MOVE:
                int moveY = (int) (event.getY() - moveLastY);

                scrollBy(0, -moveY);

                LogUtils.d("move Y :" + moveY);

                break;

            case MotionEvent.ACTION_UP:

                mScroller.startScroll(0, getScrollY(), 0, headHeight);
                break;
        }

        moveLastY = event.getY();

        return true;
    }

    @Override
    public void computeScroll() {
        // 第三步，重写computeScroll()方法，并在其内部完成平滑滚动的逻辑
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }
}
