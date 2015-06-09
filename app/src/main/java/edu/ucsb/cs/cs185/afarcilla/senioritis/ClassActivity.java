package edu.ucsb.cs.cs185.afarcilla.senioritis;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ClassActivity  extends Activity {

    private RecyclerView mRecyclerView;
    private ClassRecycleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_layout);

        TextView title = (TextView) findViewById(R.id.title);
        final List<AssignmentStruct> myDataset = new ArrayList<>();

        SharedPreferences preferences = this.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        int assignmentsNum = preferences.getInt("assignmentsNum", 1);


        for(int i = 0; i < assignmentsNum; i++){
            //add assignment data
            myDataset.add(new AssignmentStruct("test1", "Homework", 0));

        }

        if(!myDataset.isEmpty()) {
            TextView emptyText = (TextView) findViewById(R.id.empty);
            emptyText.setText("");
        }

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String className = extras.getString("className");
            title.setText(className);


        }

        mRecyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new ClassRecycleAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        TextView grade = (TextView) findViewById(R.id.grade);




        String desiredGrade = extras.getString("desiredGrade");
        Float homeworkPercent = extras.getFloat("homeworkPercent");
        Float midtermPercent = extras.getFloat("midtermPercent");
        Float finalPercent = extras.getFloat("finalPercent");
        Float projectsPercent = extras.getFloat("projectsPercent");
        Float otherPercent = extras.getFloat("otherPercent");

        int gradeValue = 0;
        switch (desiredGrade){
            case "A+":
                gradeValue = 97;
                break;
            case "A":
                gradeValue = 93;
                break;
            case "A-":
                gradeValue = 90;
                break;
            case "B+":
                gradeValue = 87;
                break;
            case "B":
                gradeValue = 83;
                break;
            case "B-":
                gradeValue = 80;
                break;
            case "C+":
                gradeValue = 77;
                break;
            case "C":
                gradeValue = 73;
                break;
            case "C-":
                gradeValue = 70;
                break;
            case "D+":
                gradeValue = 67;
                break;
            case "D":
                gradeValue = 63;
                break;
            case "D-":
                gradeValue = 60;
                break;
            case "F":
                gradeValue = 59;
        }

        Float percentNeeded = 100 * ((gradeValue - (homeworkPercent + midtermPercent + projectsPercent + otherPercent)) / finalPercent);
        grade.setText(String.valueOf(percentNeeded) + "%");


    }

    public void backButton(View v){
        this.finish();
    }
}
