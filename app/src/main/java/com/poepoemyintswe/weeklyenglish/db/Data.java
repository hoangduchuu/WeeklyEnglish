package com.poepoemyintswe.weeklyenglish.db;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by poepoe on 20/3/15.
 */
public class Data extends RealmObject {
  private RealmList<Lesson> lessons;

  public RealmList<Lesson> getLessons() {
    return lessons;
  }

  public void setLessons(RealmList<Lesson> lessons) {
    this.lessons = lessons;
  }
}
