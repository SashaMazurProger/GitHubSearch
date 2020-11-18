package com.sashamprog.githubsearch.ui.fragment.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sashamprog.githubsearch.R
import com.sashamprog.githubsearch.base.BaseViewHolder
import com.sashamprog.githubsearch.data.provider.github.entity.SearchRepositoryResult
import kotlinx.android.synthetic.main.recycler_item_search.view.*

class AdapterRepository(
    internal var onClickItemListener: ((item: SearchRepositoryResult.Item) -> Unit)? = null
) : RecyclerView.Adapter<BaseViewHolder>() {

    private val mItems: ArrayList<SearchRepositoryResult.Item> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            BaseViewHolder.VIEW_TYPE_NORMAL ->
                return ItemHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.recycler_item_search,
                        parent,
                        false
                    )
                )
            else -> EmptyViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.empty_holder,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) = holder.onBind(position)

    override fun getItemCount(): Int = if (mItems.isNotEmpty()) mItems.count() else 1

    override fun getItemViewType(position: Int): Int =
        if (mItems.isEmpty())
            BaseViewHolder.VIEW_TYPE_EMPTY
        else
            BaseViewHolder.VIEW_TYPE_NORMAL

    fun setItems(items: List<SearchRepositoryResult.Item>) {
        if (items.isNotEmpty()) {
            mItems.clear()
            mItems.addAll(items)
        } else {
            mItems.clear()
        }
        notifyDataSetChanged()
    }

    inner class ItemHolder(private var view: View) : BaseViewHolder(view) {

        override fun onBind(position: Int) {
            super.onBind(position)
            val item = mItems[position]
            view.apply {
                text_view_full_name.text = item.fullName
                this.setOnClickListener { onClickItemListener?.invoke(item) }
            }
        }
    }

    inner class EmptyViewHolder(view: View) : BaseViewHolder(view)
}
