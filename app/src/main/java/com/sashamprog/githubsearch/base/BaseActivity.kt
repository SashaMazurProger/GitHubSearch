package com.sashamprog.githubsearch.base

import android.annotation.TargetApi
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.sashamprog.githubsearch.R
import kotlinx.coroutines.Job


abstract class BaseActivity : AppCompatActivity(), CoroutinesComponentI {

    override val asyncJobs: MutableList<Job?> = mutableListOf()

    fun showMessage(message: String?) {
        Toast.makeText(
            this, message
                ?: getString(R.string.error_unknown), Toast.LENGTH_SHORT
        ).show()
    }

    fun showMessage(@StringRes resId: Int) {
        showMessage(getString(resId))
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissionsActivity(permissions: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            requestPermissions(permissions, requestCode)
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissionsFragment(
        fragment: BaseFragment,
        permissions: Array<String>,
        requestCode: Int
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            fragment.requestPermissions(permissions, requestCode)
    }

    fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE)
                as InputMethodManager
        imm.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun hasPermission(permission: String): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    override fun onDestroy() {
        super.onDestroy()
        cancelAllAsync()
    }

}