package com.haretskiy.pavel.bsuirschedule.di.modules

import android.content.Context
import com.haretskiy.pavel.bsuirschedule.App
import com.haretskiy.pavel.bsuirschedule.di.scopes.ActivityScope
import com.haretskiy.pavel.bsuirschedule.utils.GroupStore
import com.haretskiy.pavel.bsuirschedule.utils.GroupStoreImpl
import com.haretskiy.pavel.bsuirschedule.utils.Router
import com.haretskiy.pavel.bsuirschedule.utils.RouterImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton


@Module
interface AppModule {

    @Binds
    fun provideApplication(app: App): Context

    @Binds
    @Singleton
    fun provideGroupStore(groupStore: GroupStoreImpl): GroupStore

    @Binds
    @ActivityScope
    fun provideRouter(routerImpl: RouterImpl): Router
}