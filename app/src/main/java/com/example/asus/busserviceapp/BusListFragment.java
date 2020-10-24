package com.example.asus.busserviceapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import Adapters.BusListAdapter;
import IRepository.IBusTrip;
import StronglyType.BusTripModel;
import butterknife.BindView;
import butterknife.ButterKnife;
import model.APIInitializer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class BusListFragment extends Fragment {
@BindView(R.id.busList)RecyclerView busList;
BusListAdapter busListAdapter;
@BindView(R.id.placeholderText)TextView placeholderText;
Retrofit retrofit;
    public BusListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_bus_list, container, false);
        ButterKnife.bind(this,view);
        retrofit= APIInitializer.initNetwork(getActivity());
        retrofit.create(IBusTrip.class).GetBusInfo(16,"Down",2).enqueue(new Callback<BusTripModel[]>() {
            @Override
            public void onResponse(Call<BusTripModel[]> call, Response<BusTripModel[]> response) {
                if(response.body()==null|| response.body().length==0){
                    placeholderText.setVisibility(View.VISIBLE);
                }else{
                    placeholderText.setVisibility(View.GONE);
                }
                busListAdapter=new BusListAdapter(getActivity(),response.body());
                Log.e("URl",call.request().url().toString());
                busList.setAdapter(busListAdapter);
            }

            @Override
            public void onFailure(Call<BusTripModel[]> call, Throwable t) {

            }
        });


        return view;
    }


    public static BusListFragment newInstance() {
        return new BusListFragment();
    }
}
