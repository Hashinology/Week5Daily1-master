package com.pixelart.week5daily1.ui.mainscreen

import com.pixelart.week5daily1.base.BasePresenter
import com.pixelart.week5daily1.base.BaseView
import com.pixelart.week5daily1.model.GitHubUser
import com.pixelart.week5daily1.model.Repository

interface MainScreenContract{
    interface View: BaseView{
        fun showUsers(users: GitHubUser)
        fun showRepos(repos: List<Repository>)
    }

    interface Presenter: BasePresenter{
        fun loadUsers(userName: String)
        fun loadRepos(user: String)
        fun loadRepoList(): List<Repository>
    }
}