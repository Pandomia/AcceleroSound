package project.testcompany.com.AcceleroSound;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.content.Context;

import org.w3c.dom.Text;

import project.testcompany.com.test1.R;
import yuku.ambilwarna.AmbilWarnaDialog;


public class SettingsFragment extends PreferenceFragmentCompat {
    TextView textView;
    SeekBar seekBar;
    private Context context;

    static int min = 50, max = 200, current = 50;

    static int size = current;

    static int color = Color.BLUE;

    ConstraintLayout mLayout;
    int mDefaultColor;
    Button ButtonColor;



    // AnimatedView lol = new AnimatedView();
    //lol.
    public interface Settings{
        int isize = size;
        int icolor = color;
   }
/*
    @SuppressLint("ValidFragment")
    public SettingsFragment (Context context){
        this.context = context;
    }

    AnimatedView roll = new AnimatedView(context);

    roll.*/

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        //setPreferencesFromResource(R.xml.preferences, s);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_settings,container,false);


       Button btn1 = (Button) view.findViewById(R.id.Size_Picker);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                size = current;
                Log.e("current",(String.valueOf(current)));
                Log.e("size",(String.valueOf(size)));


            }
        });
       /*{
            @Override
            public void onClick(View view){
                size = current;
            }
        }
        public void onClickBtn_Size(View view) {
            size = current;
        }
        */

        /*SeekBar seekBar = (SeekBar) view.findViewById(R.id.seekBar);
        textview = (TextView) view.findViewById(R.id.seektext);

        seekBar.setOnSeekBarChangeListener(this);*/




       /* mLayout = view.findViewById(R.id.Layout);
        mDefaultColor = ContextCompat.getColor(new MainActivity(), R.color.ColorButton);
        ButtonColor = (Button) view.findViewById(R.id.Color_Picker);
        ButtonColor.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                openColorPicker();
            }
        });*/

        textView =  (TextView) view.findViewById(R.id.seektext);
        seekBar =  (SeekBar) view.findViewById(R.id.seekBar);
        seekBar.setMax(max - min);
        seekBar.setProgress(current);
        textView.setText(""+current);



        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                current = progress + min;
                textView.setText(""+ current);
                //textView.setText(""+String.valueOf(progress));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }


        });




        return view;

    }




}
