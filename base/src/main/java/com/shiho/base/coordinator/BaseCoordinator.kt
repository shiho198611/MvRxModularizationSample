package com.shiho.base.coordinator

import io.reactivex.Observable

/**
 * @ClassName: BaseCoordinator
 * @Description: Abstract coordinator.
 * @Date: 2019-10-14 15:46
 * @History:
 * <date> <version> <desc>
 */
interface BaseCoordinator {

//    fun init(): Observable<Any>
//    fun next(data: Any? = null): Observable<Any>
//    fun back(data: Any? = null): Observable<Any>
//    fun keyBack(data: Any? = null): Observable<Any>

    fun next(data: Any? = null)
    fun back(data: Any? = null)
    fun keyBack(data: Any? = null)

    fun present(): Observable<Any>
}