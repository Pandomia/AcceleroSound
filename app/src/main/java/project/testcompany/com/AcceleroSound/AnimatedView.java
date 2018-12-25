package project.testcompany.com.AcceleroSound;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.SensorEvent;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import java.util.Random;

class AnimatedView extends View {

    private static final int CIRCLE_RADIUS = 50; //pixels, grootte van cirkel

    private Paint mPaint;
    private Paint LinePaint;
    private int x; //x location of the ball
    private int y; //y location of the ball
    private int viewWidth;
    private int viewHeight;
    private int color = Color.RED;
    private boolean Start = false;
    public MediaPlayer ring;
    public MediaPlayer DeathSound;

    private Random rnd = new Random();

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

    public void PlaySound()
    {
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

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        viewHeight = h;
        ResetBall();

    }

    public void onSensorEvent (SensorEvent event) {
        x = x - (int) event.values[0] *5;
        y = y + (int) event.values[1] *5;





        if (x <= 0 + CIRCLE_RADIUS) {
            color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

            //ring.start();

            x = 0 + CIRCLE_RADIUS;
            x+=150;

        }
        if (x >= viewWidth - CIRCLE_RADIUS) {
            color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

            ring.start();

            x = viewWidth - CIRCLE_RADIUS;
            x-=150;


        }
        if (y <= 0 + CIRCLE_RADIUS) {
            color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

            ring.start();
            y = 0 + CIRCLE_RADIUS;
            y +=150;


        }
        if (y >= viewHeight - CIRCLE_RADIUS) {
            color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

            ring.start();

            y = viewHeight - CIRCLE_RADIUS;

            y -=150;



            // y = (int)10f;

            // y = y * 10;

                /*for(int i = 0; i < 500; i++){

                    y;
                }*/
            //y-=100;


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

        //We need to call invalidate each time, so that the view continuously draws

        //canvas.drawLine();
        //Invalidate zodat het "loopt"
        invalidate();




    }



}
