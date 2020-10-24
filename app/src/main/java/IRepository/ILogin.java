package IRepository;


import StronglyType.LoginModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by jmjsa on 22/03/2017.
 */

public interface ILogin {
    @POST("Login/UserLogin")
    Call<LoginModel> GetLoginInfo(@Query("user_name") String username, @Query("password") String password);
}
