package com.githubly.mvpsample.contract

import com.githubly.mvpsample.base.IView
import com.githubly.mvpsample.model.User

/**
 * 类名：ISampleContract
 * 作者：Yun.Lei
 * 功能：
 * 创建日期：2018-07-13 14:35
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
interface ISampleContract {

    interface Presenter {
        fun userLogin()
    }

    interface View : IView {
        fun getUserName(): String
        fun getPassword(): String
        fun onLoginSuccess(user: User)
        fun onLoginFailed(msg: String)
    }
}