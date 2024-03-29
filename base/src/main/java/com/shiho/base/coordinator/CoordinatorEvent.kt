package com.shiho.base.coordinator

import androidx.fragment.app.Fragment
import java.io.IOException

/**
 * @ClassName: CoordinatorEvent
 * @Description: Transfer data for coordinator.
 * @Date: 2019-10-15 11:48
 * @History:
 * <date> <version> <desc>
 */

sealed class CoordinatorEvent{

    data class FragmentCommit(val fragment: Fragment, val transferData: Any? = Any()): CoordinatorEvent()
    data class AppFinish(val transferData: Any? = Any()): CoordinatorEvent()

    data class InteractionEnd(val data: Any? = null): IOException()

}