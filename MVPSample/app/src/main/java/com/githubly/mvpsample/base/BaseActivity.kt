package com.githubly.mvpsample.base

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.githubly.mvpsample.IProgressDialog

/**
 * 类名：BaseActivity
 * 作者：Yun.Lei
 * 功能：
 * 创建日期：2018-07-13 11:31
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
abstract class BaseActivity : AppCompatActivity(), IProgressDialog {

    override val mProgressDialog: ProgressDialog by lazy { ProgressDialog(this) }

    abstract val contentViewID: Int

    open fun initParameter() {

    }

    open fun setStatusBar() {

    }

    abstract fun initView()

    abstract fun initData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentViewID)
        initParameter()
        initView()
        setStatusBar()
        initData()
    }
}