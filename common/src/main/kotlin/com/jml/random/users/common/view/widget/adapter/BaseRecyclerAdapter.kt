package com.jml.random.users.common.view.widget.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.jml.random.users.common.extensions.inflate

abstract class BaseRecyclerAdapter<T : BaseRecyclerAdapter.BaseViewHolder<V>, V>
    : RecyclerView.Adapter<T>() {

    private var dataSet: MutableList<V> = ArrayList()

    protected fun inflate(parent: ViewGroup, @LayoutRes idLayout: Int): View {
        return parent.inflate(idLayout)
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

   open fun getItem(position: Int): V? {
        return if (position != RecyclerView.NO_POSITION) dataSet[position] else null
    }

    fun setData(data: List<V>) {
        dataSet = data.toMutableList()
        notifyDataSetChanged()
    }

    fun replaceData(data: List<V>) {
        updateAndNotifyDataListChanged(data.toMutableList())
    }

    fun addItem(position: Int, model: V) {
        dataSet.add(position, model)

        notifyItemInserted(position)
    }

    fun addData(data: List<V>) {

        val sizeCurrentRange = dataSet.size

        dataSet.addAll(data)

        notifyItemRangeInserted(sizeCurrentRange, data.size)
    }

    fun removeAt(position: Int) {
        val newDataSet: MutableList<V> = ArrayList()
        newDataSet.addAll(dataSet)
        newDataSet.removeAt(position)
        updateAndNotifyDataListChanged(newDataSet)
    }

    fun clear() {
        dataSet.clear()
        notifyDataSetChanged()
    }

    fun replace(i: Int, item: V) {
        dataSet[i] = item
        notifyItemChanged(i)
    }

    fun getAllItems(): List<V> = dataSet

    abstract class BaseViewHolder<in V> constructor(itemView: View)
        : RecyclerView.ViewHolder(itemView) {

        abstract fun bind(item: V)
    }

    private fun updateAndNotifyDataListChanged(dataList: MutableList<V>) {
        val diffResult = DiffUtil.calculateDiff(DiffCallBack(dataList, dataSet), true)
        dataSet.clear()
        dataSet.addAll(dataList)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class DiffCallBack(
        private val newList: MutableList<V>,
        private val oldList: MutableList<V>
    ) : DiffUtil.Callback() {

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]
    }
}