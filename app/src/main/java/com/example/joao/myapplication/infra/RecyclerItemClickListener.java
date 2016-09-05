package com.example.joao.myapplication.infra;

/**
 * Created by joao on 24/08/16.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

    public interface OnItemClickListener {

        void onLongClick(View view, int position);
    }

    private GestureDetector mGestureDetector;
    private OnItemClickListener mListener;


    public RecyclerItemClickListener(Context context, final RecyclerView mRecyclerView, OnItemClickListener listener) {
        this.mListener = listener;
        this.mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }
            @Override
            public void onLongPress(MotionEvent e) {
                View child = mRecyclerView.findChildViewUnder(e.getX(),e.getY());

                if(child!=null && mListener != null) {

                    mListener.onLongClick(child,mRecyclerView.getChildAdapterPosition(child));
                }
            }

        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        mGestureDetector.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

}