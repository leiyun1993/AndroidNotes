package com.githubly.mvpsample

import android.widget.Toast
import com.githubly.mvpsample.base.BaseMVPActivity
import com.githubly.mvpsample.contract.ISampleContract
import com.githubly.mvpsample.model.User
import com.githubly.mvpsample.presenter.SamplePresenter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 类名：MainActivity
 * 作者：Yun.Lei
 * 功能：
 * 创建日期：2018-07-13 14:37
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class MainActivity : BaseMVPActivity<SamplePresenter>(), ISampleContract.View {

    override val contentViewID: Int
        get() = R.layout.activity_main

    /**
     * 初始化Presenter
     */
    override fun initPresenter(): SamplePresenter? = SamplePresenter(this)


    override fun initView() {
        btnUserLogin.setOnClickListener {
            if (getUserName().isNotEmpty() && getPassword().isNotEmpty()) {
                //loading
                showLoadingDialog()
                mPresenter?.userLogin()
            } else {
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun initData() {

    }

    /**
     * 获取数据接口
     */
    override fun getUserName(): String = edtUserName.text.toString()

    /**
     * 获取数据接口
     */
    override fun getPassword(): String = edtPassword.text.toString()

    /**
     * 绑定数据
     */
    override fun onLoginSuccess(user: User) {
        //dismissLoading
        dismissLoadingDialog()
        user.apply {
            tvUserInfo.text = "登录成功：$name,$age,$sex"
        }
    }

    override fun onLoginFailed(msg: String) {
        //dismissLoading
    }
}
