
        package StronglyType;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class BusTripModel {

    @SerializedName("bus_id")
    @Expose
    private Integer busId;
    @SerializedName("trip_count")
    @Expose
    private Integer tripCount;
    @SerializedName("bus_registration_no")
    @Expose
    private String busRegistrationNo;
    @SerializedName("area_id")
    @Expose
    private Integer areaId;
    @SerializedName("area_name")
    @Expose
    private String areaName;
    @SerializedName("trip_master_id")
    @Expose
    private Integer tripMasterId;

    public Integer getBusId() {
        return busId;
    }

    public void setBusId(Integer busId) {
        this.busId = busId;
    }

    public Integer getTripCount() {
        return tripCount;
    }

    public void setTripCount(Integer tripCount) {
        this.tripCount = tripCount;
    }

    public String getBusRegistrationNo() {
        return busRegistrationNo;
    }

    public void setBusRegistrationNo(String busRegistrationNo) {
        this.busRegistrationNo = busRegistrationNo;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getTripMasterId() {
        return tripMasterId;
    }

    public void setTripMasterId(Integer tripMasterId) {
        this.tripMasterId = tripMasterId;
    }

}