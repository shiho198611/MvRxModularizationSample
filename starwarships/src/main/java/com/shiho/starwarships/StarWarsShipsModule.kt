package com.shiho.starwarships

import org.koin.dsl.module

/**
 * @ClassName: StarWarsShipsModule
 * @Description: DI for star wars ships module.
 * @Date: 2019-10-16 16:17
 * @History:
 * <date> <version> <desc>
 */

val starWarsShipsModule = module {
    single {
        StarWarsShipsCoordinator()
    }
}