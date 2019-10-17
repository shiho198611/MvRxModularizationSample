package com.shiho.starwarpeople.peopledetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.shiho.starwarpeople.R
import com.shiho.starwarpeople.StarWarsPeopleCoordinator

/**
 * @ClassName: StarWarsPeopleDetailFragment
 * @Description: Coordinator second testing fragment.
 * @Date: 2019-10-14 17:46
 * @History:
 * <date> <version> <desc>
 */
class StarWarsPeopleDetailFragment(val coordinator: StarWarsPeopleCoordinator): Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_people_detail, container, false)

        root.findViewById<Button>(R.id.btn_detail_people_back).setOnClickListener(this)

        return root
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.btn_detail_people_back) {
            coordinator.back()
        }
    }
}