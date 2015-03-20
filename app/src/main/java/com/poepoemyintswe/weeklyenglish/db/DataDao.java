package com.poepoemyintswe.weeklyenglish.db;

import com.google.gson.annotations.SerializedName;
import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by poepoe on 20/3/15.
 */
public class DataDao extends RealmObject {

  @SerializedName("lessons") private RealmList<LessonDao> lessons;
}
