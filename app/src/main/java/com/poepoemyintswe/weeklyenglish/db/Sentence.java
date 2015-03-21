package com.poepoemyintswe.weeklyenglish.db;

import io.realm.RealmObject;

/**
 * Created by poepoe on 8/3/15.
 */
public class Sentence extends RealmObject {
  private String english;
  private String myanmar;

  public String getEnglish() {
    return english;
  }

  public void setEnglish(String english) {
    this.english = english;
  }

  public String getMyanmar() {
    return myanmar;
  }

  public void setMyanmar(String myanmar) {
    this.myanmar = myanmar;
  }
}
