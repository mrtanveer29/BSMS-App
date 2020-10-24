package com.example.asus.busserviceapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Adapters.CounterListAdapter;
import IRepository.ICounter;
import StronglyType.CounterModel;
import StronglyType.OutputModel;
import StronglyType.TripDetailsData;
import StronglyType.TripMasterData;
import StronglyType.WayBillModel;
import butterknife.BindView;
import butterknife.ButterKnife;
import model.APIInitializer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static StronglyType.WayBillModel.*;


public class SalesInfoActivity extends AppCompatActivity {
    private static final int SAVE_MENU_ITEM = 1;
    @BindView(R.id.toCounter)
    AutoCompleteTextView toCounter;
    @BindView(R.id.passenger)
    EditText passenger;
    @BindView(R.id.tripList)
    RecyclerView tripList;
    Retrofit retrofit;
    ICounter CounterListener;

    CounterModel[] allCouters;
    List<TripDetailsData> tripDetailsDataList;

    private SharedPreferences sharedPreferences;
;
    String direction;
    String route;
    String counter;
    int busId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_info);
        getSupportActionBar().setTitle("Input Sales");
        ButterKnife.bind(this);

        // stirage
        sharedPreferences = getSharedPreferences("ADASCM", MODE_PRIVATE);

        direction=sharedPreferences.getString("direction","");
        route=sharedPreferences.getString("route_id","0");
        counter=sharedPreferences.getString("counter_id","0");
        busId= getIntent().getIntExtra("bus_id",0);


        // Network init

        tripDetailsDataList=new ArrayList<>();
        retrofit= APIInitializer.initNetwork(this);
        CounterListener=retrofit.create(ICounter.class);

        CounterListener.GetCounters(Integer.parseInt(route),direction,Integer.parseInt(counter)).enqueue(new Callback<CounterModel[]>() {
            @Override
            public void onResponse(Call<CounterModel[]> call, Response<CounterModel[]> response) {
                Log.e("Tag",call.request().url().toString());
                allCouters=response.body();
                ArrayAdapter<CounterModel> arrayAdapter=new ArrayAdapter<CounterModel>(getApplicationContext(),R.layout.simple_list_style,R.id.txtcounter,allCouters);
                toCounter.setAdapter(arrayAdapter);
            }

            @Override
            public void onFailure(Call<CounterModel[]> call, Throwable t) {
                Toast.makeText(SalesInfoActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //noinspection SimplifiableIfStatement
        final ProgressDialog progressDialog = new ProgressDialog(SalesInfoActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Saving...");
        progressDialog.show();
        switch (item.getItemId()) {
            case R.id.action_settings:
                TripMasterData tripMasterData= new TripMasterData();
                tripMasterData.setBusId(busId);
                tripMasterData.setAreaId(Integer.parseInt(counter));
                tripMasterData.setDirection(direction);
                tripMasterData.setRouteId(Integer.parseInt(route));

                WayBillModel wayBillModel=new WayBillModel();
                wayBillModel.setTripMasterData(tripMasterData);
                wayBillModel.setTripDetailsData(tripDetailsDataList);


                CounterListener.saveTripDetails(wayBillModel).enqueue(new Callback<OutputModel>() {
                    @Override
                    public void onResponse(Call<OutputModel> call, Response<OutputModel> response) {

                        Toast.makeText(SalesInfoActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(SalesInfoActivity.this,MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<OutputModel> call, Throwable t) {
                        Toast.makeText(SalesInfoActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                return true;

            default:
                return false;
        }
    }

    public void add(View v){


        String selectedCounter=toCounter.getText().toString();
        Toast.makeText(SalesInfoActivity.this, selectedCounter, Toast.LENGTH_SHORT).show();
        CounterModel selectedCounterModel = null;
        for (CounterModel item: allCouters){
            Log.e("Tags",item.getAreaName());
            if(item.getAreaName().equals(selectedCounter)){

                selectedCounterModel=item;
                break;
            }
        }
        if (selectedCounterModel != null) {
            TripDetailsData tripDetailsData=new TripDetailsData();
            tripDetailsData.setToArea(selectedCounterModel.getAreaId());
            tripDetailsData.setToAreaName(selectedCounterModel.getAreaName());

            tripDetailsData.setNoOfPassenger(Integer.parseInt(passenger.getText().toString()));

            tripDetailsData.setFromArea(Integer.parseInt(counter)); // static data problem

            tripDetailsDataList.add(tripDetailsData);

            CounterListAdapter counterListAdapter=new CounterListAdapter(getApplication(),tripDetailsDataList);
            tripList.setAdapter(counterListAdapter);
            passenger.setText("");
            toCounter.setText("");
        }



    }
}
