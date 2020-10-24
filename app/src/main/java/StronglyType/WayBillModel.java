
package StronglyType;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class WayBillModel {


    @SerializedName("tripMasterData")
    @Expose
    private TripMasterData tripMasterData;
    @SerializedName("tripDetailsData")
    @Expose
    private List<TripDetailsData> tripDetailsData = null;

    public TripMasterData getTripMasterData() {
        return tripMasterData;
    }

    public void setTripMasterData(TripMasterData tripMasterData) {
        this.tripMasterData = tripMasterData;
    }

    public List<TripDetailsData> getTripDetailsData() {
        return tripDetailsData;
    }

    public void setTripDetailsData(List<TripDetailsData> tripDetailsData) {
        this.tripDetailsData = tripDetailsData;
    }

}