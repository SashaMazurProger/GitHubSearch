package com.sashamprog.githubsearch.base

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import kotlinx.coroutines.Job


abstract class BaseFragment : Fragment(), CoroutinesComponentI {

    override val asyncJobs: MutableList<Job?> = mutableListOf()

    var baseActivity: BaseActivity? = null
        private set

    @TargetApi(23)
    override fun onAttach(context: Context) {
        super.onAttach(context)
        onAttachToContext(context)
    }

    @Suppress("DEPRECATION", "OverridingDeprecatedMember")
    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            onAttachToContext(activity)
    }

    private fun onAttachToContext(context: Context) {
        if (context is BaseActivity) {
            try {
                this.baseActivity = context
            } catch (e: NullPointerException) {
                Log.e("error", e.toString())
            }
        }
    }

    fun showMessage(message: String?) {
        baseActivity?.showMessage(message)
    }

    fun showMessage(@StringRes resId: Int) {
        baseActivity?.showMessage(resId)
    }

    override fun onDestroy() {
        cancelAllAsync()
        baseActivity = null
        super.onDestroy()
    }
}
