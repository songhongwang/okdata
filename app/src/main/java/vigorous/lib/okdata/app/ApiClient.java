package vigorous.lib.okdata.app;

import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vigorous.lib.okdata.client.HttpClientUtil;

/**
 * Created by vigorous on 16/12/2.
 */

public class ApiClient {
    private static Map<String, String> mCommonParams;

    public static Map<String, String> getCommonParams() {
        return mCommonParams;
    }

    private ApiClient() {
    }

    // static inner class implements singleInstance
    private static class Holder {
        private static final ApiClient mApiClient = new ApiClient();
    }


    public <T> T init(Class<T> targetClass, String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpClientUtil.getHttpClient())
                .build();
        return retrofit.create(targetClass);
    }


    public static ApiClient getInstance(Map<String, String> commonParams) {
        mCommonParams = commonParams;
        return Holder.mApiClient;
    }

    public static ApiClient getInstance() {
        return Holder.mApiClient;
    }

}
