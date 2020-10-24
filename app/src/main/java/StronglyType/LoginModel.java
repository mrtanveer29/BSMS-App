

package StronglyType;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginModel {
   public class UserData {

        @SerializedName("user_name")
        @Expose
        private String userName;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("emp_firstname")
        @Expose
        private String empFirstname;
        @SerializedName("emp_lastname")
        @Expose
        private String empLastname;
        @SerializedName("route_id")
        @Expose
        private Integer routeId;
        @SerializedName("area_id")
        @Expose
        private Integer areaId;
        @SerializedName("direction")
        @Expose
        private String direction;
        @SerializedName("company_id")
        @Expose
        private Integer companyId;
        @SerializedName("company_code")
        @Expose
        private String companyCode;
        @SerializedName("company_name")
        @Expose
        private String companyName;
        @SerializedName("designation_id")
        @Expose
        private Integer designationId;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmpFirstname() {
            return empFirstname;
        }

        public void setEmpFirstname(String empFirstname) {
            this.empFirstname = empFirstname;
        }

        public String getEmpLastname() {
            return empLastname;
        }

        public void setEmpLastname(String empLastname) {
            this.empLastname = empLastname;
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

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        public Integer getCompanyId() {
            return companyId;
        }

        public void setCompanyId(Integer companyId) {
            this.companyId = companyId;
        }

        public String getCompanyCode() {
            return companyCode;
        }

        public void setCompanyCode(String companyCode) {
            this.companyCode = companyCode;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public Integer getDesignationId() {
            return designationId;
        }

        public void setDesignationId(Integer designationId) {
            this.designationId = designationId;
        }

    }

    @SerializedName("login")
    @Expose
    private Boolean login;
    @SerializedName("output_message")
    @Expose
    private String outputMessage;
    @SerializedName("user_data")
    @Expose
    private UserData userData;

    public Boolean getLogin() {
        return login;
    }

    public void setLogin(Boolean login) {
        this.login = login;
    }

    public String getOutputMessage() {
        return outputMessage;
    }

    public void setOutputMessage(String outputMessage) {
        this.outputMessage = outputMessage;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

}


