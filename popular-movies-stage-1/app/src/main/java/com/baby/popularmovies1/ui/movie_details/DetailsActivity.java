package com.baby.popularmovies1.ui.movie_details;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.baby.popularmovies1.R;


public class DetailsActivity extends AppCompatActivity {

    Bundle params;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        com.baby.popularmovies1.ui.movie_details.DetailsFragment fragment=new com.baby.popularmovies1.ui.movie_details.DetailsFragment();
        Fragment detailsFragment=getFragmentManager().findFragmentByTag("details");
        if (detailsFragment==null) {
            params=getIntent().getExtras();
            fragment.setArguments(params);

            getFragmentManager().beginTransaction().add(R.id.container,fragment,"details").commit();
        }

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
