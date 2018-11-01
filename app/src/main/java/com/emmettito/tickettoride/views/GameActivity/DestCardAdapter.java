package com.emmettito.tickettoride.views.GameActivity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


public class DestCardAdapter extends RecyclerView.Adapter<DestCardAdapter.DestCardHolder> {
    public class DestCardHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @Override
        public void onClick(View view) {

        }

        public DestCardHolder(View v) {
            super(v);
        }

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public DestCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(DestCardHolder holder, int position) {

    }


}
