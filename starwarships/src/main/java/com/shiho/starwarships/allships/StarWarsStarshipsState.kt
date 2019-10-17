package com.shiho.starwarships.allships

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.shiho.base.model.starships.StarWarsStarShips

/**
 * @ClassName: StarWarsStarshipsState
 * @Description: MvRx and multi module testing state for ViewModel.
 * @Date: 2019-10-09 16:06
 * @History:
 * <date> <version> <desc>
 */
data class StarWarsStarshipsState(val ships: Async<StarWarsStarShips>): MvRxState