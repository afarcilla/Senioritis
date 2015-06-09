package edu.ucsb.cs.cs185.afarcilla.senioritis;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.CardHolder> {

    private List<ClassStruct> mDataset;

    public static class CardHolder extends RecyclerView.ViewHolder {
        public View mView;
        public CardHolder(View v) {
            super(v);
            mView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecycleAdapter(List<ClassStruct> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.class_card_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        CardHolder vh = new CardHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(CardHolder holder, int position) {
        final int p = position;
        String className = mDataset.get(position).className;
        String targetGrade = mDataset.get(position).desiredGrade;

        TextView class_title = (TextView) holder.mView.findViewById(R.id.classTitle);
        TextView target = (TextView) holder.mView.findViewById(R.id.targetGrade);
        class_title.setText(className);
        target.setText(targetGrade);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent touchIntent = new Intent(context, ClassActivity.class);
                String className = mDataset.get(p).className;
                Float units = mDataset.get(p).units;
                String desiredGrade = mDataset.get(p).desiredGrade;
                Float homeworkPercent = mDataset.get(p).homeworkPercent;
                Float midTermPercent = mDataset.get(p).midTermPercent;
                Float finalPercent = mDataset.get(p).finalPercent;
                Float projectsPercent = mDataset.get(p).projectsPercent;
                Float otherPercent = mDataset.get(p).otherPercent;

                touchIntent.putExtra("className", className);
                touchIntent.putExtra("units", units);
                touchIntent.putExtra("desiredGrade", desiredGrade);
                touchIntent.putExtra("homeworkPercent", homeworkPercent);
                touchIntent.putExtra("midtermPercent", midTermPercent);
                touchIntent.putExtra("finalPercent", finalPercent);
                touchIntent.putExtra("projectsPercent", projectsPercent);
                touchIntent.putExtra("otherPercent", otherPercent);

                context.startActivity(touchIntent);
            }});

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}

