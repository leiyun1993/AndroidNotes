package com.githubly.mvpsample

import android.app.ProgressDialog

/**
 * 类名：IProgressDialog
 * 作者：Yun.Lei
 * 功能：
 * 创建日期：2018-07-13 15:17
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

interface IProgressDialog {
    val mProgressDialog: ProgressDialog
}

inline fun IProgressDialog.showLoadingDialog(msg: String = "加载中...") {
    if (!mProgressDialog.isShowing) {
        mProgressDialog.setCancelable(false)
        mProgressDialog.setMessage(msg)
        mProgressDialog.show()
    }
}

inline fun IProgressDialog.dismissLoadingDialog() {
    if (mProgressDialog.isShowing) {
        mProgressDialog.dismiss()
    }
}