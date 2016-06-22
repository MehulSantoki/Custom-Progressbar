package com.yuvadevelopers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.media.Image;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Mehul Shah on 6/13/2016.
 */
public class CircularProgressbar extends View {
    Paint paint = new Paint();
    Paint textPaint = new Paint();
    float center_x, center_y;
    float width ;
    float height,rotateAngle=0 ;
    float radius,startAngle=0,sweepAngle=360,angleIncrement=0;
    boolean isReverse = false;
    private String mProgressText;
    public static int DETERMINATE = 0;
    public static int INDETERMINATE = 1;
    private int mProgressType = INDETERMINATE;



    public CircularProgressbar(Context context) {
        super(context);
        init();
    }
    public CircularProgressbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircularProgressbar(Context context, AttributeSet attrs, int defStyle) {
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

        rotateAngle+= angleIncrement;
        width = (float)getWidth();
        height = (float)getHeight();
        center_x = width/2;
        center_y = height/2;

        if (width > height){
            radius = height/3;

        }else{
            radius = width/3;

        }

        if(mProgressType == INDETERMINATE) {
            canvas.rotate(rotateAngle, center_x, center_y);
        }
        final RectF oval = new RectF();
        paint.setStyle(Paint.Style.STROKE);
        center_x = width/2;
        center_y = height/2;
        oval.set(center_x - radius, center_y - radius, center_x + radius, center_y + radius);

        if(isReverse){
            sweepAngle -= 8;//3
            angleIncrement = 8;//8
            paint.setColor(Color.parseColor("#fab905"));
            canvas.drawArc(oval, startAngle, -sweepAngle, false, paint);
            if(sweepAngle > 90){

                paint.setColor(Color.parseColor("#33a652"));
                canvas.drawArc(oval, startAngle, -(sweepAngle -90), false, paint);

            }
            if(sweepAngle > 180){
                paint.setColor(Color.parseColor("#e84135"));
                canvas.drawArc(oval, startAngle , -(sweepAngle -180), false, paint);

            }

            if(sweepAngle > 270){
                paint.setColor(Color.parseColor("#4487f2"));
                canvas.drawArc(oval, startAngle , -(sweepAngle -270), false, paint);

            }

        }else{
            sweepAngle += 6;//4
            angleIncrement = 8;//5
            paint.setColor(Color.parseColor("#fab905"));
            canvas.drawArc(oval, startAngle, sweepAngle, false, paint);
            if(sweepAngle > 90){
                paint.setColor(Color.parseColor("#33a652"));
                canvas.drawArc(oval, startAngle+90, (sweepAngle -90), false, paint);

            }
            if(sweepAngle > 180){
                paint.setColor(Color.parseColor("#e84135"));
                canvas.drawArc(oval, startAngle + 180, (sweepAngle -180), false, paint);

            }
            if(sweepAngle > 270){
                paint.setColor(Color.parseColor("#4487f2"));
                canvas.drawArc(oval, startAngle + 270, (sweepAngle -270), false, paint);

            }

        }


        if(mProgressType == INDETERMINATE ){
            if(getVisibility() == VISIBLE) {
                startProgress();
            }
        }else{
            textPaint.setTextSize(getWidth() / 4);
            textPaint.setColor(paint.getColor());
            drawCenter(canvas,textPaint,mProgressText);
        }


    }
    private Rect r = new Rect();

    private void drawCenter(Canvas canvas, Paint paint, String text) {
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.setTextAlign(Paint.Align.LEFT);
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = cHeight / 2f + r.height() / 2f - r.bottom;
        canvas.drawText(text, x, y, paint);
    }
    private void startProgress(){

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                if(sweepAngle > 360){

                    isReverse = true;

                }else if(sweepAngle < 0){

                    isReverse = false;
                }


                if(rotateAngle > 360){
                    rotateAngle = 0;

                }

                invalidate();

            }
        };new Handler().postDelayed(runnable, 10);


    }

    /*To make progressbar determinate use this function*/
    public void setProgress(int progress){

        if(progress <= 100) {
            sweepAngle = (progress * 360) / 100;
            mProgressText = String.valueOf(progress);
            invalidate();
        }

    }
    public void setProgressType(int type){

        mProgressType = type;


    }


}
