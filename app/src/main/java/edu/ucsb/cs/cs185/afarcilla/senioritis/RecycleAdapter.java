package edu.ucsb.cs.cs185.afarcilla.senioritis;

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
        String className = mDataset.get(position).className;

        TextView class_title = (TextView) holder.mView.findViewById(R.id.classTitle);
        class_title.setText(className);

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}

