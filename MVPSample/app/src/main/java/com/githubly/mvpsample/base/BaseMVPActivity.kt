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

    //kotlin 懒加载，在第一次使用Presenter时初始化，这种设计师针对一个View只针对一个Presenter。
    //多个Presenter的情况此处不应该使用泛型
    protected val mPresenter: P? by lazy { initPresenter() }

    abstract fun initPresenter(): P?

    override fun onDestroy() {
        super.onDestroy()
        //view和presenter解绑
        mPresenter?.onDestroy()
    }

}