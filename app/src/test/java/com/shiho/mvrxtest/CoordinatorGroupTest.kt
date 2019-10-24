package com.shiho.mvrxtest

import com.shiho.base.coordinator.CoordinatorEvent
import com.shiho.mvrxtest.funselector.FunSelectEvent
import com.shiho.mvrxtest.funselector.FunSelectFragment
import com.shiho.starwarpeople.allpeople.StarWarsPeopleFragment
import com.shiho.starwarships.allships.StarWarsStarshipsFragment
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

/**
 * @ClassName: CoordinatorGroupTest
 * @Description: Testing for Coordinator group.
 * @Date: 2019-10-23 18:15
 * @History:
 * <date> <version> <desc>
 */
class CoordinatorGroupTest {

    lateinit var coordinatorGroup: CoordinatorGroup
    lateinit var groupObservable: Observable<CoordinatorEvent>

    @Before
    fun initTest() {
        coordinatorGroup = CoordinatorGroup()
        groupObservable = coordinatorGroup.present()
    }

    @Test
    fun present_init_FunSelectorCoordinator() {
        val testObserver = groupObservable.test()

        testObserver.assertValue {
            it is CoordinatorEvent.FragmentCommit && it.fragment is FunSelectFragment
        }

    }

    @Test
    fun next_FunSelector_StarWarsPeople() {
        val testObserver = groupObservable.test()

        coordinatorGroup.next(FunSelectEvent.NAVITOPEOPLE)

        testObserver.assertValueAt(0) {
           it is CoordinatorEvent.FragmentCommit && it.fragment is FunSelectFragment
        }.assertValueAt(1) {
            it is CoordinatorEvent.FragmentCommit && it.fragment is StarWarsPeopleFragment
        }
    }

    @Test
    fun next_FunSelector_StarWarsShips() {
        val testObserver = groupObservable.test()

        coordinatorGroup.next(FunSelectEvent.NAVITOSHIPS)

        testObserver.assertValueAt(0) {
            it is CoordinatorEvent.FragmentCommit && it.fragment is FunSelectFragment
        }.assertValueAt(1) {
            it is CoordinatorEvent.FragmentCommit && it.fragment is StarWarsStarshipsFragment
        }

    }

    @Test
    fun back_FunSelector_AppFinishEvent() {
        val testObserver = groupObservable.test()

        coordinatorGroup.back()

        testObserver.assertValueAt(1) {
            it is CoordinatorEvent.AppFinish
        }
    }

    @Test
    fun back_StarWarsPeople_FunSelector() {
        val testObserver = groupObservable.test()

        coordinatorGroup.next(FunSelectEvent.NAVITOPEOPLE)
        coordinatorGroup.back()

        testObserver.assertValueAt(0) {
            it is CoordinatorEvent.FragmentCommit && it.fragment is FunSelectFragment
        }
        testObserver.assertValueAt(1) {
            it is CoordinatorEvent.FragmentCommit && it.fragment is StarWarsPeopleFragment
        }
        testObserver.assertValueAt(2) {
            it is CoordinatorEvent.FragmentCommit && it.fragment is FunSelectFragment
        }
    }

    @Test
    fun back_StarWarsPeople_AppFinishEvent() {
        val testObserver = groupObservable.test()

        coordinatorGroup.next(FunSelectEvent.NAVITOPEOPLE)
        coordinatorGroup.back()
        coordinatorGroup.back()

        testObserver.assertValueAt(0) {
            it is CoordinatorEvent.FragmentCommit && it.fragment is FunSelectFragment
        }.assertValueAt(1) {
            it is CoordinatorEvent.FragmentCommit && it.fragment is StarWarsPeopleFragment
        }.assertValueAt(2) {
            it is CoordinatorEvent.FragmentCommit && it.fragment is FunSelectFragment
        }.assertValueAt(3) {
            it is CoordinatorEvent.AppFinish
        }
    }
}