package com.shiho.mvrxtest.funselector

import com.shiho.base.coordinator.BaseCoordinator
import com.shiho.base.coordinator.CoordinatorEvent
import io.reactivex.Observable
import io.reactivex.ObservableEmitter

/**
 * @ClassName: FunSelectorCoordinator
 * @Description: Coordinator for FunSelectFragment.
 * @Date: 2019-10-16 18:17
 * @History:
 * <date> <version> <desc>
 */
class FunSelectorCoordinator: BaseCoordinator {

    val funSelObservable: Observable<CoordinatorEvent>
    lateinit var funSelEmitter: ObservableEmitter<CoordinatorEvent>

    init {
        funSelObservable = Observable.create {
            funSelEmitter = it

            val fragment = FunSelectFragment(this)
            it.onNext(CoordinatorEvent.FragmentCommit(fragment))
        }
    }

    override fun next(data: Any?) {
//        funSelEmitter.onNext(CoordinatorEvent.InteractionEnd(data))
        funSelEmitter.onError(CoordinatorEvent.InteractionEnd(data))
    }

    override fun back(data: Any?) {
//        funSelEmitter.onNext(CoordinatorEvent.InteractionEnd())
        funSelEmitter.onError(CoordinatorEvent.InteractionEnd())
    }

    override fun keyBack(data: Any?) {
        back(data)
    }

    override fun present(): Observable<CoordinatorEvent> {
        return funSelObservable
    }
}