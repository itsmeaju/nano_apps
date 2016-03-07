package com.baby.movietracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class DetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new DetailFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class DetailFragment extends Fragment {

        private static final String LOG_TAG = DetailFragment.class.getSimpleName();
        Movie movie;
         private String mMovieStr;

        public DetailFragment() {
            setHasOptionsMenu(true);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

            Intent intent = getActivity().getIntent();
            if (intent != null && intent.hasExtra("movies_details")) {

                movie = (Movie)intent.getParcelableExtra("movies_details");
                DisplayInfo(rootView);
            }

            return rootView;
        }

        private void DisplayInfo(View v){
            TextView title = (TextView) v.findViewById(R.id.movie_title_view);
            ImageView poster = (ImageView) v.findViewById(R.id.poster_image_view);
            TextView releaseDate = (TextView) v.findViewById(R.id.release_date);
            TextView ratings = (TextView) v.findViewById(R.id.ratings_view);
            TextView overview = (TextView) v.findViewById(R.id.synopsis_view);

            title.setText(movie.getTitle());
            Picasso.with(getActivity()).load(movie.getPoster()).into(poster);
            releaseDate.setText(movie.getReleaseDate());
            ratings.setText(movie.getVoteAverage() + "/10");
            overview.setText(movie.getOverview());
        }


    }
}
