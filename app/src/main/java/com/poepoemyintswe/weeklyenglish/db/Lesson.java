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

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getExplanation() {
    return explanation;
  }

  public void setExplanation(String explanation) {
    this.explanation = explanation;
  }

  public RealmList<Sentence> getSentences() {
    return sentences;
  }

  public void setSentences(RealmList<Sentence> sentences) {
    this.sentences = sentences;
  }
}

