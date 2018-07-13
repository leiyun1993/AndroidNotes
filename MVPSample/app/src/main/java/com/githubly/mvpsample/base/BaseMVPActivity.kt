package com.githubly.mvpsample.base

/**
 * 类名：BaseMVPActivity
 * 作者：Yun.Lei
 * 功能：
 * 创建日期：2018-07-13 10:46
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
abstract class BaseMVPActivity<out P : BasePresenter<*>> : BaseActivity() {

    protected val mPresenter: P? by lazy { initPresenter() }

    abstract fun initPresenter(): P?

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDestroy()
    }

}