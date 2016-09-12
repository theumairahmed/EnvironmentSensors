package theumairahmed.com.androidenvironmentsensors;

import android.app.Fragment;

/**
 * Created by Umair Ahmed on 9/3/2016.
 */

//Nice piece of Fish :-)
public class MainActivity extends SingleFragmentActivity {
    @Override
    public Fragment createFragment() {
        return new SensorsFragment();
    }
}
