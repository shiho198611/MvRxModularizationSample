package com.shiho.starwarpeople.allpeople

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.airbnb.mvrx.*
import com.shiho.base.coordinator.BaseCoordinator
import com.shiho.starwarpeople.R
import com.shiho.starwarpeople.StarWarsPeopleCoordinator

/**
 * @ClassName: StarWarsPeopleFragment
 * @Description: MvRx test fragment.
 * @Date: 2019-09-19 17:56
 * @History:
 * <date> <version> <desc>
 */
class StarWarsPeopleFragment(val coordinator: BaseCoordinator): BaseMvRxFragment(), View.OnClickListener {

    private val viewModel: StarWarsPeopleViewModel by fragmentViewModel()
    private lateinit var txtPeople: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_people, container, false)

        root.findViewById<Button>(R.id.btn_query_people).setOnClickListener(this)
        root.findViewById<Button>(R.id.btn_people_next).setOnClickListener(this)
        txtPeople = root.findViewById(R.id.txt_people_name)

        return root
    }

    override fun invalidate() = withState(viewModel) { state ->

        when(state.people) {
            is Loading -> txtPeople.text = "Loading..."
            is Success -> {
                val data = state.people.invoke()
                if(data.results?.isNotEmpty() == true) {
                    var textData = ""
                    data.results?.forEach {
                        textData+=it?.name+", "
                    }

                    txtPeople.text = textData
                }
            }
            is Uninitialized -> txtPeople.text = "No Data"
            is Fail -> txtPeople.text = "API service error"
        }

    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.btn_query_people) {
            viewModel.getStarWarsPeoples()
        }
        else if(v?.id == R.id.btn_people_next) {
            coordinator.next()
        }
    }

}