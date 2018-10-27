package com.anoop.cst.services;

import java.util.List;

import com.anoop.cst.models.JiraIssue;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface JiraBasicAPI {

    @GET("/rest/api/2/search")
    Observable<List<JiraIssue>> getIssues(@Query("query") String query);

}