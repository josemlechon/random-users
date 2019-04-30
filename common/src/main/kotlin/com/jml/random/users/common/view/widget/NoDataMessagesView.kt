package com.jml.random.users.common.view.widget

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.jml.random.users.common.R
import com.jml.random.users.common.extensions.gone
import com.jml.random.users.common.extensions.inflateCustomView
import com.jml.random.users.common.extensions.visible

import kotlinx.android.synthetic.main.compound_no_data_messages.view.*

class NoDataMessagesView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        inflateCustomView(R.layout.compound_no_data_messages)
        visible(false)
    }

    fun setTitle(title: String) {
        no_data_messages_title_text.text = title
    }

    fun setMessage(title: String) {
        no_data_messages_description_text.text = title
    }

    fun visible(status: Boolean) {
        if (status) {
            visible()
        } else {
            gone()
        }
    }
}