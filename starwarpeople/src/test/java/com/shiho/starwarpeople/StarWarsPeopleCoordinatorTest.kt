package com.shiho.starwarpeople

import com.shiho.base.coordinator.CoordinatorEvent
import com.shiho.starwarpeople.allpeople.StarWarsPeopleFragment
import com.shiho.starwarpeople.peopledetail.StarWarsPeopleDetailFragment
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * @ClassName: StarWarsPeopleCoordinatorTest
 * @Description: Unit test for coordinator.
 * @Date: 2019-10-21 17:03
 * @History:
 * <date> <version> <desc>
 */
class StarWarsPeopleCoordinatorTest {

    lateinit var starWarsPeopleCoordinator: StarWarsPeopleCoordinator
    lateinit var coordinatorPresent: Observable<CoordinatorEvent>

    @Before
    fun initTest() {
        starWarsPeopleCoordinator = StarWarsPeopleCoordinator()

        coordinatorPresent = starWarsPeopleCoordinator.present()
    }

    @Test
    fun present_init_AllPeople() {
        val testObserver = coordinatorPresent.test()

        testObserver.assertValue {
            it is CoordinatorEvent.FragmentCommit && it.fragment is StarWarsPeopleFragment
        }
    }

    @Test
    fun next_AllPeople_PeopleDetail() {
        val testObserver = coordinatorPresent.test()

        starWarsPeopleCoordinator.next()

        testObserver.assertOf {
            Assert.assertEquals(it.valueCount(), 2)
            it.values().forEachIndexed { index, event ->
                Assert.assertTrue(event is CoordinatorEvent.FragmentCommit)
                if(index == 0) {
                    Assert.assertTrue((event as CoordinatorEvent.FragmentCommit).fragment is StarWarsPeopleFragment)
                }
                else if(index == 1) {
                    Assert.assertTrue((event as CoordinatorEvent.FragmentCommit).fragment is StarWarsPeopleDetailFragment)
                }
            }
        }
    }

    @Test
    fun next_PeopleDetailStar_NoAction() {
        val testObserver = coordinatorPresent.test()

        starWarsPeopleCoordinator.next()
        starWarsPeopleCoordinator.next()

        testObserver.assertOf {
            Assert.assertEquals(it.valueCount(), 2)
        }
    }

    @Test
    fun back_AllPeople_ThrowInteractionEnd() {
        val testObserver = coordinatorPresent.test()

        starWarsPeopleCoordinator.back()
        testObserver.assertError(CoordinatorEvent.InteractionEnd::class.java)
    }

    @Test
    fun back_PeopleDetail_AllPeople(){
        val testObserver = coordinatorPresent.test()

        starWarsPeopleCoordinator.next()
        starWarsPeopleCoordinator.back()

        testObserver.assertOf {
            Assert.assertEquals(it.valueCount(), 3)
            it.values().forEachIndexed { index, event ->
                Assert.assertTrue(event is CoordinatorEvent.FragmentCommit)
                if(index == 2) {
                   Assert.assertTrue((event as CoordinatorEvent.FragmentCommit).fragment is StarWarsPeopleFragment)
                }
            }
        }
    }

    @Test
    fun keyBack_AllPeople_ThrowInteractionEnd() {
        val testObserver = coordinatorPresent.test()

        starWarsPeopleCoordinator.back()
        testObserver.assertError(CoordinatorEvent.InteractionEnd::class.java)
    }

    @Test
    fun keyBack_PeopleDetail_AllPeople() {
        val testObserver = coordinatorPresent.test()

        starWarsPeopleCoordinator.next()
        starWarsPeopleCoordinator.back()

        testObserver.assertOf {
            Assert.assertEquals(it.valueCount(), 3)
            it.values().forEachIndexed { index, event ->
                Assert.assertTrue(event is CoordinatorEvent.FragmentCommit)
                if(index == 2) {
                    Assert.assertTrue((event as CoordinatorEvent.FragmentCommit).fragment is StarWarsPeopleFragment)
                }
            }
        }
    }
}