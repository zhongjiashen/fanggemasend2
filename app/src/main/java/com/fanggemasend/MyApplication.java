package com.fanggemasend;

import android.app.Application;

import com.fanggemasend.httputils.api.ApiService;
import com.fanggemasend.utils.LocalStore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by 1363655717 on 2017/4/4.
 */

public class MyApplication extends Application{
    public ApiService apiService;
    public static MyApplication application;
    public String spread="";
    public LocalStore localStore;
    @Override
    public void onCreate() {
        super.onCreate();
        localStore= new LocalStore(this);
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(new Gson());

        ScalarsConverterFactory scalarsConverterFactory = ScalarsConverterFactory.create();
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(new SafeTypeAdapterFactory()).create();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();
        apiService = new Retrofit.Builder()
//                .baseUrl("http://www.jiushijiudian.com/")
                .baseUrl("http://192.168.0.7/api/Login/")
//                .baseUrl("http://android.chinawutong.com/")
                .addConverterFactory(scalarsConverterFactory)
//                .addConverterFactory(gsonConverterFactory)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build().create(ApiService.class);
        application=this;
    }
    private final static class SafeTypeAdapterFactory<T> implements TypeAdapterFactory {
        @Override
        public TypeAdapter create(Gson gson, final TypeToken type) {
            final TypeAdapter delegate = gson.getDelegateAdapter(this, type);
            return new TypeAdapter<T>() {
                @Override
                public void write(JsonWriter out, T value) throws IOException {
                    try {
                        delegate.write(out, value);
                    } catch (IOException e) {
                        delegate.write(out, "");
                    }
                }


                @Override
                public T read(JsonReader in) throws IOException {
                    try {
                        return (T) delegate.read(in);
                    } catch (IOException e) {
                        in.skipValue();
                        return null;
                    } catch (IllegalStateException e) {
                        in.skipValue();
                        return null;
                    } catch (JsonSyntaxException e) {
                        in.skipValue();
                        if (type.getType() instanceof Class) {
                            try {
                                return (T) ((Class) type.getType()).newInstance();
                            } catch (Exception e1) {

                            }
                        }
                        return null;
                    }
                }
            };
        }
    }
}
