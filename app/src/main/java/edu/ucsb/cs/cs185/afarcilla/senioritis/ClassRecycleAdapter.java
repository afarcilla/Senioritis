package edu.ucsb.cs.cs185.afarcilla.senioritis;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ClassRecycleAdapter extends RecyclerView.Adapter<ClassRecycleAdapter.CardHolder> {

    private List<AssignmentStruct> mDataset;

    public static class CardHolder extends RecyclerView.ViewHolder {
        public View mView;
        public CardHolder(View v) {
            super(v);
            mView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ClassRecycleAdapter(List<AssignmentStruct> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.assignment_card_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        CardHolder vh = new CardHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(CardHolder holder, int position) {
        String AssignmentName = mDataset.get(position).assignmentTitle;
        Integer grade = mDataset.get(position).score;

        TextView assignmentGrade = (TextView) holder.mView.findViewById(R.id.gradeNumber);
        TextView assignment = (TextView) holder.mView.findViewById(R.id.AssignmentTitle);
        assignment.setText(AssignmentName);
        assignmentGrade.setText(Integer.toString(grade));


    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}

