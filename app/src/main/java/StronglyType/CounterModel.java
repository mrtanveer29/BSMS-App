

    package StronglyType;

    import com.google.gson.annotations.Expose;
    import com.google.gson.annotations.SerializedName;

public class CounterModel {

    @SerializedName("serial_no")
    @Expose
    private Integer serialNo;
    @SerializedName("area_id")
    @Expose
    private Integer areaId;
    @SerializedName("area_name")
    @Expose
    private String areaName;

    public Integer getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Integer serialNo) {
        this.serialNo = serialNo;
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

    @Override
    public String toString() {
        return areaName;
    }
}