package com.shiho.base.model

import com.shiho.base.model.people.StarWarsPeople
import com.shiho.base.model.starships.StarWarsStarShips
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * @ClassName: NetworkService
 * @Description: MvRx test api service.
 * @Date: 2019-09-17 17:34
 * @History:
 * <date> <version> <desc>
 */
interface NetworkService {

    @GET("people")
    fun getStarWarsPeople(): Observable<StarWarsPeople>

    @GET("startships")
    fun getStarWarsStarShips(): Observable<StarWarsStarShips>

}