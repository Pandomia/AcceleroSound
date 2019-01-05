package project.testcompany.com.AcceleroSound;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import project.testcompany.com.test1.R;

public class SettingsFragment extends Fragment{
    TextView textView;
    SeekBar seekBar;

    int min = 0, max = 80, current = 40;
    //int CurrentVelocity = getResources().getInteger(R.integer.VelocitySpeed);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_settings,container,false);
        /*SeekBar seekBar = (SeekBar) view.findViewById(R.id.seekBar);
        textview = (TextView) view.findViewById(R.id.seektext);

        seekBar.setOnSeekBarChangeListener(this);*/


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
