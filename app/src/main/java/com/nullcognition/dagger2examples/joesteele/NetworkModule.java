package com.nullcognition.dagger2examples.joesteele;

import android.app.Application;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.client.Client;
import retrofit.client.OkClient;

/**
 * Created by ersin on 26/02/15 at 7:21 PM
 */


@Module
public final class NetworkModule {

   static final int DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50MB

   @Provides
   @Singleton
   OkHttpClient provideOkHttpClient(ComponentTest app){
	  return createOkHttpClient(app);
   }

   @Provides
   @Singleton
   Client provideClient(OkHttpClient client){
	  return new OkClient(client);
   }

   static OkHttpClient createOkHttpClient(Application app){
	  OkHttpClient client = new OkHttpClient();

	  File cacheDir = new File(app.getCacheDir(), "http");
	  client.setCache(new Cache(cacheDir, DISK_CACHE_SIZE));

	  return client;
   }
}
