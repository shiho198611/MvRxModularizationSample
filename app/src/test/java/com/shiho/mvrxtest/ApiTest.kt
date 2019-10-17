package com.shiho.mvrxtest

import com.shiho.base.model.NetworkHelper
import org.junit.Before
import org.junit.Test

/**
 * @ClassName: ApiTest
 * @Description: Test query api info.
 * @Date: 2019-09-18 14:55
 * @History:
 * <date> <version> <desc>
 */
class ApiTest {

    lateinit var networkHelper: NetworkHelper

    @Before
    fun initTest() {
        networkHelper = NetworkHelper("https://swapi.co/api/")
    }

    @Test
    fun getStarWarsPeople_NoValue_DataReturn() {

        val resultObserver = networkHelper.apiService.getStarWarsPeople().test()
        resultObserver.awaitTerminalEvent()

        resultObserver.assertValue {

            it.results!!.forEach {
                println(it!!.name)
            }

            return@assertValue true
        }

    }

}