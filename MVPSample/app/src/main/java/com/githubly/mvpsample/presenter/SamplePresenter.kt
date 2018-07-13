package com.githubly.mvpsample.presenter

import android.os.Handler
import android.os.Looper
import com.githubly.mvpsample.base.BasePresenter
import com.githubly.mvpsample.contract.ISampleContract
import com.githubly.mvpsample.model.User
import java.lang.Thread.sleep
import kotlin.concurrent.thread

/**
 * 类名：SamplePresenter
 * 作者：Yun.Lei
 * 功能：
 * 创建日期：2018-07-13 14:37
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class SamplePresenter(view: ISampleContract.View) :
        BasePresenter<ISampleContract.View>(view), ISampleContract.Presenter {

    override fun userLogin() {
        thread {
            sleep(2000)
            Handler(Looper.getMainLooper()).post {
                mView?.onLoginSuccess(User("test", "18", "男"))
            }
        }
    }
}