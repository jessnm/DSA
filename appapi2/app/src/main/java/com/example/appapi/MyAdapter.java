//package com.vogella.android.recyclerview;
package com.example.appapi;

import java.util.ArrayList;
import java.util.List;

//import android.support.v7.widget.RecyclerView;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Track> values;
    private RecyclerView recyclerView;
    TrackInterface trackInterface;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    //viewholder tiene lo que es visible en el reci-yclevie
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public TextView txtFooter;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
        }
    }

    public void update(int position, String item) {
        /*values.add(position, track);
        notifyItemInserted(position);/*/
    }

    public void delete(int position) {
        String id = values.get(position).getId();

        try {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(TrackInterface.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            trackInterface = retrofit.create(TrackInterface.class);
            Call<Void> trackCall = trackInterface.deleteTrack(id);
            /* Android Doesn't allow synchronous execution of Http Request and so we must put it in queue*/
            trackCall.enqueue(new Callback<Void>() {

                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.code() == 201) {
                        values.remove(position);
                        notifyItemRemoved(position);
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    String anError = t.toString();
                }


            });
        } catch (Exception e) {

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<Track> myDataset) {
        values = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final ViewHolder myHolder = holder;

        holder.txtHeader.setText(values.get(position).title);
        holder.txtHeader.setOnClickListener((v) -> {
            delete(myHolder.getAdapterPosition());
        });
        holder.txtFooter.setText("Footer: " + values.get(position).singer);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }
}