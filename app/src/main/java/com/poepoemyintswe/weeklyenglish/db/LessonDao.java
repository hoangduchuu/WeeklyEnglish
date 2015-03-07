package com.poepoemyintswe.weeklyenglish.db;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by poepoe on 8/3/15.
 */
public class LessonDao extends RealmObject {
  private int id;
  private RealmList<SentenceDao> sentences;
  private String title;
  private String explanation;

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public void setExplanation(String explanation) {
    this.explanation = explanation;
  }

  public String getExplanation() {
    return explanation;
  }

  public void setSentences(RealmList<SentenceDao> sentences) {
    this.sentences = sentences;
  }

  public RealmList<SentenceDao> getSentences() {
    return sentences;
  }
}

