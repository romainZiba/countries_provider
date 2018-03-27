package com.docdoku.countriesprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri

/**
 * Created by romainz on 27/03/18.
 */
class CountryContentProvider : ContentProvider() {

    /** TODO: implement the different methods of the content provider to query, insert, delete,
     * update elements depending of the URI the client targets
     */

    companion object {
        private const val AUTHORITY = "com.docdoku.countriesprovider"
        private const val COUNTRIES = 1
        private const val COUNTRIES_ID = 2
        private var sURIMatcher = UriMatcher(UriMatcher.NO_MATCH)
        private const val COUNTRY_BASE_PATH = "countries"

        init {
            sURIMatcher.addURI(AUTHORITY, COUNTRY_BASE_PATH, COUNTRIES)
            sURIMatcher.addURI(AUTHORITY, "$COUNTRY_BASE_PATH/#", COUNTRIES_ID)
        }
    }

    private lateinit var dbHelper: DBOpenHelper
    private lateinit var db: SQLiteDatabase

    override fun onCreate(): Boolean {
        return true
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?, selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        return null
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }
}
