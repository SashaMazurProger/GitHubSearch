package com.sashamprog.githubsearch.ui.activity

import android.os.Bundle
import com.sashamprog.githubsearch.R
import com.sashamprog.githubsearch.base.BaseActivity
import org.koin.android.ext.android.inject
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command


class SingleActivity : BaseActivity(),
    SingleActivityI.View {

    private val navigatorHolder: NavigatorHolder by inject()
    override val presenter: SingleActivityI.Presenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.single_activity)
        presenter.onAttach(this)
        presenter.navigateScreens()
    }

    override fun onResumeFragments() {
        navigatorHolder.setNavigator(navigator)
        super.onResumeFragments()
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    private val navigator = object : SupportAppNavigator(
        this,
        R.id.container_parent
    ) {

        override fun applyCommands(commands: Array<Command>) {
            super.applyCommands(commands)
            supportFragmentManager.executePendingTransactions()
        }
    }
}
