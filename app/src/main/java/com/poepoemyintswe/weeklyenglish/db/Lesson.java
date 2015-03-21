package com.poepoemyintswe.weeklyenglish.db;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by poepoe on 8/3/15.
 */
public class Lesson extends RealmObject {
  private int id;
  private RealmList<Sentence> sentences;
  private String title;
  private String explanation;

  public int getId() {
    return id;
  }

  public RealmList<Sentence> getSentences() {
    return sentences;
  }

  public String getExplanation() {
    return explanation;
  }

  public String getTitle() {
    return title;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setSentences(RealmList<Sentence> sentences) {
    this.sentences = sentences;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setExplanation(String explanation) {
    this.explanation = explanation;
  }
}

