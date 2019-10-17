package com.shiho.base

import com.shiho.base.model.NetworkHelper
import org.koin.dsl.module

/**
 * @ClassName: NetworkModule
 * @Description: DI module for network service.
 * @Date: 2019-09-25 11:45
 * @History:
 * <date> <version> <desc>
 */

val networkServiceModule = module {

    single {
        NetworkHelper("https://swapi.co/api/")
    }

}
