package com.shiho.starwarpeople

import com.shiho.base.coordinator.BaseCoordinator
import com.shiho.base.coordinator.CoordinatorEvent
import com.shiho.starwarpeople.allpeople.StarWarsPeopleFragment
import com.shiho.starwarpeople.peopledetail.StarWarsPeopleDetailFragment
import io.reactivex.Observable
import io.reactivex.ObservableEmitter

/**
 * @ClassName: StarWarsPeopleCoordinator
 * @Description: Coordinator architecture testing.
 * @Date: 2019-10-14 14:19
 * @History:
 * <date> <version> <desc>
 */
class StarWarsPeopleCoordinator: BaseCoordinator {

    private var fragmentIndex: FragmentIndex = FragmentIndex.STARWARS_ALL_PEOPLE

    private val fragmentObservable: Observable<CoordinatorEvent>
    private lateinit var indexEmitter: ObservableEmitter<CoordinatorEvent>

    enum class FragmentIndex {
        STARWARS_ALL_PEOPLE,
        STARWARS_PEOPLE_DETAIL
    }

    init {
        fragmentObservable = Observable.create {
            indexEmitter = it
            val fragment = StarWarsPeopleFragment(this)
            it.onNext(CoordinatorEvent.FragmentCommit(fragment))
        }
    }

    override fun next(data: Any?) {
        if(fragmentIndex == FragmentIndex.STARWARS_ALL_PEOPLE) {

            fragmentIndex = FragmentIndex.STARWARS_PEOPLE_DETAIL
            val fragment = StarWarsPeopleDetailFragment(this)
            indexEmitter.onNext(CoordinatorEvent.FragmentCommit(fragment))
        }
    }

    override fun back(data: Any?) {
        if(fragmentIndex == FragmentIndex.STARWARS_PEOPLE_DETAIL) {

            fragmentIndex = FragmentIndex.STARWARS_ALL_PEOPLE
            val fragment = StarWarsPeopleFragment(this)
            indexEmitter.onNext(CoordinatorEvent.FragmentCommit(fragment))
        }
        else if(fragmentIndex == FragmentIndex.STARWARS_ALL_PEOPLE) {
            indexEmitter.onError(CoordinatorEvent.InteractionEnd())
//            indexEmitter.onNext(CoordinatorEvent.InteractionEnd())
        }
    }

    override fun keyBack(data: Any?) {
        back(data)
    }

    override fun present(): Observable<CoordinatorEvent> {
        return fragmentObservable
    }

}