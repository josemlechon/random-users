package com.jml.random.users.common.extensions

import android.app.Activity
import android.graphics.Color
import android.os.Handler
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.jml.random.users.common.view.BaseActivity


fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Fragment.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    activity?.let {
        Toast.makeText(it, message, duration).show()
    }
}

fun ViewGroup.inflate(@LayoutRes layoutId: Int): View {
    return LayoutInflater.from(context).inflate(layoutId, this, false)
}

fun ViewGroup.inflateCustomView(@LayoutRes layoutRes: Int, attachToRoot: Boolean = true): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun View.setClickListener(body: () -> Unit) {
    this.setOnClickListener {
        it.isEnabled = false
        body.invoke()

        Handler().postDelayed(
            { it.isEnabled = true },
            100
        )
    }
}

fun CheckedTextView.setCheckBoxClickListener(body: (Boolean) -> Unit) {
    setOnClickListener { view ->
        view as CheckedTextView
        view.isChecked = !isChecked
        body.invoke(view.isChecked)
    }
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.isVisible() = visibility == View.VISIBLE

fun View.visibleGone(status: Boolean?) {
    visibility = if (status != null && status) View.VISIBLE else View.GONE
}

fun View.visibleInvisible(status: Boolean?) {
    visibility = if (status != null && status) View.VISIBLE else View.INVISIBLE
}

fun Activity.inflateMenu(@MenuRes menuIdRes: Int, menu: Menu?): Boolean {
    menu.let { menuInflater.inflate(menuIdRes, menu) }
    return true
}

fun ImageView.setDrawable(@DrawableRes drawableRes: Int) {
    setImageDrawable(ContextCompat.getDrawable(context, drawableRes))
}

fun BaseActivity.setToolbarTitle(@StringRes toolbarTitle: Int) {
    supportActionBar?.setTitle(toolbarTitle)
}
/**
 * Sets the Toolbar title and display the navigate back arrow
 */
fun BaseActivity.setToolbarWithBack(toolbar: Toolbar) {

    setSupportActionBar(toolbar)

    supportActionBar
        ?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
}

fun View.getString(@StringRes id: Int, vararg formatArgs: Any): String {
    return resources.getString(id, *formatArgs)
}

fun TextView.setString(@StringRes id: Int, vararg formatArgs: Any?) {
    this.text = resources.getString(id, *formatArgs)
}

/**
 * Load an url image into the ImageView
 */

fun ImageView.loadCircleFromUrl(url: String, @DrawableRes pinHolder: Int? = null) {
    url.let {
        val requestCreator = Glide.with(this).load(url)

        pinHolder?.let {
            requestCreator
                .placeholder(it)
                .error(it)
        }

        requestCreator.circleCrop()

        requestCreator.into(this)
    }
}

fun String.parseColor(): Int {
    return Color.parseColor(this)
}

fun TextView.getString(): String {
    return text.toString()
}

fun TextView.getInt(): Int {
    return try {
        text.toString().toInt()
    } catch (ex: Exception) {
        0
    }
}