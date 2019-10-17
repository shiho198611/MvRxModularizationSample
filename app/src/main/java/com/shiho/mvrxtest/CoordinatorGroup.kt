package com.shiho.mvrxtest

import com.shiho.base.coordinator.BaseCoordinator
import com.shiho.base.coordinator.CoordinatorEvent
import com.shiho.mvrxtest.funselector.FunSelectorCoordinator
import com.shiho.starwarpeople.StarWarsPeopleCoordinator
import com.shiho.starwarships.StarWarsShipsCoordinator
import io.reactivex.Observable
import io.reactivex.functions.Function
import org.koin.core.KoinComponent

/**
 * @ClassName: CoordinatorGroup
 * @Description: Collection about module coordinator.
 * @Date: 2019-10-14 18:18
 * @History:
 * <date> <version> <desc>
 */
class CoordinatorGroup: BaseCoordinator, KoinComponent {

    private var fragmentCoordinator: BaseCoordinator
    var state = CoordinatorState.INIT

    init {
        fragmentCoordinator = FunSelectorCoordinator()
    }

    override fun next(data: Any?) {
        fragmentCoordinator.next(data)
    }

    override fun back(data: Any?) {
        return fragmentCoordinator.back(data)
    }

    override fun present(): Observable<Any> {
        return fragmentCoordinator.present().onErrorResumeNext(TransferFunction())
    }

    override fun keyBack(data: Any?) {
        return fragmentCoordinator.keyBack(data)
    }


    enum class CoordinatorState {
        INIT,
        PEOPLE,
        SHIPS
    }

    inner class TransferFunction: Function<Throwable, Observable<Any>> {

        override fun apply(th: Throwable): Observable<Any> {
            if(th is CoordinatorEvent.InteractionEnd &&
                state == CoordinatorState.PEOPLE ||
                state == CoordinatorState.SHIPS) {

                state = CoordinatorState.INIT
                fragmentCoordinator = FunSelectorCoordinator()
                return fragmentCoordinator.present().onErrorResumeNext(TransferFunction())
            }
            else if(th is CoordinatorEvent.InteractionEnd && state == CoordinatorState.INIT) {
                if(th.data != null && th.data is Int) {
                    if(th.data == R.id.btn_goto_people) {
                        state = CoordinatorState.PEOPLE
                        fragmentCoordinator = StarWarsPeopleCoordinator()
                    }
                    else if(th.data == R.id.btn_goto_ships) {
                        state = CoordinatorState.SHIPS
                        fragmentCoordinator = StarWarsShipsCoordinator()
                    }
                    return fragmentCoordinator.present().onErrorResumeNext(TransferFunction())
                }
            }
            return Observable.just(CoordinatorEvent.AppFinish())
        }

    }
}