package com.shiho.mvrxtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shiho.base.coordinator.CoordinatorEvent
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

class MainActivity : AppCompatActivity() {

    lateinit var group: CoordinatorGroup
    lateinit var presentDisposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        group = CoordinatorGroup()

        presentDisposable = group.present()
            .subscribe(FragmentConsumer())
    }

    override fun onDestroy() {
        super.onDestroy()
        if (presentDisposable.isDisposed) {
            presentDisposable.dispose()
        }

    }

    override fun onBackPressed() {
        group.keyBack()
    }

    inner class FragmentConsumer : Consumer<Any> {
        override fun accept(it: Any?) {
            if (it is CoordinatorEvent.FragmentCommit) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, it.fragment)
                    .commit()
            } else if (it is CoordinatorEvent.AppFinish) {
                finish()
            }
        }
    }
}
