package com.poepoemyintswe.weeklyenglish.db;

import io.realm.RealmObject;

/**
 * Created by poepoe on 8/3/15.
 */
public class SentenceDao extends RealmObject {
  private String english;
  private String myanmar;

  public void setEnglish(String english) {
    this.english = english;
  }

  public String getEnglish() {
    return english;
  }

  public void setMyanmar(String myanmar) {
    this.myanmar = myanmar;
  }

  public String getMyanmar() {
    return myanmar;
  }
}
