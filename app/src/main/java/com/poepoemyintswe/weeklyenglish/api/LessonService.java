package com.poepoemyintswe.weeklyenglish.api;

import com.poepoemyintswe.weeklyenglish.db.DataDao;
import retrofit.Callback;
import retrofit.http.GET;

import static com.poepoemyintswe.weeklyenglish.Config.LESSONS;

/**
 * Created by poepoe on 7/3/15.
 */
public interface LessonService {
  @GET(LESSONS) void getLessons(Callback<DataDao> callback);
}
