package com.poepoemyintswe.weeklyenglish.api;

import com.poepoemyintswe.weeklyenglish.db.Data;
import retrofit.Callback;
import retrofit.http.GET;

import static com.poepoemyintswe.weeklyenglish.Config.LESSONS;
import static com.poepoemyintswe.weeklyenglish.Config.LESSON_COUNT;

/**
 * Created by poepoe on 7/3/15.
 */
public interface LessonService {
  @GET(LESSONS) void getLessons(Callback<Data> callback);

  @GET(LESSONS + LESSON_COUNT) void getCount(Callback<Integer> count);
}
