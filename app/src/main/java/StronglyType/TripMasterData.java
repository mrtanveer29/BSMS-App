package StronglyType;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ASUS on 11/28/2017.
 */
public class TripMasterData {
    @SerializedName("counter_position")
    @Expose
    private Integer counterPosition;
    @SerializedName("bus_id")
    @Expose
    private Integer busId;
    @SerializedName("route_id")
    @Expose
    private Integer routeId;
    @SerializedName("area_id")
    @Expose
    private Integer areaId;
    @SerializedName("updated_by")
    @Expose
    private Integer updatedBy;
    @SerializedName("direction")
    @Expose
    private String direction;

    public Integer getCounterPosition() {
        return counterPosition;
    }

    public void setCounterPosition(Integer counterPosition) {
        this.counterPosition = counterPosition;
    }

    public Integer getBusId() {
        return busId;
    }

    public void setBusId(Integer busId) {
        this.busId = busId;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
