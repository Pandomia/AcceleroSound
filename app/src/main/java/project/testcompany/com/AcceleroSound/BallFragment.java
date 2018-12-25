package project.testcompany.com.AcceleroSound;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import project.testcompany.com.test1.R;



public class BallFragment extends Fragment {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    //private AnimatedView mAnimatedView = null;
    MediaPlayer ring;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ball, container,false);
    }


}
