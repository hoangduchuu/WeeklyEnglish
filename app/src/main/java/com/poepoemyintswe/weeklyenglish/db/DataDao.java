package com.poepoemyintswe.weeklyenglish.db;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by poepoe on 20/3/15.
 */
public class DataDao extends RealmObject {
  private RealmList<LessonDao> lessons;
}
