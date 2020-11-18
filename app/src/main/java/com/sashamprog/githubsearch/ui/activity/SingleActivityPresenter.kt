package com.sashamprog.githubsearch.ui.activity

import com.sashamprog.githubsearch.base.BasePresenter
import com.sashamprog.githubsearch.utils.navigation.Screens
import ru.terrakok.cicerone.Router

class SingleActivityPresenter(
    router: Router
) : BasePresenter<SingleActivityI.View>(router),
    SingleActivityI.Presenter {

    override fun navigateScreens() {
        router.navigateTo(Screens.ScreenSearch)
    }
}
