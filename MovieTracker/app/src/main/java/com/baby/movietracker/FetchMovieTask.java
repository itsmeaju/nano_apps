package com.baby.movietracker;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by Baby on 3/7/2016.
 */

public class FetchMovieTask extends AsyncTask<String, Void, List<Movie>> {

    private final String LOG_TAG = FetchMovieTask.class.getSimpleName();
    public AsyncResponse delegate;
    private final String M_POSTER_BASE = "http://image.tmdb.org/t/p/";
    private final String M_POSTER_SIZE = "w185";

public FetchMovieTask(AsyncResponse delegate){
        this.delegate = delegate;
        }
private List<Movie> getMovieDataFromJson(String movieJsonStr)
        throws JSONException {

// These are the names of the JSON objects that need to be extracted.
    final String M_RESULTS = "results";
    final String M_TITLE = "title";
    final String M_OVERVIEW = "overview";
    final String M_POSTER = "poster_path";
    final String M_RATING = "vote_average";
    final String M_RELEASE_DATE = "release_date";
        String[] resultStrs=null;

        JSONObject movieJson = new JSONObject(movieJsonStr);
        JSONArray resultArray = movieJson.getJSONArray(M_RESULTS);

        List<Movie> movies = new ArrayList<Movie>();


      /*      // Data is fetched in Celsius by default.
            // If user prefers to see in Fahrenheit, convert the values here.
            // We do this rather than fetching in Fahrenheit so that the user can
            // change this option without us having to re-fetch the data once
            // we start storing the values in a database.
            SharedPreferences sharedPrefs =
                    PreferenceManager.getDefaultSharedPreferences(getActivity());
            String unitType = sharedPrefs.getString(
                    getString(R.string.pref_units_key),
                    getString(R.string.pref_units_rating));     */

        for(int i = 0; i < resultArray.length(); ++i) {

        JSONObject oneMovie = resultArray.getJSONObject(i);
        String title = oneMovie.getString(M_TITLE);
        String release_date = getYear(oneMovie.getString(M_RELEASE_DATE));
        String overview = oneMovie.getString(M_OVERVIEW);
        String rating = oneMovie.getString(M_RATING);
        String poster = oneMovie.getString(M_POSTER);

        movies.add(new Movie(title, poster, overview, rating, release_date));
        }
        return movies;

        }


@Override
protected List<Movie> doInBackground(String... params) {

        if (params.length == 0) {
        return null;
        }

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String movieJsonStr = null;




        try {
final String BASE_URL = "http://api.themoviedb.org/3/discover/movie?";
final String SORT_BY = "sort_by";
final String KEY = "api_key";
        String sortBy = params[0];

        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
        .appendQueryParameter(SORT_BY, sortBy)
        .appendQueryParameter(KEY, BuildConfig.MOVIE_DB_API_KEY)
        .build();

        URL url = new URL(builtUri.toString());
        // Create the request to OpenWeatherMap, and open the connection
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.connect();

        // Read the input stream into a String
        InputStream inputStream = urlConnection.getInputStream();
        StringBuffer buffer = new StringBuffer();
        if (inputStream == null) {
        // Nothing to do.
        return null;
        }
        reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = reader.readLine()) != null) {
        // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
        // But it does make debugging a *lot* easier if you print out the completed
        // buffer for debugging.
        buffer.append(line + "\n");
        }

        if (buffer.length() == 0) {
        // Stream was empty.  No point in parsing.
        return null;
        }
        movieJsonStr = buffer.toString();
        } catch (IOException e) {
        Log.e(LOG_TAG, "Error ", e);
        return null;
        } finally {
        if (urlConnection != null) {
        urlConnection.disconnect();
        }
        if (reader != null) {
        try {
        reader.close();
        } catch (final IOException e) {
        Log.e(LOG_TAG, "Error closing stream", e);
        }
        }
        }

        try {
        return getMovieDataFromJson(movieJsonStr);
        } catch (JSONException e) {
        Log.e(LOG_TAG, e.getMessage(), e);
        e.printStackTrace();
        }

        return null;
        }
private String getYear(String date){
final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
final Calendar cal = Calendar.getInstance();
        try {
        cal.setTime(df.parse(date));
        } catch (ParseException e) {
        e.printStackTrace();
        }

        return Integer.toString(cal.get(Calendar.YEAR));
        }
@Override
protected void onPostExecute(List<Movie> results) {
        if (results != null) {
        delegate.onTaskCompleted(results);
        } }
        }
