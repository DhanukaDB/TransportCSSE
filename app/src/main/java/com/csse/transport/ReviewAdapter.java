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

public class ReviewAdapter extends ArrayAdapter {

    private Activity rContext;
    List<Review> reviewList;

    public ReviewAdapter(Activity rContext, List<Review> reviewList){
        super(rContext,R.layout.list_review, reviewList);
        this.rContext = rContext;
        this.reviewList = reviewList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = rContext.getLayoutInflater();
        View listReviewView = layoutInflater.inflate(R.layout.list_review, null,true);

        TextView tvRating = listReviewView.findViewById(R.id.tvRevRate);
        TextView tvRevEmail = listReviewView.findViewById(R.id.tvRevEmail);
        TextView tvRevOther = listReviewView.findViewById(R.id.tvRevOther);

       Review review = reviewList.get(position);

        tvRating.setText(review.getRate());
        tvRevEmail.setText(review.getEmail());
        tvRevOther.setText(review.getOther());

        return listReviewView;
    }
}
