package com.poepoemyintswe.weeklyenglish.db;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by poepoe on 8/3/15.
 */
public class Lesson extends RealmObject {
  public int id;
  public RealmList<Sentence> sentences;
  public String title;
  public String explanation;
}

