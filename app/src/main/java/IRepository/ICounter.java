package IRepository;

import StronglyType.BusTripModel;
import StronglyType.CounterModel;
import StronglyType.OutputModel;
import StronglyType.WayBillModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ASUS on 11/27/2017.
 */
public interface ICounter {
    @GET("Trip/GetAllCounter")
    Call<CounterModel[]> GetCounters(@Query("route_id") int routeId, @Query("direction") String direction, @Query("counter_id") int counterId);
    @POST("Trip/Post")
    Call<OutputModel> saveTripDetails(@Body WayBillModel model);
}
