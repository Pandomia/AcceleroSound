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

import project.testcompany.com.AcceleroSound.R;
import yuku.ambilwarna.AmbilWarnaDialog;


public class SettingsFragment extends Fragment {
    TextView textView;
    SeekBar seekBar;

    TextView speedText;
    SeekBar speedBar;

    Context context;
    private static final String FILE_RADIUS = "radiusball.txt";

    Button colorPicker;
    int defColor;

    static int minSize = 50, maxSize = 200, currentSize = 100;
    static int minSpeed = 1, maxSpeed = 8, currentSpeed = 5;

    static int size = currentSize;
    static int speed = currentSpeed;
    static int ballColor = 0xff0000ff;//Color.BLUE;

    public interface Settings {
        int iSize = size;
        int iSpeed = speed;
        int iColor = ballColor;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        textView = (TextView) view.findViewById(R.id.seektext);
        seekBar = (SeekBar) view.findViewById(R.id.seekBar);

        speedText = view.findViewById(R.id.speedText);
        speedBar = view.findViewById(R.id.speedBar);

        colorPicker = (Button) view.findViewById(R.id.color_picker);

        seekBar.setMin(minSize);
        seekBar.setMax(maxSize);
        seekBar.setProgress(currentSize);
        textView.setText(currentSize + "px");

        speedBar.setMin(minSpeed);
        speedBar.setMax(maxSpeed);
        speedBar.setProgress(currentSpeed);
        speedText.setText(currentSpeed + "x");


        colorPicker.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                openColorPicker();

            }
        });



        Button btn1 = (Button) view.findViewById(R.id.Size_Picker);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                size = currentSize;
                speed = currentSpeed;
                context = getContext();
                int RadiusBall= currentSize;
                int SpeedBall = currentSpeed;
                String ColorBall = String.valueOf(ballColor);
                FileOutputStream Fos=null;


                try {
                    Fos = context.openFileOutput(FILE_RADIUS,Context.MODE_PRIVATE);
                    Fos.write(String.valueOf(RadiusBall).getBytes());
                    Fos.write(",".getBytes());
                    Fos.write(String.valueOf(SpeedBall).getBytes());
                    Fos.write(",".getBytes());
                    Fos.write(String.valueOf(ColorBall).getBytes());


                    Log.e("BanaaSaus", "NukeT4wn" + context.getFilesDir());
                    Log.e("Main" , String.valueOf(SettingsFragment.currentSize));

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

                Log.e("current", (String.valueOf(currentSize)));
                Log.e("size", (String.valueOf(size)));


            }



        });



        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentSize = progress;
                textView.setText(currentSize + "px");
                //textView.setText(""+String.valueOf(progress));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }


        });

        speedBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentSpeed = progress;
                speedText.setText(currentSpeed+"x");
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


    public void openColorPicker(){
        AmbilWarnaDialog cPicker = new AmbilWarnaDialog(getActivity(), ballColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                ballColor = color;
            }
        });
        cPicker.show();

    }




}
