package news.com.newsapp.home.view.adapter.custom

import android.view.ViewGroup
import android.widget.AdapterView
import android.support.v4.view.PagerAdapter
import android.view.View


/**
 * Created by Tarak on 27,April,2018
 *
 */
abstract class RecyclingPagerAdapter internal constructor(private val recycleBin: RecycleBin) : PagerAdapter() {

    /**
     *
     *
     * Returns the number of types of Views that will be created by
     * [.getView]. Each type represents a set of views that can be
     * converted in [.getView]. If the adapter always returns the same
     * type of View for all items, this method should return 1.
     *
     *
     *
     * This method will only be called when when the adapter is set on the
     * the [AdapterView].
     *
     *
     * @return The number of types of Views that will be created by this adapter
     */
    val viewTypeCount: Int
        get() = 1

    constructor() : this(RecycleBin()) {}

    init {
        recycleBin.setViewTypeCount(viewTypeCount)
    }

    override fun notifyDataSetChanged() {
        recycleBin.scrapActiveViews()
        super.notifyDataSetChanged()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any? {
        val viewType = getItemViewType(position)
        var view: View? = null
        if (viewType != IGNORE_ITEM_VIEW_TYPE) {
            view = recycleBin.getScrapView(position, viewType)
        }
        view = getView(position, view, container)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
        val viewType = getItemViewType(position)
        if (viewType != IGNORE_ITEM_VIEW_TYPE) {
            recycleBin.addScrapView(view, position, viewType)
        }
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    /**
     * Get the type of View that will be created by [.getView] for the specified item.
     *
     * @param position The position of the item within the adapter's data set whose view type we
     * want.
     * @return An integer representing the type of View. Two views should share the same type if one
     * can be converted to the other in [.getView]. Note: Integers must be in the
     * range 0 to [.getViewTypeCount] - 1. [.IGNORE_ITEM_VIEW_TYPE] can
     * also be returned.
     * @see .IGNORE_ITEM_VIEW_TYPE
     */
    // Argument potentially used by subclasses.
    fun getItemViewType(position: Int): Int {
        return 0
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * [android.view.LayoutInflater.inflate]
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position The position of the item within the adapter's data set of the item whose view
     * we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     * is non-null and of an appropriate type before using. If it is not possible to convert
     * this view to display the correct data, this method can create a new view.
     * Heterogeneous lists can specify their number of view types, so that this View is
     * always of the right type (see [.getViewTypeCount] and
     * [.getItemViewType]).
     * @param parent The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    abstract fun getView(position: Int, convertView: View?, container: ViewGroup): View

    companion object {
        internal val IGNORE_ITEM_VIEW_TYPE = AdapterView.ITEM_VIEW_TYPE_IGNORE
    }
}