package com.jml.random.users.common.extensions

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.jml.random.users.common.R
import com.jml.random.users.common.exceptions.ErrorType

/**
 * Show the generic error Dialog.
 */

fun Context.showErrorDialog(error: ErrorType, ok: (() -> Unit)? = null, retry: (() -> Unit)? = null) {


    val message = if (error == ErrorType.GENERIC) {
        R.string.error_generic
    } else {
        R.string.error_no_internet
    }

    val builder = AlertDialog.Builder(this)
        .setMessage(message)
        .setCancelable(false)

    builder.setPositiveButton(R.string.action_ok)
    { dialog, _ ->
        ok?.invoke()
        dialog.dismiss()
    }

    if (retry != null) {
        builder.setNegativeButton(R.string.action_retry)
        { dialog, _ ->
            retry.invoke()
            dialog.dismiss()
        }
    }

    builder.show()
}
