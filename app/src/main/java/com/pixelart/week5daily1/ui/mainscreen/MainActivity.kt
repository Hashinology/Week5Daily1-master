package com.pixelart.week5daily1.ui.mainscreen

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.pixelart.week5daily1.adapter.RecyclerViewAdapter
import com.pixelart.week5daily1.base.BaseActivity
import com.pixelart.week5daily1.databinding.ActivityMainBinding
import com.pixelart.week5daily1.di.ApplicationModule
import com.pixelart.week5daily1.di.DaggerApplicationComponent
import com.pixelart.week5daily1.di.NetworkModule
import com.pixelart.week5daily1.model.GitHubUser
import com.pixelart.week5daily1.model.Repository
import com.pixelart.week5daily1.model.UserData
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<MainScreenContract.Presenter>(),
    MainScreenContract.View, RecyclerViewAdapter.OnItemClickedListener {
    val TAG = "MainActivity"

    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: RecyclerViewAdapter
    private var userList = ArrayList<UserData>()

    lateinit var repositories: List<Repository>

    var searchUser = ""
    var owner = ""

    @Inject
    lateinit var presenter: MainScreenContract.Presenter
    @Inject
    lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        repositories = ArrayList()
        layoutManager = LinearLayoutManager(this)

    }

    override fun onResume() {
        super.onResume()

        var owner = ""
        for (i in 0 until userList.size) {
            owner = userList[i].userName
        }
        presenter.loadRepos(owner)
    }

    override fun getViewPresenter(): MainScreenContract.Presenter = presenter

    override fun init() {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .networkModule(NetworkModule())
            .build().injectMainScreen(this)
    }

    override fun showUsers(users: GitHubUser) {
       //Initialize recyclerview adapter here
        userList.clear()
        val temp = presenter.loadRepoList()
        for (i in 0 until users.items.size) {
            userList.add(UserData(users.items[i].login, users.items[i].avatarUrl, "Repositories: ${temp.size}"))
            owner = userList[i].userName
            presenter.loadRepos(owner)
            Log.d(TAG, users.items[i].reposUrl)

        }

        adapter = RecyclerViewAdapter(userList, this)
        mainBinding.recyclerView.layoutManager = layoutManager
        mainBinding.recyclerView.adapter = adapter
    }

    override fun showRepos(repos: List<Repository>) {

    }

    fun onSearch(view: View){
        searchUser = mainBinding.etSearchInput.text.toString()

        presenter.loadUsers(searchUser)
        presenter.loadRepos(owner)

    }

    override fun onItemClicked(position: Int) {

    }

}
