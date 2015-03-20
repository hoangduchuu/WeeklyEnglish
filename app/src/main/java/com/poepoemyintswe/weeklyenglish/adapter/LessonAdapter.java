package com.poepoemyintswe.weeklyenglish.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.poepoemyintswe.weeklyenglish.R;
import com.poepoemyintswe.weeklyenglish.db.LessonDao;
import java.util.List;

/**
 * Created by poepoe on 8/3/15.
 */
public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.ViewHolder> {

  private List<LessonDao> data;

  public LessonAdapter(List<LessonDao> data) {
    setHasStableIds(true);
    this.data = data;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    View view =
        LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_lesson, viewGroup, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(ViewHolder viewHolder, int i) {
    viewHolder.bindLesson(getItem(i));
  }

  @Override public int getItemCount() {
    return data.size();
  }

  private LessonDao getItem(int position) {
    return data.get(position);
  }

  public void add(LessonDao lesson) {
    insert(lesson, data.size());
  }

  public void insert(LessonDao lesson, int position) {
    data.add(position, lesson);
    notifyItemInserted(position);
    notifyDataSetChanged();
  }

  public void clear(int size) {
    data.subList(0, size).clear();
    notifyItemRangeRemoved(0, size);
    notifyDataSetChanged();
  }

  public void clearAll() {
    data.clear();
    notifyDataSetChanged();
  }

  public void remove(int position) {
    data.remove(position);
    notifyItemRemoved(position);
    notifyDataSetChanged();
  }

  public void addAll(List<LessonDao> Data) {
    int startIndex = data.size();
    data.addAll(startIndex, Data);
    notifyItemRangeInserted(startIndex, Data.size());
    notifyDataSetChanged();
  }

  public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @InjectView(R.id.id) TextView id;
    @InjectView(R.id.title) TextView title;
    @InjectView(R.id.explanation) TextView explanation;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.inject(this, itemView);
    }

    public void bindLesson(LessonDao lesson) {
      //id.setText(Integer.toString(lesson.id));
      //title.setText(lesson.title);
      //explanation.setText(lesson.explanation);
    }

    @Override public void onClick(View v) {

    }
  }
}
