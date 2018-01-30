package pwr.adamzimnyy;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import java.util.List;

/**
 * Created by adamz on 30.01.2018.
 */
public interface Api {

    @POST("/online")
    Call<Void> sendOnline(@Body List<Online> onlineList);
}
