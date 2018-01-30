package pwr.adamzimnyy;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

     private static final int MINUTES = 20;
     private static final String URL = "http://sunwell-back.herokuapp.com/";
    //  public static final String URL = "http://localhost:8080/";

    public static void main(String[] args) throws InterruptedException, IOException {

        List<Online> todayOnline = new ArrayList<Online>();
        while (true) {
            for (int i = 0; i < 18; i++) {
                Online on = OnlineParser.parse();
                todayOnline.add(on);
                System.out.println("Online saved: Feronis = " + on.getFeronis() + ", Angrathar = " + on.getAngrathar());
                Thread.sleep(1000 * 60 * MINUTES);
            }

            Call<Void> call = ((Api) RetrofitBuilder.getService(Api.class, URL)).sendOnline(todayOnline);
            System.out.println("Sending request...");
            call.enqueue(new Callback<Void>() {
                public void onResponse(Call<Void> call, Response<Void> response) {
                    System.out.println("Response: " + response.code());
                }

                public void onFailure(Call<Void> call, Throwable throwable) {
                    System.out.println("Failed: " + throwable.getLocalizedMessage());

                }
            });
            todayOnline.clear();
        }
    }
}
