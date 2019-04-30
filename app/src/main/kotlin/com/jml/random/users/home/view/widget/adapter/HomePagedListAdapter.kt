package com.jml.random.users.home.view.widget.adapter

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.jml.random.users.R
import com.jml.random.users.common.extensions.inflate
import com.jml.random.users.common.extensions.loadCircleFromUrl
import com.jml.random.users.common.extensions.setClickListener
import com.jml.random.users.common.view.widget.adapter.BaseRecyclerAdapter
import com.jml.random.users.home.view.model.UserBriefUI
import com.jml.random.users.users.domain.model.User
import kotlinx.android.synthetic.main.item_home_user.view.*

class HomePagedListAdapter : PagedListAdapter<User, HomePagedListAdapter.ViewHolder>(movieDiffCallback) {

    var doOnItemClick: ((position: Int, id: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_home_user))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let(holder::bind)

    }


    inner class ViewHolder(itemView: View) : BaseRecyclerAdapter.BaseViewHolder<User>(itemView) {

        override fun bind(item: User) {

            itemView.apply {
                home_user_full_name_image.text = item.getFullName().capitalize()
                home_user_email_text.text = item.email
                home_user_phone_text.text = item.phone

                home_user_head_image.loadCircleFromUrl(item.pictures.medium)

                setClickListener(::onItemClick)
            }
        }

        private fun onItemClick() {
            getItem(adapterPosition)
                ?.takeIf { doOnItemClick != null }
                ?.let {
                    doOnItemClick!!.invoke(adapterPosition, it.id)
                }
        }
    }

    companion object {
        private val movieDiffCallback = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }
}
