package com.jml.random.users.common.extensions

import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.viewpager.widget.ViewPager
import androidx.core.os.HandlerCompat.postDelayed
import android.os.Looper


fun EditText.onChange(
    after: ((String) -> Unit)? = null,
    before: ((String) -> Unit)? = null,
    whenChange: ((String) -> Unit)? = null
) {

    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            after?.invoke(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            before?.invoke(s.toString())
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            whenChange?.invoke(s.toString())
        }
    })
}

fun EditText.onChangeDelayed( time: Long = 500,
    whenChange: ((String) -> Unit)? = null
) {

    this.addTextChangedListener(object : TextWatcher {

        var handler = Handler(Looper.getMainLooper() /*UI thread*/)

        var workRunnable: Runnable? = null

        override fun afterTextChanged(s: Editable?) {}

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            handler.removeCallbacks(workRunnable)
            workRunnable = Runnable { whenChange?.invoke(s.toString()) }
            handler.postDelayed(workRunnable, time)

        }
    })
}

fun ViewPager.onPage(
    onPageSelected: ((position: Int) -> Unit)? = null,
    onScrolled: ((position: Int, positionOffset: Float, positionOffsetPixels: Int) -> Unit)? = null,
    onScrollChange: ((state: Int) -> Unit)? = null
) {

    this.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
            onScrollChange?.invoke(state)
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            onScrolled?.invoke(position, positionOffset, positionOffsetPixels)
        }

        override fun onPageSelected(position: Int) {
            onPageSelected?.invoke(position)
        }
    })
}