package news.com.newsapp.home.view

import android.app.FragmentManager
import android.app.SearchManager
import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import news.com.newsapp.NewsApplication
import news.com.newsapp.R
import news.com.newsapp.base.BaseActivity
import news.com.newsapp.base.BaseFragment
import news.com.newsapp.databinding.ActivityMainBinding
import news.com.newsapp.utils.FragmentHelper


class MainActivity : BaseActivity() {

    lateinit var menu: Menu
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)

        initViews()
        initData()

//        (applicationContext as NewsApplication).getNewsComponent()!!.inject(this)
    }

    private fun initViews() {

        FragmentHelper.addFragment(this, R.id.home_container, HomeFragment.newInstance())

    }

    private fun initData() {


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        this@MainActivity.menu = menu!!
        menuInflater.inflate(R.menu.menu_home, menu)

        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setOnCloseListener(object : SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                return true
            }
        })

        searchView.setOnSearchClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {

            }
        })

        val searchPlate = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text) as EditText
        searchPlate.setTextColor(Color.WHITE)
        searchPlate.setHintTextColor(Color.WHITE)
        searchPlate.hint = getString(R.string.search_hint)
        val searchPlateView = searchView.findViewById(android.support.v7.appcompat.R.id.search_plate) as View
        searchPlateView.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent))
        // use this method for search process
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // use this method when query submitted
                FragmentHelper.replaceFragment(this@MainActivity, R.id.home_container,
                        AllFeedsFragment.newInstance(null, false, query), true)
                invalidateOptionsMenu()
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // use this method for auto complete search process
                return false
            }
        })

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item!!.getItemId() == android.R.id.home) {
            onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

}