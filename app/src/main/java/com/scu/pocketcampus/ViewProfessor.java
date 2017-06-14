package com.scu.pocketcampus;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.scu.pocketcampus.model.Professor;
import com.scu.pocketcampus.model.Rate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewProfessor extends AppCompatActivity {
    private DatabaseReference mDatabase;
    final List<Rate> ratingtList = new ArrayList<>();
    private double overallRating =0. ,levelOfDifficulty = 0.;
    public Float rating = 0f;
    public Integer difficultyLevel= 0;
    private Button rateButton;
    int count = 0;
    String remark = "";
    Professor prof;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, SearchProfessor.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_professor);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Bundle extras = getIntent().getExtras();
        prof = (Professor) extras.getParcelable("Prof Object");
        setTitle(prof.getFirstName()+" "+prof.getLastName());
        TextView deptName = (TextView) findViewById(R.id.nameOfDepartment_textView);
        deptName.setText(prof.getDepartmentName());
        final  Intent intent = new Intent(this,ViewProfessor.class);
        final List<String> commentList = new ArrayList<String>();

        final ListView commentlistview = (ListView) findViewById(R.id.commentListView);
        final ArrayAdapter<String> comment_adapter = new ArrayAdapter<String>(this, R.layout.my_list, commentList);
        commentlistview.setAdapter(comment_adapter);


        mDatabase.child("rating").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> snapshots =  dataSnapshot.getChildren();
                for(DataSnapshot snapshot : snapshots){
                    ratingtList.add(snapshot.getValue(Rate.class));
                }

                for(Rate rate: ratingtList){

                    if(rate.getProfId().equals(prof.getProfessorId())){
                        rating += rate.getRating();
                        difficultyLevel += rate.getDifficultyLevel();
                        if(rating == 1){
                            remark = "Awful";
                        }
                        else if(rating == 2){
                            remark = "Poor";
                        }
                       else if(rating == 3){
                            remark = "Fair";
                        }
                        else if(rating == 4){
                            remark = "Good";
                        }
                        else{
                            remark = "Awesome";
                        }
                        commentList.add(rate.getRating() +" / 5        "+remark+"                          " +
                                ""+rate.getComment());
                        count++;
                    }
                }
                if(count!=0) {
                    rating = rating / count;
                    difficultyLevel = (difficultyLevel / 10) / count;
                }
                else{
                    rating = 0f;
                    difficultyLevel = 0;
                    commentList.add("No Comments Found");
                }

               TextView ratingTextView = (TextView)findViewById(R.id.textViewRating);
                ratingTextView.setText(""+rating +" / 5");
                TextView difficultyTextView = (TextView)findViewById(R.id.textViewDiffiuty);
                difficultyTextView.setText(""+difficultyLevel +" / 10");
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

     public void onClick(View v){
         if (v.getId() == R.id.rate_button) {
             Intent intent = new Intent(this, RateProfessor.class);
             intent.putExtra("Prof Id",(Parcelable) prof);
             startActivity(intent);
         }
     }
}
