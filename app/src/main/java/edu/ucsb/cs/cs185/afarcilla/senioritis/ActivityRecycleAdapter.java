package edu.ucsb.cs.cs185.afarcilla.senioritis;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ActivityRecycleAdapter extends RecyclerView.Adapter<ActivityRecycleAdapter.CardHolder> {

    private List<ActivityStruct> mDataset;

    public static class CardHolder extends RecyclerView.ViewHolder {
        public View mView;
        public CardHolder(View v) {
            super(v);
            mView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ActivityRecycleAdapter(List<ActivityStruct> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_card_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        CardHolder vh = new CardHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(CardHolder holder, int position) {
        String ActivityName = mDataset.get(position).activityName;
        Integer importance = mDataset.get(position).priority + 1;

        TextView activityName = (TextView) holder.mView.findViewById(R.id.ActivityTitle);
        TextView importanceNumber = (TextView) holder.mView.findViewById(R.id.importanceNumber);
        activityName.setText(ActivityName);
        importanceNumber.setText(Integer.toString(importance));

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}

