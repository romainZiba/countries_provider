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
        dbHelper = DBOpenHelper(context)
        db = dbHelper.writableDatabase
        return true
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?, selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        var cursor: Cursor? = null
        when (sURIMatcher.match(uri)) {
            COUNTRIES -> {
                db.beginTransaction()
                cursor = db.query(DBOpenHelper.COUNTRY_TABLE, projection, selection, selectionArgs, null, null, sortOrder)
                db.setTransactionSuccessful()
                db.endTransaction()
                return cursor
            }
            COUNTRIES_ID -> {
                val countryId = uri.lastPathSegment
                db.beginTransaction()
                val selectionFull: String = if (selection == null) {
                    "_id=?"
                } else {
                    "_id=?and $selection"
                }
                var selectionArgsFull: Array<String>?
                selectionArgsFull = if (selectionArgs != null) {
                    val args = mutableListOf<String>()
                    args.addAll(selectionArgs.toList())
                    args.add(0, countryId)
                    args.toTypedArray()
                } else {
                    selectionArgs
                }
                cursor = db.query(DBOpenHelper.COUNTRY_TABLE, projection, selectionFull, selectionArgsFull, null, null, sortOrder)
                db.endTransaction()
                return cursor
            }
        }
        return cursor
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        when (sURIMatcher.match(uri)) {
            COUNTRIES -> {
                db.beginTransaction()
                val generatedId = db.insert(DBOpenHelper.COUNTRY_TABLE, null, values)
                db.setTransactionSuccessful()
                db.endTransaction()
                context!!.contentResolver.notifyChange(uri, null)
                return Uri.withAppendedPath(uri, generatedId.toString() + "")
            }
        }
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        val db = dbHelper.writableDatabase
        var rows = 0

        when (sURIMatcher.match(uri)) {
            COUNTRIES -> {
                db.beginTransaction()
                rows = db.delete(DBOpenHelper.COUNTRY_TABLE, selection, selectionArgs)
                db.setTransactionSuccessful()
                db.endTransaction()
            }
            COUNTRIES_ID -> {
                val countryId = uri.lastPathSegment
                db.beginTransaction()
                val selectionFull: String = if (selection == null) {
                    "_id=?"
                } else {
                    "_id=? and $selection"
                }
                var selectionArgsFull: Array<String>?
                selectionArgsFull = if (selectionArgs != null) {
                    val args = mutableListOf<String>()
                    args.addAll(selectionArgs.toList())
                    args.add(0, countryId)
                    args.toTypedArray()
                } else {
                    selectionArgs
                }
                rows = db.delete(DBOpenHelper.COUNTRY_TABLE, selectionFull, selectionArgsFull)
                db.setTransactionSuccessful()
                db.endTransaction()
            }
        }

        return rows
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }
}
