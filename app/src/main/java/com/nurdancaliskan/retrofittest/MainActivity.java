package com.nurdancaliskan.retrofittest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.Toast;

import com.nurdancaliskan.retrofittest.adapter.CustomAdapter;
import com.nurdancaliskan.retrofittest.model.RetroPhoto;
import com.nurdancaliskan.retrofittest.network.GetDataService;
import com.nurdancaliskan.retrofittest.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<RetroPhoto>> call = service.getAllPhotos();
        call.enqueue(new Callback<List<RetroPhoto>>() {
            @Override
            public void onResponse(Call<List<RetroPhoto>> call, Response<List<RetroPhoto>> res) {
                generateDataList(res.body());
            }

            @Override
            public void onFailure(Call<List<RetroPhoto>> call, Throwable t) {

            }
        });
    }

        /*Method to generate List of data using RecyclerView with custom adapter*/
        private void generateDataList (List <RetroPhoto> photoList) {
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            CustomAdapter adapter = new CustomAdapter(this, photoList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
}