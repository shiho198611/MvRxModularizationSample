package com.shiho.starwarpeople.allpeople

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.shiho.base.model.people.StarWarsPeople

/**
 * @ClassName: StarWarsPeopleInfoState
 * @Description: MvRx test state.
 * @Date: 2019-09-18 15:29
 * @History:
 * <date> <version> <desc>
 */
data class StarWarsPeopleInfoState(val people: Async<StarWarsPeople>): MvRxState