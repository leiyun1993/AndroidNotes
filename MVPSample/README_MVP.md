
### 1、MVP说明

MVP跟MVC很相像，因为MVP也是三层，唯一的差别是Model和View之间不进行通讯，都是通过Presenter完成。在android中由于activity（god object）的存在，Controller和View很难做到完全解耦。但在MVP中就可以很好的解决这个问题

看下MVP的设计图：

 ![image](https://github.com/leiyun1993/AndroidNotes/raw/master/image/mvp.png)

 一般情况下就这两种

 ### 2、MVP Sample

 项目结构代码

 ![image](https://github.com/leiyun1993/AndroidNotes/raw/master/image/mvp_sample.png)

 BasePresenter:业务逻辑放在Presenter中实现，弱化Model
```kotlin
abstract class BasePresenter<T : IView>(view: T) {
 protected var mView: T? = view

 fun onDestroy() {
     mView = null
 }

}
```
 Contract:参考Google官方的写法，这个类似合同类的接口把P和V的所有方法全部写在一起，看起来代码格外清楚，所有的功能清清楚楚，其实只要看这个Contract差不多所有的功能就已经有所了解了
```kotlin
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
```
 Presenter的具体实现,具体是Presenter和View的交互，从View获取数据和给View传递数据
```kotlin
class SamplePresenter(view: ISampleContract.View) :
        BasePresenter<ISampleContract.View>(view), ISampleContract.Presenter {

    override fun userLogin() {
        thread {        //模拟网络请求和其他逻辑处理
            sleep(2000)
            Handler(Looper.getMainLooper()).post {
                mView?.onLoginSuccess(User("test", "18", "男"))
            }
        }
    }
}
```
 此处针对一个View对应一个Presenter则可以将BaseActivity（fragment同理）做如下处理，便于管理初始化和解绑
```kotlin
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
```
 最后是具体的View实现
```kotlin
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
```
这里的MainActivity实现了SampleContract.View只是作为View存在的，mPresenter为Model和View之间交互的桥梁。

### 参考文档

[MVC、MVP、MVVM，我到底该怎么选？](https://juejin.im/post/5b3a3a44f265da630e27a7e6)