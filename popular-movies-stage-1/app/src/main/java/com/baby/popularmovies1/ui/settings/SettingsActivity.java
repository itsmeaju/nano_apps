package com.baby.popularmovies1.ui.settings;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.baby.popularmovies1.R;
public class SettingsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return (super.onOptionsItemSelected(item));
    }
}
