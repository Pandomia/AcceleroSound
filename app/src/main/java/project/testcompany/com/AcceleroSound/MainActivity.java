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

import java.util.Random;

import project.testcompany.com.test1.R;



public class MainActivity extends AppCompatActivity implements SensorEventListener/*,NavigationView.OnNavigationItemSelectedListener*/ {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private AnimatedView mAnimatedView = null;
    public boolean inanimation = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();


        /*//Toolbar toolbar = findViewById(R.id.toolbar);
        //drawer = findViewById(R.id.drawerLayout);
        //NavigationView navigationView = findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();*/


        //final Button button = findViewById(R.id.button);
       /* button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(mAnimatedView);
            }
        });*/

        /*getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        */
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


        mAnimatedView = new AnimatedView(this);
        mAnimatedView.ring = MediaPlayer.create(MainActivity.this, R.raw.boom);
        mAnimatedView.DeathSound = MediaPlayer.create(MainActivity.this ,R.raw.roblox);






/*
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new SettingsFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_settings);
        }*/
        //View veranderen naar animated
        //setContentView(mAnimatedView);

    }



    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item){

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                Fragment selectedFragment = null;
                switch (item.getItemId()){
                    case R.id.nav_home:
                       // transaction.replace(R.id.fragment_container,new HomeFragment()).commit();
                        selectedFragment = new HomeFragment();

                        break;

                    case R.id.nav_settings:
                       // transaction.replace(R.id.fragment_container,new HomeFragment()).commit();

                        selectedFragment = new SettingsFragment();
                       // setContentView(R.layout.fragment_settings);

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

/*
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SettingsFragment()).commit();
                break;

            case R.id.test_set:
                setContentView(mAnimatedView);
                Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show();
                break;


        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/

    @Override
    public void onBackPressed() {

        //Back knop

        if(inanimation == true){
            onPause();

        }
        else{
            super.onBackPressed();
        }

       /* if (gamePlaying) {
            stopGame();
        } else {
            super.onBackPressed();
        }*/ //FRAGMENTS <-----!!!!
/*
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
      //  super.onBackPressed();

        //Back press fixen
        //onPause();
        //setContentView(R.layout.activity_main);


    }

    public void onClickBtn(View v) {
        //ring.start();
        onResume();
        setContentView(mAnimatedView);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mAnimatedView.ResetBall();
        inanimation = true;


        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);




    }

    @Override
    protected void onPause() {

        super.onPause();
        mSensorManager.unregisterListener(this);
        //setContentView(R.layout.activity_main);

        inanimation = false;

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


