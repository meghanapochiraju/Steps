package com.utd.mxp165130.steps;

import android.content.Context;
import android.support.v7.widget.*;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class ItemClickListener implements RecyclerView.OnItemTouchListener {

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }
    private OnItemClickListener clickListener;
    GestureDetector gestureDetector;

    public ItemClickListener(Context context, OnItemClickListener listener) {
        clickListener = listener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
            clickListener.onItemClick(childView, rv.getChildAdapterPosition(childView));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}