package com.trackeg.trackegapps.DI

import androidx.lifecycle.MutableLiveData
import com.trackeg.trackegapps.model.data.login.ApiResponse
import com.trackeg.trackegapps.other.MyApplication
import com.trackeg.trackegapps.other.Resource
import com.trackeg.trackegapps.viewmodel.LoginViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideLiveData(): MutableLiveData<Resource<ApiResponse>> = MutableLiveData()

    @Singleton
    @Provides
    fun provideApplicationContext() = MyApplication()
}