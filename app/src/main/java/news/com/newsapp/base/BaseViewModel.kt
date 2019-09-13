package news.com.newsapp.base

import android.arch.lifecycle.ViewModel
import android.databinding.Observable
import android.databinding.PropertyChangeRegistry
import java.util.*


/**
 * Created by Tarak on 23,April,2018
 *
 */

open class BaseViewModel : ViewModel(), Observable {

    private var mCallbacks: PropertyChangeRegistry? = null

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

        synchronized(this) {
            if (mCallbacks == null) {
                mCallbacks = PropertyChangeRegistry()
            }
            mCallbacks!!.add(callback)
        }
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        synchronized(this) {
            if (mCallbacks == null) {
                return
            }
        }
        mCallbacks!!.remove(callback)
    }

}