package com.jml.random.users.common.view

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity


abstract class BaseActivity : AppCompatActivity() {

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (android.R.id.home == item?.itemId) {
            onBackPressed()
            true
        } else super.onOptionsItemSelected(item)
    }
}

