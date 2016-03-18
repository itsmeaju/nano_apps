package com.baby.popularmovies1.api;

import java.util.List;

/**
 * Created by Baby on 18.03.16.
 */
public class ListResponse {
    List<com.baby.popularmovies1.api.MovieModel> results;
    int page;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<com.baby.popularmovies1.api.MovieModel> getResults() {
        return results;
    }

    public void setResults(List<com.baby.popularmovies1.api.MovieModel> results) {
        this.results = results;
    }
}
