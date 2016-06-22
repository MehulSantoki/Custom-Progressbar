package com.yuvadevelopers;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Mehul Shah on 6/15/2016.
 */
public class HorizontalProgressbar extends View {

    Paint paint = new Paint();
    Paint textPaint = new Paint();
    private int mProgerss = 0;
    public static int DETERMINATE = 0;
    public static int INDETERMINATE = 1;
    private int mProgressType = INDETERMINATE;
    private int mColor = 0,mColorProg=0;





    public HorizontalProgressbar(Context context) {
        super(context);
        init();
    }
    public HorizontalProgressbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HorizontalProgressbar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){
        paint.setAntiAlias(true);
        paint.setStrokeWidth(15);
        paint.setStyle(Paint.Style.FILL);
        textPaint.setAntiAlias(true);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setStrokeWidth(getHeight());

        if(mProgressType == DETERMINATE ) {
            if (mProgerss > 0) {
                paint.setColor(Color.parseColor("#fab905"));
                canvas.drawLine(0, getHeight() / 2, mProgerss, getHeight() / 2, paint);

            }
            if (mProgerss > getWidth() / 4) {
                paint.setColor(Color.parseColor("#33a652"));
                canvas.drawLine(getWidth() / 4, getHeight() / 2, mProgerss, getHeight() / 2, paint);
            }
            if (mProgerss > getWidth() / 2) {
                paint.setColor(Color.parseColor("#e84135"));
                canvas.drawLine(getWidth() / 2, getHeight() / 2, mProgerss, getHeight() / 2, paint);
            }
            if (mProgerss > getWidth() / 1.33) {
                paint.setColor(Color.parseColor("#4487f2"));
                canvas.drawLine((int) (getWidth() / 1.33), getHeight() / 2, mProgerss, getHeight() / 2, paint);
            }

        }else{

            if(mColor == 0){
                paint.setColor(Color.parseColor("#fab905"));
                canvas.drawLine(mColorProg, getHeight() / 2, mProgerss, getHeight() / 2, paint);
            }else if(mColor == 1){
                paint.setColor(Color.parseColor("#33a652"));
                canvas.drawLine(mColorProg, getHeight() / 2, mProgerss, getHeight() / 2, paint);
            }else if(mColor == 2){
                paint.setColor(Color.parseColor("#e84135"));
                canvas.drawLine(mColorProg, getHeight() / 2, mProgerss, getHeight() / 2, paint);
            }else if(mColor == 3){
                paint.setColor(Color.parseColor("#4487f2"));
                canvas.drawLine(mColorProg, getHeight() / 2, mProgerss, getHeight() / 2, paint);
            }

            startProgress();
        }

    }


    private void startProgress(){

        Runnable runnable = new Runnable() {
            @Override
            public void run() {


                mColorProg+=5;
                if(mColorProg > getWidth()){
                    mColorProg = 0;
                    if(mColor == 4){
                        mColor=0;
                    }else{
                        mColor++;
                    }

                }

                invalidate();

            }
        };new Handler().postDelayed(runnable, 10);


    }

    /*To make progressbar determinate use this function*/
    public void setProgress(int progress){

        Log.e("width","  "+getWidth());
        if(progress <= 100) {
            mProgerss = (progress*getWidth())/100;
            invalidate();
        }

    }

}
