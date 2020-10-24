package Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.busserviceapp.R;
import com.example.asus.busserviceapp.SalesInfoActivity;

import java.util.ArrayList;
import java.util.List;

import StronglyType.BusTripModel;

/**
 * Created by ASUS on 11/23/2017.
 */
public class BusListAdapter extends RecyclerView.Adapter<BusListViewholder> {
    public Context mContext;
    BusTripModel[] busTripModelArrayList;

    public BusListAdapter(Context mContext, BusTripModel[] busTripModelArrayList) {
        this.mContext = mContext;
        this.busTripModelArrayList=busTripModelArrayList;
    }

    @Override
    public BusListViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_bus_list,parent,false);
        BusListViewholder busListViewholder=new BusListViewholder(view);

        return busListViewholder;
    }

    @Override
    public void onBindViewHolder(BusListViewholder holder, final int position) {
        final BusTripModel busTripModel=busTripModelArrayList[position];
        holder.txtBusRegNo.setText(busTripModel.getBusRegistrationNo());
        holder.txtLocation.setText(busTripModel.getAreaName());
        holder.txtAvailableSeat.setText("");
        holder.busListParentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, SalesInfoActivity.class);
                intent.putExtra("bus_id",busTripModel.getBusId());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return busTripModelArrayList.length;
    }
}
class BusListViewholder extends RecyclerView.ViewHolder{
    TextView txtBusRegNo;
    TextView txtLocation;
    TextView txtAvailableSeat;
    View busListParentView;
    public BusListViewholder(View itemView) {
        super(itemView);
        txtBusRegNo= (TextView) itemView.findViewById(R.id.txtBusRegNo);
        txtLocation= (TextView) itemView.findViewById(R.id.txtLocation);
        txtAvailableSeat= (TextView) itemView.findViewById(R.id.txtAvailableSeat);
        busListParentView= (View) itemView.findViewById(R.id.busListParentView);
    }
}
