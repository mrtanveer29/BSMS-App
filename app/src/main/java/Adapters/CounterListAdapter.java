package Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.busserviceapp.R;

import java.util.List;

import StronglyType.TripDetailsData;

/**
 * Created by ASUS on 11/27/2017.
 */
public class CounterListAdapter extends RecyclerView.Adapter<CounterViewHolder> {
    private Context mContext;
    private List<TripDetailsData>  tripDetailsDatas;

    public CounterListAdapter(Context mContext, List<TripDetailsData>  tripDetailsDatas) {
        this.mContext = mContext;
        this.tripDetailsDatas = tripDetailsDatas;
    }

    @Override
    public CounterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_counter_list,parent,false);
        CounterViewHolder counterViewHolder=new CounterViewHolder(view);
        return counterViewHolder;
    }

    @Override
    public void onBindViewHolder(CounterViewHolder holder, int position) {
        TripDetailsData tripDetailsData=tripDetailsDatas.get(position);
        holder.txtCounterName.setText(tripDetailsData.getToAreaName());
        holder.txtPassenger.setText(tripDetailsData.getNoOfPassenger()+" Passengers");
    }

    @Override
    public int getItemCount() {
        return tripDetailsDatas.size();
    }
}

class CounterViewHolder extends RecyclerView.ViewHolder{
TextView txtCounterName;
TextView txtPassenger;
    public CounterViewHolder(View itemView) {
        super(itemView);
        txtCounterName= (TextView) itemView.findViewById(R.id.txtCounterName);
        txtPassenger= (TextView) itemView.findViewById(R.id.txtPassenger);
    }
}
