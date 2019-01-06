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
import android.widget.Toast;
import android.os.Environment;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import project.testcompany.com.test1.R;
import yuku.ambilwarna.AmbilWarnaDialog;


public class SettingsFragment extends PreferenceFragmentCompat {
    TextView textView;
    SeekBar seekBar;
    Context context;
    private static final String FILE_RADIUS = "radiusball.txt";


    static int min = 50, max = 200, current = 100;

    static int size = current;

    static int color = Color.BLUE;

    public interface Settings {
        int isize = size;
        int icolor = color;
    }


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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        textView = (TextView) view.findViewById(R.id.seektext);
        seekBar = (SeekBar) view.findViewById(R.id.seekBar);
        seekBar.setMax(max - min);
        seekBar.setProgress(current);
        textView.setText("" + current);




        Button btn1 = (Button) view.findViewById(R.id.Size_Picker);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                size = current;
                context = getContext();
                int RadiusBall= current;
                FileOutputStream Fos=null;


                try {
                    Fos = context.openFileOutput(FILE_RADIUS,Context.MODE_PRIVATE);
                    Fos.write(String.valueOf(RadiusBall).getBytes());
                    Fos.write(",".getBytes());
                    Fos.write(String.valueOf("1").getBytes());

                    Log.e("BanaaSaus", "NukeT4wn" + context.getFilesDir());
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




                //FoS = openFileOutput(FILE_RADIUS, MODE_PRIVATE);

                Log.e("current", (String.valueOf(current)));
                Log.e("size", (String.valueOf(size)));


            }



        });



        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                current = progress;
                textView.setText("" + current);
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
