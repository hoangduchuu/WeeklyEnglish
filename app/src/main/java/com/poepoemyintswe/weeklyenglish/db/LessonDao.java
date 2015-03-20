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

  public RealmList<SentenceDao> getSentences() {
    return sentences;
  }

  public void setSentences(RealmList<SentenceDao> sentences) {
    this.sentences = sentences;
  }
}

