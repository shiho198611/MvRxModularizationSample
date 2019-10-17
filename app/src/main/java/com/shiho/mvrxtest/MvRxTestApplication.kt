package com.shiho.mvrxtest

import android.app.Application
import com.shiho.base.networkServiceModule
import com.shiho.starwarpeople.starWarsPeopleModule
import com.shiho.starwarships.starWarsShipsModule

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * @ClassName: MvRxTestApplication
 * @Description: Testing android application.
 * @Date: 2019-10-15 14:42
 * @History:
 * <date> <version> <desc>
 */
class MvRxTestApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MvRxTestApplication)
            modules(networkServiceModule)
            modules(starWarsPeopleModule)
            modules(starWarsShipsModule)
        }
    }
}