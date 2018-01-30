package pwr.adamzimnyy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class RetrofitBuilder {
    private static boolean useLocalhost = false;

    public static final String RIOT_BASE = "https://eun1.api.riotgames.com/lol/";
    public static final String RIOT_SUMMONER = "https://eun1.api.riotgames.com/lol/summoner/v3/";
    public static final String RIOT_MASTERY = "https://eun1.api.riotgames.com/lol/champion-mastery/v3/";
    public static final String RIOT_IMAGE = "http://ddragon.leagueoflegends.com/cdn/7.20.3/img/";
    public static final String RIOT_STATIC_DATA = "https://eun1.api.riotgames.com/lol/static-data/v3/";
    public static final String LOCALHOST = "http://192.168.0.108:8080";

    public static Retrofit build(String url) {
        GsonBuilder builder = new GsonBuilder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().
                readTimeout(120, TimeUnit.SECONDS).connectTimeout(120, TimeUnit.SECONDS).addInterceptor(interceptor).build();


        Gson gson = builder.setDateFormat("yyyy-MM-dd HH:mm:ss Z").create();
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static Object getService(Class<?> clas, String url) {
        return build(url).create(clas);
    }

}
