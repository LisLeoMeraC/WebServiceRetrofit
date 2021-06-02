package com.example.retrofit;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.retrofit.Interfaces.RevistasWeb;
import com.example.retrofit.modelos.Revistas;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public TextView mJsonTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //btnScar = findViewById(R.id.btnBuscar);
        mJsonTxtView = findViewById(R.id.jsonText);

        getPosts();

    }



    private void getPosts(){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://revistas.uteq.edu.ec/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RevistasWeb jsonPlaceHolderApi= retrofit.create(RevistasWeb.class);

        Call<List<Revistas>> call= jsonPlaceHolderApi.getPosts();
        call.enqueue(new Callback<List<Revistas>>() {
            @Override
            public void onResponse(Call<List<Revistas>> call, Response<List<Revistas>> response) {
                if(!response.isSuccessful()){
                    mJsonTxtView.setText("Codigo: "+response.code());
                    return;
                }
                List<Revistas> postsList= response.body();
                for(Revistas p: postsList){
                    String content="";

                    content+= "issue_id: "+p.getIssue_id() + "\n";
                    content+= "volume: "+p.getVolume() + "\n";
                    content+="number: "+p.getNumber()+"\n";
                    content+= "year: "+p.getYear() + "\n";
                    content+= "date_published: "+p.getDate_published() + "\n";
                    content+= "title: "+p.getTitle() + "\n";
                    content+= "doi: "+p.getDoi() + "\n";
                    content+= "cover: "+p.getCover() + "\n\n";
                    mJsonTxtView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Revistas>> call, Throwable t) {
                mJsonTxtView.setText(t.getMessage());
            }
        });
    }
}