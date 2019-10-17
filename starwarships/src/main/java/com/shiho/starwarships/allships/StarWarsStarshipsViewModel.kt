package com.shiho.starwarships.allships

import com.airbnb.mvrx.*
import com.shiho.base.model.NetworkHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * @ClassName: StarWarsStarshipsViewModel
 * @Description: MvRx and multi module testing ViewModel.
 * @Date: 2019-10-09 16:01
 * @History:
 * <date> <version> <desc>
 */
class StarWarsStarshipsViewModel(initialState: StarWarsStarshipsState):
    BaseMvRxViewModel<StarWarsStarshipsState>(initialState, debugMode = true), KoinComponent {

    private val netHelper: NetworkHelper by inject()

    companion object: MvRxViewModelFactory<StarWarsStarshipsViewModel, StarWarsStarshipsState> {

        override fun initialState(viewModelContext: ViewModelContext): StarWarsStarshipsState? {
            return StarWarsStarshipsState(Uninitialized)
        }
    }

    fun queryStarships() {
        setState {
            copy(ships = Loading())
        }
        val data = netHelper.apiService
            .getStarWarsStarShips()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .execute {
                copy(ships = it)
            }
    }

}