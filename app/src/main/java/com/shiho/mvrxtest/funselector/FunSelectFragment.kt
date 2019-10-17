package com.shiho.mvrxtest.funselector

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.shiho.base.coordinator.BaseCoordinator
import com.shiho.mvrxtest.R

/**
 * @ClassName: FunSelectFragment
 * @Description: Select fragment for goto all function.
 * @Date: 2019-10-16 16:40
 * @History:
 * <date> <version> <desc>
 */
class FunSelectFragment(val coordinator: BaseCoordinator): Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_fun_select, container, false)

        root.findViewById<Button>(R.id.btn_goto_people).setOnClickListener(this)
        root.findViewById<Button>(R.id.btn_goto_ships).setOnClickListener(this)

        return root
    }

    override fun onClick(v: View?) {
        coordinator.next(v?.id)
    }
}