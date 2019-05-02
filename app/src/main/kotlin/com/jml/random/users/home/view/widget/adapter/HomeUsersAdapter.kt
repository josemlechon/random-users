package com.jml.random.users.home.view.widget.adapter

import android.view.View
import android.view.ViewGroup
import com.jml.random.users.R
import com.jml.random.users.common.extensions.inflate
import com.jml.random.users.common.extensions.loadCircleFromUrl
import com.jml.random.users.common.extensions.setClickListener
import com.jml.random.users.common.view.widget.adapter.BaseRecyclerAdapter
import com.jml.random.users.home.view.model.UserBriefUI
import kotlinx.android.synthetic.main.item_home_user.view.*

class HomeUsersAdapter : BaseRecyclerAdapter<HomeUsersAdapter.ViewHolder, UserBriefUI>() {

    var doOnItemClick: ((position: Int, id: String) -> Unit)? = null
    var doDeleteUserClick: ((position: Int, id: String) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_home_user))
    }

    inner class ViewHolder(itemView: View) : BaseRecyclerAdapter.BaseViewHolder<UserBriefUI>(itemView) {

        override fun bind(item: UserBriefUI) {

            itemView.apply {
                home_user_full_name_text.text = item.fullName.capitalize()
                home_user_email_text.text = item.email
                home_user_phone_text.text = item.phone

                home_user_head_image.loadCircleFromUrl(item.picture)

                home_user_delete_button.setClickListener(::onDeleteItemClicked)
                setClickListener(::onItemClick)
            }
        }

        private fun onItemClick() {
            getItem(adapterPosition)
                ?.takeIf { doOnItemClick != null }
                ?.let {
                    doOnItemClick?.invoke(adapterPosition, it.id)
                }
        }

        private fun onDeleteItemClicked() {
            getItem(adapterPosition)
                ?.takeIf { doDeleteUserClick != null }
                ?.let {
                    doDeleteUserClick?.invoke(adapterPosition, it.id)
                }
        }
    }


}