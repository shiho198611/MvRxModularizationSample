package com.shiho.starwarships

import com.shiho.base.coordinator.BaseCoordinator
import com.shiho.base.coordinator.CoordinatorEvent
import com.shiho.starwarships.allships.StarWarsStarshipsFragment
import io.reactivex.Observable
import io.reactivex.ObservableEmitter

/**
 * @ClassName: StarWarsShipsCoordinator
 * @Description: Another function for testing modularization.
 * @Date: 2019-10-16 15:41
 * @History:
 * <date> <version> <desc>
 */
class StarWarsShipsCoordinator: BaseCoordinator {

    private var shipState = StarWarsShipsState.SHIPS
    private val presentObservable: Observable<CoordinatorEvent>
    lateinit var presentEmitter: ObservableEmitter<CoordinatorEvent>

    init {
        presentObservable = Observable.create {
            presentEmitter = it

            val fragment = StarWarsStarshipsFragment(this)
            it.onNext(CoordinatorEvent.FragmentCommit(fragment))
        }
    }

    override fun next(data: Any?) {
        //No use, only one page.
    }

    override fun back(data: Any?) {
        if(shipState == StarWarsShipsState.SHIPS) {
            presentEmitter.onError(CoordinatorEvent.InteractionEnd())
//            presentEmitter.onNext(CoordinatorEvent.InteractionEnd())
        }
    }

    override fun keyBack(data: Any?) {
        back(data)
    }

    override fun present(): Observable<CoordinatorEvent> {
        return presentObservable
    }

    enum class StarWarsShipsState {
        SHIPS
    }
}