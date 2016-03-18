package com.baby.popularmovies1.api;

import java.util.List;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Baby on 18.03.16.
 */
public class APIClient implements RequestInterceptor {
    public static  final String API_KEY="tmdb_api_key_to_be_added_here";
    public static  final String KEY_PARAM="api_key";
    public static final String BASE_URL="https://api.themoviedb.org/3";
    private static APIMethods api;
    private static APIClient instance;
    private APIClient() {

    }
    public static  APIClient getInstance(){
        if (instance==null){
            instance=new APIClient();
        }
        return instance;
    }
    private APIMethods getApi(){
        if (api==null){
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setRequestInterceptor(this)
                    .setEndpoint(APIClient.BASE_URL)
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .build();

            api=restAdapter.create(APIMethods.class);
        }
        return api;
    }
    @Override
    public void intercept(RequestFacade request) {
        request.addQueryParam(APIClient.KEY_PARAM,APIClient.API_KEY);
    }
    public List<com.baby.popularmovies1.api.MovieModel> getPopular(){
        com.baby.popularmovies1.api.ListResponse result=getApi().getPopular();
        return  result.getResults();
    }
    public List<com.baby.popularmovies1.api.MovieModel> getTopRated(){
        com.baby.popularmovies1.api.ListResponse result=getApi().getTopRated();
        return  result.getResults();
    }

    public com.baby.popularmovies1.api.MovieModel getMovieDetails(String id) {
        com.baby.popularmovies1.api.MovieModel m=getApi().getMovieDetails(id);
        return m;
    }

    private   interface APIMethods{
        @GET("/movie/popular")
        com.baby.popularmovies1.api.ListResponse getPopular();
        @GET("/movie/top_rated")
        com.baby.popularmovies1.api.ListResponse getTopRated();
        @GET("/movie/{id}")
        com.baby.popularmovies1.api.MovieModel getMovieDetails(@Path("id") String id);
    }
}
