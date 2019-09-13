package news.com.newsapp.utils

import android.app.Activity
import android.app.Fragment

/**
 * Created by Tarak on 24,April,2018
 *
 */
class FragmentHelper {

    companion object {
        fun addFragment(activity: Activity, containerId: Int, fragment: Fragment) {

            val transaction = activity.fragmentManager.beginTransaction()
            transaction.add(containerId, fragment)
            transaction.commit()

        }

        fun replaceFragment(activity: Activity, containerId: Int, fragment: Fragment, addToBackStack: Boolean) {

            val transaction = activity.fragmentManager.beginTransaction()
            transaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out, android.R.animator.fade_in, android.R.animator.fade_out)
            transaction.replace(containerId, fragment)
            if (addToBackStack)
                transaction.addToBackStack(fragment.javaClass.name)
            transaction.commitAllowingStateLoss()

        }
    }

}