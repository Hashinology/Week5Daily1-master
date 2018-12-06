package com.pixelart.week5daily1.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity<T: BasePresenter>: AppCompatActivity(), BaseView{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun showMessage(message: String) {

    }

    override fun showError(error: String) {

    }

    abstract fun getViewPresenter():T
    abstract fun init()
}