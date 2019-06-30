package com.example.foodhall.contract

import com.example.foodhall.datamodel.CategoryModel
import com.example.foodhall.datamodel.Data
import com.example.foodhall.datamodel.VendorTypeDetail

interface DashboardContract {
    interface View{
        fun isNetworkAvailable() : Boolean
        fun showProgress()
        fun hideProgress()
        fun setHeaderAdapter( vendorTypeDetail: ArrayList<VendorTypeDetail>)
        fun setCategoryAdapter(categoryList : ArrayList<CategoryModel>)
        fun setBottomAdapter( bannerType : ArrayList<Data>)

    }
    interface Presenter{
        fun initData()
    }
}