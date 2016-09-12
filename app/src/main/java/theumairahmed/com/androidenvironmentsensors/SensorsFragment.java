package theumairahmed.com.androidenvironmentsensors;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


/**
 * Created by Umair Ahmed on 9/3/2016.
 */

//Github Comment to test changes
    //Github 2nd comment to test changes
public class SensorsFragment extends Fragment implements SensorEventListener{
   private TextView tempTv;
   private TextView humTv;
   private TextView presTv;
   private TextView lumTv;
    private SensorManager mSensorManager;

    private Sensor mTempSensor;
    private Sensor mHumSensor;
    private Sensor mPresSensor;
    private Sensor mLumSensor;

    private GraphView mGraphView;


    @Override
    public void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mTempSensor, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mHumSensor, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mPresSensor, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mLumSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.sensor_fragment2,container,false);
       tempTv = (TextView) v.findViewById(R.id.temp_valTv);
        humTv = (TextView) v.findViewById(R.id.hum_valTv);
        presTv = (TextView) v.findViewById(R.id.pres_valTv);
        lumTv = (TextView) v.findViewById(R.id.lum_valTv);

        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mTempSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        mHumSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        mPresSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        mLumSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);


         //Sensor Support Checking Code
        if (mTempSensor==null)
            tempTv.setText("Temperature Sensor Not Supported");

        if (mHumSensor==null)
           humTv.setText("Humidity Sensor Not Supported");

        if (mPresSensor==null)
            presTv.setText("Pressure Sensor Not Supported");

        if (mLumSensor==null)
            lumTv.setText("Light Sensor Not Supported");

      mGraphView = (GraphView) v.findViewById(R.id.graphView);
        mGraphView.setTitle("Demo Graph");

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {new DataPoint(0,1),
                new DataPoint(1,5),
                new DataPoint(2,3),
                new DataPoint(3,2),
                new DataPoint(4,6)
        });

        series.setDrawBackground(true);
        series.setColor(Color.WHITE);
        series.setThickness(4);
        series.setBackgroundColor(0x40FFFFFF);
        mGraphView.addSeries(series);

        return v;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
     switch (event.sensor.getType())
     {
         case Sensor.TYPE_AMBIENT_TEMPERATURE:
             tempTv.setText((int)event.values[0]+"");
             break;

         case Sensor.TYPE_RELATIVE_HUMIDITY:
             humTv.setText((int)event.values[0]+"");
             break;

         case Sensor.TYPE_PRESSURE:
             presTv.setText((int)event.values[0]+"");
             break;

         case Sensor.TYPE_LIGHT:
             lumTv.setText((int)event.values[0]+"");
             break;

     }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
