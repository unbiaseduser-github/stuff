package com.sixtyninefourtwenty.stuff.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView

@Suppress("unused")
class ViewsViewPagerAdapter(private val views: List<View>) : RecyclerView.Adapter<ViewsViewPagerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(FrameLayout(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        })

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(views[position])
    }

    override fun getItemCount(): Int = views.size

    class ViewHolder(private val frame: FrameLayout) : RecyclerView.ViewHolder(frame) {
        fun bind(view: View) {
            with(frame) {
                removeAllViews()
                addView(view)
            }
        }
    }

}