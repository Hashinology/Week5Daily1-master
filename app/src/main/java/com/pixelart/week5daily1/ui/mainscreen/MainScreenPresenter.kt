package com.pixelart.week5daily1.ui.mainscreen

import com.pixelart.week5daily1.model.GitHubUser
import com.pixelart.week5daily1.model.Repository
import com.pixelart.week5daily1.remote.RemoteService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainScreenPresenter (private val view: MainScreenContract.View, private val remoteService: RemoteService):
    MainScreenContract.Presenter {

    private var subscription: Disposable? = null

    override fun onResume() {

    }

    override fun loadUsers(userName: String) {
        subscription = remoteService
            .getUsers(userName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe (

                {users -> view.showUsers(users)},
                {view.showError("Error data loading failed")}
            )

    }

    override fun loadRepos(user: String){
        subscription = remoteService
            .getUserRepos(user)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe (

                {repos -> getRepos(repos)},
                {view.showError("Error data loading failed")}
            )
    }

    private var repos:List<Repository> = ArrayList()
    private fun getRepos(repo:List<Repository>){
        repos = repo
    }

    override fun loadRepoList(): List<Repository> {
        return repos
    }

    override fun onDestroy() {
        subscription?.dispose()
    }

}