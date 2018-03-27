package com.docdoku.countriesdisplayer

import android.content.Context
import android.database.Cursor
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by romainz on 27/03/18.
 */
class CountriesAdapter(private val mCursor: Cursor, private val mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return CountryViewHolder(TextView(mContext))
    }

    override fun getItemCount(): Int {
        return mCursor.count
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        mCursor.moveToPosition(position)
        (holder?.itemView as TextView).text = mCursor.getString(mCursor.getColumnIndex("name"))
    }

    class CountryViewHolder(mView: View) : RecyclerView.ViewHolder(mView)
}