package project.testcompany.com.AcceleroSound;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.support.v7.widget.Toolbar;
import android.app.ActionBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;




public class MainActivity extends AppCompatActivity implements SensorEventListener/*,NavigationView.OnNavigationItemSelectedListener*/ {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private AnimatedView mAnimatedView = null;
    private static final String FILE_RADIUS = "radiusball.txt";
    private String Default = "100,5,-16776961";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);





        try {
            FileOutputStream fos = openFileOutput(FILE_RADIUS, Context.MODE_PRIVATE);
            fos.write(Default.getBytes());
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.unregisterListener(this);

        mAnimatedView = new AnimatedView(this);
        CallNav();

        mAnimatedView.ring = MediaPlayer.create(MainActivity.this, R.raw.boom);
        mAnimatedView.DeathSound = MediaPlayer.create(MainActivity.this ,R.raw.roblox);
        mAnimatedView.Steve = MediaPlayer.create(MainActivity.this, R.raw.minecraft);
        mAnimatedView.Undertale = MediaPlayer.create(MainActivity.this, R.raw.undertale);



    }



    public BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item){


                Fragment selectedFragment = null;
                switch (item.getItemId()){
                    case R.id.nav_home:
                        selectedFragment = new HomeFragment();
                        break;

                    case R.id.nav_settings:
                        selectedFragment = new SettingsFragment();

                        break;
                    case R.id.nav_about:
                        selectedFragment = new AboutFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        selectedFragment).commit();
                return true;
            }

            };


    public void CallNav(){

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();

    }

    @Override
    public void onBackPressed() {

        //Back knop

        if(mAnimatedView.inanimation == true){
            mAnimatedView.inanimation = false;

            onPause();


        }
        else if(mAnimatedView.inanimation == false){
            super.onBackPressed();
        }





    }

    public void onClickBtn(View v) {
        mAnimatedView.inanimation = true;
        onResume();
        setContentView(mAnimatedView);

        FileInputStream fis = null;

        try {
            fis = openFileInput(FILE_RADIUS);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            String SArray[];


            while ((text = br.readLine())  != null)
            {
                sb.append(text).append(",");


            }
            SArray = sb.toString().split("," );

            Log.d("Button STart Clickeed", sb.toString());
            //text2 = str.Spl sb.toString()

            Log.d("buffer is mijn vriend" , SArray[0] + "  " + SArray[1]+ "  " +mAnimatedView.color);

            mAnimatedView.CIRCLE_RADIUS = Integer.valueOf(SArray[0]);
            mAnimatedView.Velocity = Integer.valueOf(SArray[1]);
            mAnimatedView.color  = Integer.valueOf( SArray[2]);



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        setContentView(R.layout.activity_main);
        CallNav();




    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            mAnimatedView.onSensorEvent(event);

        }
    }


}


