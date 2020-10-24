package StronglyType;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TripDetailsData {

    @SerializedName("from_area")
    @Expose
    private Integer fromArea;
    @SerializedName("to_area")
    @Expose
    private Integer toArea;
    @SerializedName("no_of_passenger")
    @Expose
    private String toAreaName;
    private Integer noOfPassenger;

    public Integer getFromArea() {
        return fromArea;
    }

    public void setFromArea(Integer fromArea) {
        this.fromArea = fromArea;
    }

    public Integer getToArea() {
        return toArea;
    }

    public void setToArea(Integer toArea) {
        this.toArea = toArea;
    }

    public Integer getNoOfPassenger() {
        return noOfPassenger;
    }

    public void setNoOfPassenger(Integer noOfPassenger) {
        this.noOfPassenger = noOfPassenger;
    }

    public String getToAreaName() {
        return toAreaName;
    }

    public void setToAreaName(String toAreaName) {
        this.toAreaName = toAreaName;
    }
}