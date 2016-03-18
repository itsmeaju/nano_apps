package com.baby.popularmovies1.ui.settings;


import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceFragment;

import com.baby.popularmovies1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends PreferenceFragment {


    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

    }
}
