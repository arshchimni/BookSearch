package com.mytechwall.android.booksearch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by arshdeep chimni on 28-05-2017.
 */

public class BookAdapter extends ArrayAdapter<BookData> {


    public BookAdapter(@NonNull Context context, ArrayList<BookData> books) {
        super(context, 0, books);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        ViewHolder holder;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
            holder = new ViewHolder(listItemView);
            listItemView.setTag(holder);

        }
        else {
            holder = (ViewHolder) listItemView.getTag();
        }

        BookData currentBook = getItem(position);

        Picasso.with(getContext()).load(currentBook.getImageUrl()).into(holder.thumbnailIv);
        holder.titleTv.setText(currentBook.getTitle());
        holder.descriptionTv.setText(currentBook.getDescription());
        holder.ratingtv.setText(currentBook.getRating()+"");


        return listItemView;
    }

    static class ViewHolder {
        @BindView(R.id.thumbnailIv)
        ImageView thumbnailIv;
        @BindView(R.id.titleTv)
        TextView titleTv;
        @BindView(R.id.descriptionTv)
        TextView descriptionTv;
        @BindView(R.id.ratingtv)
        TextView ratingtv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
