package com.technical_assessment.starterappmvvm.base


import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.technical_assessment.starterappmvvm.BaseApp
import com.technical_assessment.starterappmvvm.di.component.ApplicationComponent
import com.technical_assessment.starterappmvvm.ui.core.dialog.DefaultLoadingDialog
import com.google.android.material.snackbar.Snackbar
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

typealias InflateActivity<T> = (LayoutInflater) -> T

abstract class BaseActivity<VB:ViewBinding>(
    var inflate:InflateActivity<VB>
):AppCompatActivity() {

    private lateinit var _appComponent:ApplicationComponent
    val appComponent get() = _appComponent


    private var _binding:VB ?= null
    val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        initComponent()
        inject()
        super.onCreate(savedInstanceState)
        setLayout()
        internalSetup()
        initSetup()
    }

    open fun initComponent(){
        _appComponent = (applicationContext as BaseApp).applicationComponent
    }

    open fun internalSetup(){}

    abstract fun initSetup()

    private fun setLayout(){
        _binding = inflate.invoke(layoutInflater)
        setContentView(binding?.root)
    }

    abstract fun inject()

    fun addSubscription(disposable: Disposable) = CompositeDisposable().add(disposable)

    fun removeStatusBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
    }

    private var loadingDialog:DefaultLoadingDialog ?= null
    fun showLoadingDialog(){
        if (loadingDialog == null){
            loadingDialog = DefaultLoadingDialog()
            loadingDialog?.show(supportFragmentManager, DefaultLoadingDialog::class.java.simpleName)
        }
    }

    fun dismissDialog(){
        if (loadingDialog != null){
            loadingDialog?.dismiss()
            loadingDialog = null
        }
    }

    fun showToast(message:String, long:Int?=null){
        Toast.makeText(this, message, long?:Toast.LENGTH_SHORT).show()
    }

    fun showSnackBar(view:View, message:String, long:Int?=null){
        Snackbar.make(view, message, long?:Snackbar.LENGTH_SHORT).show()
    }
}