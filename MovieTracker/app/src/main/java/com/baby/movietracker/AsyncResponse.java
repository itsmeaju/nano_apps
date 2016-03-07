package com.baby.movietracker;


import java.util.List;

/**
 * Created by Baby on 3/7/2016.
 */
public interface AsyncResponse {

    void onTaskCompleted( List<Movie> results );

}