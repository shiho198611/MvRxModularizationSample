package com.shiho.starwarships.allships

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.airbnb.mvrx.*
import com.shiho.base.coordinator.BaseCoordinator
import com.shiho.starwarships.R

/**
 * @ClassName: StarWarsStarshipsFragment
 * @Description: MvRx and multi module test fragment.
 * @Date: 2019-10-09 15:59
 * @History:
 * <date> <version> <desc>
 */
class StarWarsStarshipsFragment(val coordinator: BaseCoordinator): BaseMvRxFragment(), View.OnClickListener {

    private val starshipsViewModel: StarWarsStarshipsViewModel by fragmentViewModel()

    lateinit var shipTxt: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_starships, container, false)

        shipTxt = rootView.findViewById(R.id.txt_starships_name)

        rootView.findViewById<Button>(R.id.btn_query).setOnClickListener(this)

        return rootView
    }

    override fun invalidate() = withState(starshipsViewModel) { state ->
        when(state.ships) {
            is Loading -> shipTxt.text = "Loading..."
            is Uninitialized -> shipTxt.text = "No Data."
            is Success -> {
                val data = state.ships.invoke().results
                if(data?.isEmpty() == true) {
                    shipTxt.text = "No Api data."
                }
                else {
                    var result = ""
                    data?.forEach {
                        result += it?.name
                    }
                    shipTxt.text = result
                }

            }
            is Fail -> shipTxt.text = "Query data fail."
        }
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.btn_query) {
            starshipsViewModel.queryStarships()
        }
    }
}