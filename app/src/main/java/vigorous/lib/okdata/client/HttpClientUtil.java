package vigorous.lib.okdata.client;



import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import vigorous.lib.okdata.BuildConfig;

/**
 * Created by vigorous on 16/11/4.
 *
 */

public class HttpClientUtil {
    private static final int READ_TIME_OUT = 10;
    private static final int WRITE_TIME_OUT = 10;
    private static OkHttpClient mOKHttpClient;

    private HttpClientUtil() {
    }

    public static OkHttpClient getHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if (BuildConfig.LOG_DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        if (mOKHttpClient == null) {
            mOKHttpClient = new OkHttpClient
                    .Builder()
                    .addInterceptor(logging)
                    .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                    .readTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                    .build();
        }
        return mOKHttpClient;
    }
}
