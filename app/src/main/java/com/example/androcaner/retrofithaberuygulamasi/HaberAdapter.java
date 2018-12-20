package com.example.androcaner.retrofithaberuygulamasi;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androcaner.retrofithaberuygulamasi.Article;
import com.example.androcaner.retrofithaberuygulamasi.R;
import com.example.androcaner.retrofithaberuygulamasi.haber_detay;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;


public class HaberAdapter extends RecyclerView.Adapter<HaberAdapter.HaberViewHolder> {

    private List<Article> articleList;
    private Context context;

    public HaberAdapter(List<Article> articleList, Context context) {
        this.articleList = articleList;
        this.context = context;
    }

    @NonNull
    @Override
    public HaberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_haberler_custom_row, viewGroup, false);
        HaberViewHolder haberViewHolderoncreate=new HaberViewHolder(view);
        return haberViewHolderoncreate;
    }

    @Override
    public void onBindViewHolder(@NonNull HaberViewHolder haberViewHolder, int i) {
        final Article article=articleList.get(i);
        haberViewHolder.basliktext.setText(article.getTitle());



        //Tarihi gün ve saate çevirmek için...
        String gunvesaat=articleList.get(0).getPublishedAt();
        DateFormat m_ISO8601Local = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        try {
            Date date=m_ISO8601Local.parse(gunvesaat);
            SimpleDateFormat sdf=new SimpleDateFormat("EEEE HH:mm", new Locale("tr","TR"));
            String gunismivesaat=sdf.format(date);
            haberViewHolder.kacsaatönce.setText("Yayınlanma tarihi:  "+gunismivesaat);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Picasso.with(haberViewHolder.itemView.getContext()).load(article.getUrlToImage()).placeholder(R.drawable.haberimage).resize(50,50).into(haberViewHolder.haberkaynakresim);

        haberViewHolder.cardViewHaber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent haberdetay=new Intent(context, haber_detay.class);
                haberdetay.putExtra("URL", article.getUrl());
                context.startActivity(haberdetay);
            }
        });



    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    class HaberViewHolder extends RecyclerView.ViewHolder{
        CardView cardViewHaber;
        CircleImageView haberkaynakresim;
        TextView basliktext, kacsaatönce;


        public HaberViewHolder(@NonNull View itemView) {
            super(itemView);

            cardViewHaber=(CardView) itemView.findViewById(R.id.habercardview);
            haberkaynakresim=(CircleImageView) itemView.findViewById(R.id.haberimage);
            basliktext=(TextView) itemView.findViewById(R.id.haberbaslik);
            kacsaatönce=(TextView) itemView.findViewById(R.id.yayınlanmatarihi);


        }
    }
}
