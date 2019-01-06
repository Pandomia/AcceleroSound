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
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

import project.testcompany.com.test1.R;



public class MainActivity extends AppCompatActivity implements SensorEventListener/*,NavigationView.OnNavigationItemSelectedListener*/ {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private AnimatedView mAnimatedView = null;
    private static final String FILE_RADIUS = "radiusball.txt";

   // public  boolean  inanimation = false;


    /*public void onClickBrn_Size(Context ctx) {
        int RadiusBall= SettingsFragment.current;
        FileOutputStream Fos=null;


        try {
            Fos = ctx.openFileOutput(FILE_RADIUS,Context.MODE_PRIVATE);
            Fos.write(String.valueOf(RadiusBall).getBytes());

            Toast.makeText(this, "Changes has been saved", Toast.LENGTH_SHORT).show();
            Log.e("Dir", " i'mgay " + ctx.getFilesDir());
            Log.e("Main" , String.valueOf(SettingsFragment.current));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(Fos == null) {
                try {
                    Fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);



        /*getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        */

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.unregisterListener(this);

        mAnimatedView = new AnimatedView(this);
        CallNav();

        mAnimatedView.ring = MediaPlayer.create(MainActivity.this, R.raw.boom);
        mAnimatedView.DeathSound = MediaPlayer.create(MainActivity.this ,R.raw.roblox);

        //Log.e("inanimation",(String.valueOf(mAnimatedView.inanimation)));
        //Log.e("y-as",(String.valueOf(AnimatedView.y)));
/*
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new SettingsFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_settings);
        }*/
        //View veranderen naar animated
        //setContentView(mAnimatedView);

        //SettingsFragment sharedpref = android.support.v7.preference.Preference

    }



    public BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item){

               // FragmentManager fragmentManager = getSupportFragmentManager();
                //FragmentTransaction transaction = fragmentManager.beginTransaction();
                Fragment selectedFragment = null;
                switch (item.getItemId()){
                    case R.id.nav_home:
                        selectedFragment = new HomeFragment();
                        break;

                    case R.id.nav_settings:
                        selectedFragment = new SettingsFragment();

                        break;
                    case R.id.nav_about:
                        selectedFragment = new SettingsFragment();
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

       /* if (gamePlaying) { //Idee om Back te fixen
            stopGame();
        } else {
            super.onBackPressed();
        }*/ //FRAGMENTS <-----!!!!




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
            String text,text2;

            while ((text = br.readLine())  != null)
            {
                sb.append(text);


            }
            //Log.e("fuq",text);
            Log.e("Button STart Clickeed", sb.toString());
            //
            //text2 = str.Spl sb.toString()
            //mAnimatedView.CIRCLE_RADIUS = Integer.valueOf(sb.toString());

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


