package IRepository;

import StronglyType.BusTripModel;
import StronglyType.LoginModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ASUS on 11/27/2017.
 */
public interface IBusTrip {
    @GET("Trip/GetBusUpdates")
    Call<BusTripModel[]> GetBusInfo(@Query("route_id") int routeId, @Query("direction") String direction, @Query("counter_id") int counterPosition);
}
