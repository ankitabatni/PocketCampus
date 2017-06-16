package com.scu.pocketcampus.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.scu.pocketcampus.R;
import com.scu.pocketcampus.model.Rate;

import java.util.ArrayList;

import static com.scu.pocketcampus.R.id.commentTextView;

/**
 * Created by ankita on 6/15/17.
 */

public class ProfCommentListApadter  extends ArrayAdapter<Rate> {

    public Float starRatingValue = 0f;

    public ProfCommentListApadter(Context context, ArrayList<Rate> profrating) {
        super(context, 0, profrating);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Rate ratingInfo = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.comment_list_item, parent, false);
        }

        // Lookup view for data population
        TextView ratingString = (TextView) convertView.findViewById(R.id.stringRating);
        TextView commentText = (TextView) convertView.findViewById(commentTextView);
        TextView ratingDate = (TextView) convertView.findViewById(R.id.dateText);
        TextView courseNameText = (TextView) convertView.findViewById(R.id.courseNameTextView);
        RatingBar ratingBarView = (RatingBar) convertView.findViewById(R.id.ratingBar2);
        // Populate the data into the template view using the data object
        ratingBarView.setRating(ratingInfo.getRating());
        ratingBarView.setEnabled(false);
        ratingDate.setText(ratingInfo.getDate());
        Log.d("comment - ", ratingInfo.getComment());
        Log.d("couse name - ", ratingInfo.getCourseCode());
        commentText.setText(ratingInfo.getComment());
        courseNameText.setText(ratingInfo.getCourseCode());



        starRatingValue = ratingInfo.getRating();
        String remark = "";
        if(starRatingValue == 1){
            remark = "Awful";
        }
        else if(starRatingValue == 2){
            remark = "Poor";
        }
        else if(starRatingValue == 3){
            remark = "Fair";
        }
        else if(starRatingValue == 4){
            remark = "Good";
        }
        else{
            remark = "Awesome";
        }
        ratingString.setText(remark);
        // Return the completed view to render on screen
        return convertView;
    }


}
