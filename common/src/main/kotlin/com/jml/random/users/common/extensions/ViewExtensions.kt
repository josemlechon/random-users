package com.jml.random.users.common.extensions

import android.app.Activity
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Build
import android.os.Handler
import android.text.Html
import android.text.Html.FROM_HTML_MODE_LEGACY
import android.util.DisplayMetrics
import android.util.Patterns
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.CheckedTextView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.jml.random.users.common.view.BaseActivity
import com.jml.random.users.common.view.BaseFragment


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
            100)
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

fun Context.getColorFromId(@ColorRes colorResId: Int) =
    ResourcesCompat.getColor(resources, colorResId, null)

fun View.dpToPixels(dp: Int) =
    dp * resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT

fun Activity.dpToPixels(dp: Int) =
    dp * resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT

fun View.pixelsToDp(pixels: Int) =
    pixels / (resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)

fun Fragment.hideKeyboard() {
    activity?.currentFocus?.let {
        val imm = activity?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

fun Activity.hideKeyboard() {
    currentFocus?.let {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

fun Fragment.showKeyboard() {
    activity?.let {
        val imm = it.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
    }
}

fun ImageView.setDrawable(@DrawableRes drawableRes: Int) {
    setImageDrawable(ContextCompat.getDrawable(context, drawableRes))
}

fun Activity.setToolbarTitle(@StringRes toolbarTitle: Int) {
    actionBar?.setTitle(toolbarTitle)
}

fun BaseFragment.setToolbarTitle(@StringRes toolbarTitle: Int) {
    (activity as BaseActivity).actionBar?.setTitle(toolbarTitle)
}

fun BaseFragment.setToolbarTitle(toolbarTitle: String) {
    (activity as BaseActivity).actionBar?.title = toolbarTitle
}

/**
 * Sets the Toolbar title and display the navigate back arrow
 * @param toolbarTitle: String
 * @param titleView: TextView
 */
fun BaseFragment.setToolbarWithBack(toolbarTitle: String, titleView: TextView? = null) {
    if (activity !is BaseActivity) return

    (activity as BaseActivity).supportActionBar
        ?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)

            if (titleView != null) {
                titleView.text = toolbarTitle
                title = ""
            } else {
                title = toolbarTitle
            }
        }
}

fun Activity.hasConnectivityStatus(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    return null != activeNetwork && activeNetwork.isConnected
}

fun Fragment.hasConnectivityStatus(context: Context?): Boolean {
    context?.let {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return null != activeNetwork && activeNetwork.isConnected
    }
    return true
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
/*
fun ImageView.loadFromUrl(url: String?, crop: Boolean = false, @DrawableRes pinHolder: Int? = null) {
    url?.takeIf { !url.isNullOrEmpty() }
        ?.let {
            val requestCreator = Picasso.get().load(url)

            if (pinHolder != null) {
                requestCreator.placeholder(pinHolder)
                requestCreator.error(pinHolder)
            }
            if (crop) {
                requestCreator
                    .fit()
                    .centerInside()
            }

            requestCreator.into(this)
        }
}

fun ImageView.loadRoundedFromUrl(url: String?, @DrawableRes placeholder: Int? = null) {
    url?.takeIf { !url.isNullOrEmpty() }
        ?.let {
            Picasso.get()
                .load(url)
                .let {
                    if (placeholder != null) {
                        it.placeholder(placeholder)
                    } else {
                        it
                    }
                }
        }?.transform(CircleTransform())
        ?.into(this)
}
*/
fun ViewPager.setCarouselEffect(padding: Int, margin: Int, pageTransformer: ViewPager.PageTransformer) {
    clipToPadding = false
    pageMargin = margin
    setPadding(padding, 0, padding, 0)
    setPageTransformer(false, pageTransformer)
}

fun String.parseColor(): Int {
    return Color.parseColor(this)
}

fun String.parseHtml(): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, FROM_HTML_MODE_LEGACY).toString()
    } else {
        Html.fromHtml(this).toString()
    }
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

/**
 * Explanation why https://github.com/square/picasso/issues/609 by Jake Warthon
 */
fun String?.validPicassoUrl(): String? {
    return when {
        isNullOrEmpty() -> null
        !Patterns.WEB_URL.matcher(this).matches() -> null
        else -> this
    }
}