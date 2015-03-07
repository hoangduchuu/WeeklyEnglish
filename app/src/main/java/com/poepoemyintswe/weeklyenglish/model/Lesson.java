package com.poepoemyintswe.weeklyenglish.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poepoe on 1/3/15.
 */
public class Lesson {
  public int id;
  public List<Sentence> sentences = new ArrayList<Sentence>();
  public String title;
  public String explanation;
}
