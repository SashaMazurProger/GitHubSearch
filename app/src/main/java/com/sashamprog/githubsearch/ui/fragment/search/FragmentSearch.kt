package com.sashamprog.githubsearch.ui.fragment.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.sashamprog.githubsearch.R
import com.sashamprog.githubsearch.base.BaseFragment
import com.sashamprog.githubsearch.data.provider.github.entity.SearchRepositoryResult
import com.sashamprog.githubsearch.ui.fragment.search.adapter.AdapterRepository
import kotlinx.android.synthetic.main.search_fragment.*
import org.koin.android.ext.android.inject

class FragmentSearch : BaseFragment(),
    SearchI.View {

    override val presenter: SearchI.Presenter by inject()
    private var adapterRepository: AdapterRepository? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(
            R.layout.search_fragment,
            container,
            false
        )

        presenter.onAttach(this)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.search(query ?: "")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText?.isEmpty() == true) {
                    presenter.search("")
                }
                return true
            }

        })
    }

    override fun showSearchResults(items: List<SearchRepositoryResult.Item>) {
        if (adapterRepository == null) {
            adapterRepository = AdapterRepository { clickItem -> showMessage(clickItem.fullName) }
            recycler_view.layoutManager = LinearLayoutManager(context)
            recycler_view.adapter = adapterRepository
        }
        adapterRepository?.setItems(items)
    }

    override fun showProgress(visible: Boolean) {
        progress_bar.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun showList(visible: Boolean) {
        recycler_view.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        presenter.onDetach()
        adapterRepository = null
        super.onDestroyView()
    }

    companion object {
        fun getInstance() = FragmentSearch()
    }
}