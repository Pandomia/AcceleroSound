package project.testcompany.com.AcceleroSound;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.support.v7.widget.Toolbar;
import android.app.ActionBar;

import java.util.Random;

import project.testcompany.com.test1.R;

public class MainActivity extends Activity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
   // final Button button = findViewById(R.id.button);
    private AnimatedView mAnimatedView = null;
    MediaPlayer ring;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        ring = MediaPlayer.create(MainActivity.this, R.raw.boom);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        



        //final Button button = findViewById(R.id.button);
       /* button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(mAnimatedView);
            }
        });*/

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);



        mAnimatedView = new AnimatedView(this);
        //View veranderen naar animated
        //setContentView(mAnimatedView);
    }






    @Override
    public void onBackPressed() {

        //Back knop

       /* if (gamePlaying) {
            stopGame();
        } else {
            super.onBackPressed();
        }*/ //FRAGMENTS <-----!!!!


        onPause();
        setContentView(R.layout.activity_main);





    }

    public void onClickBtn(View v)
    {
        //ring.start();
       onResume();
         setContentView(mAnimatedView);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mAnimatedView.ResetBall();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) { }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            mAnimatedView.onSensorEvent(event);

        }
    }




    public class AnimatedView extends View {

        private static final int CIRCLE_RADIUS = 50; //pixels, grootte van cirkel

        private Paint mPaint;
        private int x;
        private int y;
        private int viewWidth;
        private int viewHeight;
        private int color = Color.RED;

        private Random rnd = new Random();

        public AnimatedView(Context context) {
            super(context);


            mPaint = new Paint();
            mPaint.setColor(color/*Color.RED*/); //Cirkel kleur


        }


        public void ResetBall()
        {
            x = viewWidth/2;
            y = viewHeight/2;
        }


        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            viewWidth = w;
            viewHeight = h;
            ResetBall();

        }

        public void onSensorEvent (SensorEvent event) {
            x = x -  (int) event.values[0] *5;
            y = y + (int) event.values[1] *5;





            if (x <= 0 + CIRCLE_RADIUS) {
                color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

                ring.start();

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

            //Invalidate zodat het "loopt"
            invalidate();




        }



    }
}
