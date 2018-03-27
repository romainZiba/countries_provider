package com.docdoku.countriesdisplayer

import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.LoaderManager
import android.support.v4.content.CursorLoader
import android.support.v4.content.Loader
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_countries.*
import kotlinx.android.synthetic.main.content_countries.*

class CountriesActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Cursor> {

    /**
     * TODO(1) Make the list of countries provided by the content provider appear in the RecyclerView
     * Initialize a CursorLoader by using the supportLoaderManager
     * Implement LoaderManager.LoaderCallbacks<Cursor> callbacks
     * Do not forget the manifest !
     * TODO(2) Add a button to the layout in order to add a country in db
     * Add a listener on the cursorLoader to listen to changes on a specific uri. Use method
     * Cursor.setNotificationUri
     */

    private val uri = Uri.parse("content://com.docdoku.countriesprovider/countries")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countries)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        rv_countries_list.layoutManager = LinearLayoutManager(this)
        supportLoaderManager.initLoader<Cursor>(0, null, this)
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        val projection = arrayOf("name")
        return CursorLoader(this, uri, projection, null, null, "name")
    }

    override fun onLoadFinished(loader: Loader<Cursor>?, data: Cursor?) {
        if (data != null) {
            data.setNotificationUri(contentResolver, uri)
            val adapter = CountriesAdapter(data, this)
            rv_countries_list.adapter = adapter
        }
    }

    override fun onLoaderReset(loader: Loader<Cursor>?) {
    }
}
