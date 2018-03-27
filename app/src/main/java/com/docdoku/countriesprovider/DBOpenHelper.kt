package com.docdoku.countriesprovider

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by romainz on 27/03/18.
 */
class DBOpenHelper(context: Context) : SQLiteOpenHelper(context, NAME_DATABASE, null, DB_VERSION) {

    companion object {
        var NAME_DATABASE = "country.db"
        const val DB_VERSION = 1
        const val COUNTRY_TABLE = "country"
        const val CREATE_TABLES = "CREATE TABLE country (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)"
        const val DROP_TABLES = "DROP TABLE country"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLES)
        db.execSQL("INSERT INTO country VALUES (0, 'Allemagne')")
        db.execSQL("INSERT INTO country VALUES (1, 'Belgique')")
        db.execSQL("INSERT INTO country VALUES (2, 'Chine')")
        db.execSQL("INSERT INTO country VALUES (3, 'Danemark')")
        db.execSQL("INSERT INTO country VALUES (4, 'Espagne')")
        db.execSQL("INSERT INTO country VALUES (5, 'France')")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DROP_TABLES)
        db.execSQL(CREATE_TABLES)

        db.execSQL("INSERT INTO country VALUES (0, 'Allemagne')")
        db.execSQL("INSERT INTO country VALUES (1, 'Belgique')")
        db.execSQL("INSERT INTO country VALUES (2, 'Chine')")
        db.execSQL("INSERT INTO country VALUES (3, 'Danemark')")
        db.execSQL("INSERT INTO country VALUES (4, 'Espagne')")
        db.execSQL("INSERT INTO country VALUES (5, 'France')")
    }
}
