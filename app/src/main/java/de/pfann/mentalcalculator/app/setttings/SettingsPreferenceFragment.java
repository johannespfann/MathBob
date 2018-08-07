package de.pfann.mentalcalculator.app.setttings;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import de.pfann.mentalcalculator.app.R;
/**
 * Created by jopfann on 02.09.14.
 */
public class SettingsPreferenceFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference);
    }
}
