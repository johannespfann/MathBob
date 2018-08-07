package de.pfann.mentalcalculator.app.setttings;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by jopfann on 04.09.14.
 */
public class SettingsPreferenceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new SettingsPreferenceFragment()).commit();
    }
}
