package project.testcompany.com.AcceleroSound;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.SensorEvent;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import java.util.Random;

class AnimatedView extends View implements SettingsFragment.Settings{

    /*int isize;

    public int getIsize() {
        return isize;
    }

    public void setIsize() {

        this.isize = SettingsFragment.Settings.isize;
    }*/



    int CIRCLE_RADIUS = SettingsFragment.Settings.iSize; //50; //pixels, grootte van cirkel

    private Paint mPaint;
    private Paint LinePaint;
    public int Velocity = SettingsFragment.Settings.iSpeed; // speed
    static public int x; //x location of the ball
    static public int y; //y location of the ball
    private int viewWidth;
    private int viewHeight;
    public int color = SettingsFragment.Settings.iColor;//Color.RED;
    private boolean Start = false;
    public boolean inanimation = false;
    public MediaPlayer ring;
    public MediaPlayer DeathSound;
    public MediaPlayer Steve;
    public MediaPlayer Undertale;


    public AnimatedView(Context context) {
        super(context);


        LinePaint = new Paint();
        LinePaint.setColor(Color.BLACK);
        LinePaint.setStrokeWidth(9);

        mPaint = new Paint();
        mPaint.setColor(color/*Color.RED*/); //Cirkel kleur


    }




    public void ResetBall()
    {
        x = viewWidth/2;
        y = viewHeight/2;
    }

    private void PlaySound()
    {

        //word niet gebruikt
        if(x == viewHeight/2 && y == viewHeight/2) {
            ring.stop();
            DeathSound.stop();
        }
        if(x > viewWidth && y > viewHeight)
            ring.start();
        if (x < viewWidth && y > viewHeight)
            DeathSound.start();

        if(x < viewWidth && y < viewHeight)
            ring.start();
        if(x > viewWidth && y < viewHeight)
            DeathSound.start();
    }

    private void CheckCorners()
    {
        if(x -CIRCLE_RADIUS<=0){
            x = 0 + CIRCLE_RADIUS ;
                /*for(int i = 0;i < 151;i++)
                {
                    x+=i;
                }*/
        }
        if(y -CIRCLE_RADIUS<=0)
        {
            y = 0 + CIRCLE_RADIUS;

        }
        if(x +CIRCLE_RADIUS>=viewWidth){
            x = viewWidth - CIRCLE_RADIUS;

        }
        if(y +CIRCLE_RADIUS >=viewHeight)
        {
            y = viewHeight - CIRCLE_RADIUS;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        viewHeight = h;
        ResetBall();
    }

    public void onSensorEvent (SensorEvent event) {
        x = x - (int) event.values[0] *Velocity;
        y = y + (int) event.values[1] *Velocity;


        if(x == viewHeight/2 && y == viewHeight/2) {
            ring.stop();
            DeathSound.stop();
            Steve.stop();
        }
        if(inanimation==false){
            //Doe niets
        }

        else if(x > viewWidth/2 && y > viewHeight/2) {

            ring.start();
            CheckCorners();
            //Log.e("speel","1");
        }
        else if (x < viewWidth/2 && y > viewHeight/2) {
            DeathSound.start();
            CheckCorners();
            //Log.e("speel","2");
        }
        else if(x < viewWidth/2 && y < viewHeight/2) {
            Steve.start();
            CheckCorners();
            //Log.e("speel","3");
        }
        else if(x > viewWidth/2 && y < viewHeight/2){
            Undertale.start();
            CheckCorners();
            //Log.e("speel","4");
        }




    }

    @Override
    protected void onDraw(Canvas canvas) {

        SensorEvent event;
        SensorEvent sensorEvent = null;
        canvas.drawLine(viewWidth/2,0,viewWidth/2,viewHeight,LinePaint);
        canvas.drawLine(0,viewHeight/2,viewWidth,viewHeight/2,LinePaint);

        // 30<(int)sensorEvent.values[0]
        if(300<x){
            // ring.start();
        }
        if(y<60 ){

        }
        mPaint.setColor(color);

        //canvas.drawColor(Color.GREEN);

        Log.d(getClass().getName(), "value x = " + x);

        Log.d(getClass().getName(), "value y = " + y);


        canvas.drawCircle(x, y, CIRCLE_RADIUS, mPaint);

        //canvas.drawLine();
        //Invalidate zodat het "loopt"
        invalidate();




    }



}
