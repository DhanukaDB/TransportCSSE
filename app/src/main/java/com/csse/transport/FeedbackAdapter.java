package com.csse.transport;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class FeedbackAdapter extends ArrayAdapter{

          private Activity fContext;
          List<Feedback> feedbackList;

       public FeedbackAdapter(Activity fContext, List<Feedback> feedbackList){
             super(fContext, R.layout.list_feedbacks,feedbackList);
             this.fContext = fContext;
             this.feedbackList = feedbackList;
       }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = fContext.getLayoutInflater();
        View listFeedbackView = layoutInflater.inflate(R.layout.list_feedbacks, null, true);

        TextView tvEmail = listFeedbackView.findViewById(R.id.tvEmail);
        TextView tvDescription = listFeedbackView.findViewById(R.id.tvDescription);

        Feedback feedback = feedbackList.get(position);
        tvEmail.setText(feedback.getEmail());
        tvDescription.setText(feedback.getFeedback());

        return listFeedbackView;

    }
}


