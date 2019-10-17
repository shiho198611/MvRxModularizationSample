package com.shiho.starwarpeople.allpeople

import com.airbnb.mvrx.*
import com.shiho.base.model.NetworkHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * @ClassName: StarWarsPeopleViewModel
 * @Description: MvRx test ViewModel.
 * @Date: 2019-09-17 17:13
 * @History:
 * <date> <version> <desc>
 */

class StarWarsPeopleViewModel(initialState: StarWarsPeopleInfoState): BaseMvRxViewModel<StarWarsPeopleInfoState>(initialState, debugMode = true), KoinComponent {

    private val networkHelper: NetworkHelper by inject()

    companion object: MvRxViewModelFactory<StarWarsPeopleViewModel, StarWarsPeopleInfoState> {

        override fun initialState(viewModelContext: ViewModelContext): StarWarsPeopleInfoState? {
            return StarWarsPeopleInfoState(Uninitialized)
        }
    }

    fun getStarWarsPeoples() {
        setState {
            copy(people = Loading())
        }
        networkHelper.apiService.getStarWarsPeople()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .execute {
                copy(people = it)
            }
    }

}