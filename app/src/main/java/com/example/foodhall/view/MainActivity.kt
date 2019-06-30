package com.example.foodhall.view

import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.example.foodhall.R
import com.example.foodhall.adapters.BottomAdapter
import com.example.foodhall.adapters.CategoryAdapter
import com.example.foodhall.adapters.HeaderAdapter
import com.example.foodhall.contract.DashboardContract
import com.example.foodhall.datamodel.*
import com.example.foodhall.presenter.DashboardPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_bottom.*


class MainActivity : AppCompatActivity(), DashboardContract.View{

    var overlayDialog : Dialog ?= null
    var presenter : DashboardContract.Presenter ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        overlayDialog = Dialog(this, android.R.style.Theme_Panel)
        presenter = DashboardPresenter(this)
        presenter?.initData()
    }

    override fun showProgress(){
        progressDialog?.visible()
        overlayDialog?.apply {
            setCancelable(false)
            show()
        }
    }

    override fun isNetworkAvailable(): Boolean {
        val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    override fun hideProgress(){
        progressDialog?.gone()
        overlayDialog?.hide()
    }

    override fun setHeaderAdapter(vendorTypeDetail: ArrayList<VendorTypeDetail>){
        val headerAdapter = HeaderAdapter(this, vendorTypeDetail)
        view_pager_foodhall?.apply {
            adapter = headerAdapter
            startAutoScroll()
            isCycle = true
            isBorderAnimation = true
        }
    }

    override fun setCategoryAdapter(categoryList : ArrayList<CategoryModel>){
        rv_allmenu?.apply {
            layoutManager = GridLayoutManager(applicationContext, 3)
            adapter = CategoryAdapter(categoryList)
        }
    }

    override fun setBottomAdapter(bannerType : ArrayList<Data>){
        rv_more_events?.apply {
            layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
            adapter = BottomAdapter(bannerType)
        }
    }

}

