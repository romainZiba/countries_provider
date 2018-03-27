package com.docdoku.countriesdisplayer

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_countries.*

class CountriesActivity : AppCompatActivity() {

    /**
     * TODO(1) Make the list of countries provided by the content provider appear in the RecyclerView
     * Initialize a CursorLoader by using the supportLoaderManager
     * Implement LoaderManager.LoaderCallbacks<Cursor> callbacks
     * Do not forget the manifest !
     * TODO(2) Add a button to the layout in order to add a country in db
     * Add a listener on the cursorLoader to listen to changes on a specific uri. Use method
     * Cursor.setNotificationUri
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countries)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }
}
