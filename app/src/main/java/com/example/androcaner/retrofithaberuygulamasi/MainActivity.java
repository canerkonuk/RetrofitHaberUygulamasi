package com.example.androcaner.retrofithaberuygulamasi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.androcaner.retrofithaberuygulamasi.HaberIconRetrofit.BestIcon;
import com.example.androcaner.retrofithaberuygulamasi.HaberIconRetrofit.BestIconClient;

import com.example.androcaner.retrofithaberuygulamasi.HaberIconRetrofit.Icon;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private static final String API_KEY_HABER="d82e3a7c6d87491ca37d22a9b36aa05f";
    RecyclerView recyclerViewHaber;
    RetrofitInterface haberretroInterface;
    RetrofitInterface bestIconretroInterface;
    RecyclerView.LayoutManager layoutManager;
    KenBurnsView kenBurnsView;
    TextView enüsttekihaberbaslik, enüsttekihaberkaynak, yayınlanmatarihienüsttekihaber;
    SwipeRefreshLayout swipeRefreshLayout;
    String haberimageurl, enusttekihaberurl;
    CircleImageView haberinsitesininfaviconu;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Lütfen Bekleyiniz");
        progressDialog.setMessage("Yükleniyor...");
        progressDialog.show();

        haberinsitesininfaviconu=findViewById(R.id.kaynakfavicon);

        kenBurnsView=findViewById(R.id.enüsttekihaber);
        enüsttekihaberbaslik=findViewById(R.id.enüssttekihaberinbaslığı);
        enüsttekihaberkaynak=findViewById(R.id.enüsttekihaberkaynagınınadı);
        yayınlanmatarihienüsttekihaber=findViewById(R.id.yayınlanma);
        swipeRefreshLayout=findViewById(R.id.swiperefresh);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Intent intent=getIntent();
                finish();
                startActivity(intent);
            }
        });

        recyclerViewHaber=findViewById(R.id.haberrecycler);
        layoutManager=new LinearLayoutManager(this);
        recyclerViewHaber.setLayoutManager(layoutManager);
        recyclerViewHaber.setHasFixedSize(true);

        haberretroInterface=HaberClient.getClient().create(RetrofitInterface.class);
        Call<News> callhaber=haberretroInterface.getNews("tr", API_KEY_HABER);

        callhaber.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                List<Article> articleList=response.body().getArticles();

                Picasso.with(getApplicationContext()).load(articleList.get(0).getUrlToImage()).placeholder(R.drawable.sunrisemain).into(kenBurnsView);

                enüsttekihaberbaslik.setText(articleList.get(0).getTitle());
                enüsttekihaberkaynak.setText(articleList.get(0).getAuthor());

                //Tarihi gün ve saate çevirmek için...

                String gunvesaat=articleList.get(0).getPublishedAt();
                DateFormat m_ISO8601Local = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                try {
                    Date date=m_ISO8601Local.parse(gunvesaat);
                    SimpleDateFormat sdf=new SimpleDateFormat("EEEE HH:mm", new Locale("tr","TR"));
                    String gunismivesaat=sdf.format(date);
                    yayınlanmatarihienüsttekihaber.setText("Yayınlanma tarihi:  "+gunismivesaat);

                } catch (ParseException e) {
                    e.printStackTrace();
                }


                //Paper db kullanarak yapsaydım bu verileri alacaktım fakat paper db bazı gecikmelere neden olduğu için kullanmaktan vazgeçtim...
                /*
                Paper.init(getApplicationContext());
                Paper.book().write("SITEICON", articleList.get(0).getSource().getName());
                Paper.book().write("ENÜSTTEKİHABERURL", articleList.get(0).getUrl());
                */


                haberimageurl=articleList.get(0).getSource().getName();
                enusttekihaberurl=articleList.get(0).getUrl();

                bestIconretroInterface=BestIconClient.getBastIconClient().create(RetrofitInterface.class);
                Call<BestIcon> bestIconCall=bestIconretroInterface.getBestIcon(haberimageurl);

                bestIconCall.enqueue(new Callback<BestIcon>() {
                    @Override
                    public void onResponse(Call<BestIcon> call, Response<BestIcon> response) {
                        List<Icon> iconList=response.body().getIcons();

                        if(haberimageurl.equals("Cnnturk.com")) {
                            Picasso.with(getApplicationContext()).load("https://besticon-demo.herokuapp.com/lettericons/C-120.png").placeholder(R.drawable.haberresim).resize(30, 30).into(haberinsitesininfaviconu);
                        }
                        else {
                            Picasso.with(getApplicationContext()).load(iconList.get(0).getUrl()).placeholder(R.drawable.haberimage).resize(30, 30).into(haberinsitesininfaviconu);
                        }

                        progressDialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<BestIcon> call, Throwable t) {

                    }
                });


                kenBurnsView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent enusttekihaber=new Intent(getApplicationContext(), haber_detay.class);
                        enusttekihaber.putExtra("ENÜSTTEKİHABERURL", enusttekihaberurl);
                        getApplicationContext().startActivity(enusttekihaber);
                    }
                });





                articleList.remove(0);
                HaberAdapter haberAdapter=new HaberAdapter(articleList,getApplicationContext());
                recyclerViewHaber.setAdapter(haberAdapter);




            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

                //System.out.println("BAŞARISIZ"+t.getLocalizedMessage());

            }
        });


        //Paper db kullanarak yapsaydım bu verileri kullanacaktım fakat paper db bazı gecikmelere neden olduğu için kullanmaktan vazgeçtim...

        /*Paper.init(getApplicationContext());
        haberimageurl=Paper.book().read("SITEICON");
        enusttekihaberurl=Paper.book().read("ENÜSTTEKİHABERURL");
        */







    }
}
