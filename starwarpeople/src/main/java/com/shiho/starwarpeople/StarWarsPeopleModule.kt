package com.shiho.starwarpeople

import org.koin.dsl.module

/**
 * @ClassName: StarWarsPeopleModule
 * @Description: DI for star wars people module.
 * @Date: 2019-10-16 16:15
 * @History:
 * <date> <version> <desc>
 */

val starWarsPeopleModule = module {
    single {
        StarWarsPeopleCoordinator()
    }
}