package com.jml.random.users.common.extensions

/**
 * Show the generic error Dialog with retry.
 * @param ok - Function to execute on Ok click (optional)
 * @param cancel - Function to execute on Cancel click (optional)

fun Context.showDialogRetry(ok: (() -> Unit)? = null, cancel: (() -> Unit)? = null) {
    val contextThemeWrapper = ContextThemeWrapper(this, R.style.Alert)
    AlertDialog.Builder(contextThemeWrapper)
        .apply {
            setTitle(R.string.alertdialog_text_title)
            setMessage(R.string.alertdialog_text)
            setNegativeButton(R.string.alert_action_close) { dialog, _ ->
                cancel?.invoke()
                dialog.dismiss()
            }
            takeIf { ok != null }
                ?.let {
                    it.setPositiveButton(R.string.alert_action_retry) { dialog, _ ->
                        ok?.invoke()
                        dialog.dismiss()
                    }
                }

            setCancelable(false)
        }
        .create()
        .show()
}
 */
/**
 * Show an error Snackbar with retry.
 * @param body - Function to execute on retry (optional).

fun BaseFragment.showSnackbarRetry(body: (() -> Unit)? = null) {

    view?.let {
        val snack = Snackbar
            .make(it, R.string.snackbar_text, Snackbar.LENGTH_LONG)
            .setActionTextColor(ContextCompat.getColor(requireContext(), R.color.oceanBlue))

        if (body != null) {
            snack.setAction(R.string.alert_action_retry) { body.invoke() }
        }
        snack.show()
    }
}
 */
/**
 * Show a custom Dialog with assignable actions for buttons.
 * @param title - Dialog title. Must be a @StringRes
 * @param message - Dialog message. Must be a @StringRes
 * @param ok - Function to execute on Ok click (optional)
 * @param cancel - Function to execute on Cancel click (optional)
 * @param okText - Ok button text. Must be a @StringRes
 * @param cancelText - Cancel button text. Must be a @StringRes

fun Context.showDialog(
    @StringRes title: Int = R.string.text_no_title,
    @StringRes message: Int,
    ok: (() -> Unit)? = null,
    cancel: (() -> Unit)? = null,
    @StringRes okText: Int = R.string.ok_text,
    @StringRes cancelText: Int? = null
) {

    val contextThemeWrapper = ContextThemeWrapper(this, R.style.Alert)
    val builder = AlertDialog.Builder(contextThemeWrapper)
        .setTitle(title)
        .setMessage(message)

    builder.setPositiveButton(okText) { dialog, _ ->
        ok?.invoke()
        dialog.dismiss()
    }
    builder.takeIf { cancelText != null }
        ?.let {
            it.setNegativeButton(cancelText!!) { dialog, _ ->
                cancel?.invoke()
                dialog.dismiss()
            }
        }

    builder.setCancelable(false)
    builder.show()
}*/

/**
 * Show the generic error Dialog.

fun Context.showGenericErrorDialog(error: Int = R.string.generic_error) {
    val contextThemeWrapper = ContextThemeWrapper(this, R.style.Alert)
    AlertDialog.Builder(contextThemeWrapper)
        .setMessage(error)
        .setPositiveButton(R.string.ok_text) { dialog, _ -> dialog.dismiss() }
        .setCancelable(false)
        .show()
}
 */
/**
 * Show the no internet connection Dialog.

fun Context.showNoInternetDialog() {
    val contextThemeWrapper = ContextThemeWrapper(this, R.style.Alert)
    AlertDialog.Builder(contextThemeWrapper)
        .setTitle(R.string.alert_no_internet_title)
        .setMessage(R.string.alert_no_internet_text)
        .setPositiveButton(R.string.ok_text) { dialog, _ -> dialog.dismiss() }
        .setNegativeButton(R.string.alert_no_internet_config_text) { dialog, _ ->
            startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
            dialog.dismiss()
        }
        .setCancelable(false)
        .show()
}
 */
/**
 * Show the generic error Dialog.

fun Context.showErrorDialog(error: ErrorType, ok: (() -> Unit)? = null, retry: (() -> Unit)? = null) {
    val contextThemeWrapper = ContextThemeWrapper(this, R.style.Alert)

    val message = if (error == ErrorType.GENERIC) {
        R.string.generic_error
    } else {
        R.string.alert_no_internet_title
    }

    val builder = AlertDialog.Builder(contextThemeWrapper)
        .setMessage(message)
        .setCancelable(false)

    builder.setPositiveButton(R.string.ok_text)
    { dialog, _ ->
        ok?.invoke()
        dialog.dismiss()
    }

    if (retry != null) {
        builder.setNegativeButton(R.string.alert_action_retry)
        { dialog, _ ->
            retry.invoke()
            dialog.dismiss()
        }
    }

    builder.show()
}
 */