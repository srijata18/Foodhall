package com.example.foodhall.presenter

import android.content.Context
import android.net.ConnectivityManager
import com.example.foodhall.contract.DashboardContract
import android.net.NetworkInfo
import android.support.v4.content.ContextCompat.getSystemService
import android.util.Log
import com.example.foodhall.Constants
import com.example.foodhall.datamodel.*
import com.example.foodhall.view.GetDetails
import com.example.foodhall.view.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DashboardPresenter(val view : DashboardContract.View) : DashboardContract.Presenter{

    val service = RetrofitClientInstance().getRetrofitInstance()!!.create(GetDetails::class.java)

    override fun initData() {
        if(view.isNetworkAvailable()){
            view.showProgress()
            getVendorDetails()
            getBottomLayoutDetails()
        }
    }

    private fun getVendorDetails(){
        val call = service.getAllCategories(prepareHeaders())
        call.enqueue(object : Callback<MainContentDetails> {
            override fun onResponse(call: Call<MainContentDetails>, response: Response<MainContentDetails>) {
                val vendorTypeDetail = ArrayList<VendorTypeDetail>()
                val categoryList = ArrayList<CategoryModel>()
                val resVendorList = response.body()?.main_content?.data?.get(1)?.vendor_type_details
                resVendorList?.let { vendorList ->
                    for (item in 0 until vendorList.size)
                        vendorList[item]?.let{
                                vendorDetails ->
                            vendorTypeDetail.add(vendorDetails)}
                }
                val resCategoryList = response.body()?.main_content?.data?.get(0)?.category
                resCategoryList?.let { listOfCategory ->
                    for (item in 0 until listOfCategory.size)
                        listOfCategory[item]?.let{
                                categoryDetails ->
                            categoryList.add(categoryDetails)}
                }
                view.setHeaderAdapter(vendorTypeDetail)
                view.setCategoryAdapter(categoryList)
                Log.i("INFO::","Success : ${response.body()}")
                view.hideProgress()
            }

            override fun onFailure(call: Call<MainContentDetails/*List<CategoryModel>*/>, t: Throwable) {
                view.hideProgress()
                Log.i("INFO::","Error : ${t.stackTrace}")
            }
        })
    }

    private fun getBottomLayoutDetails(){
        val getBannerList  = service.getBannerList(prepareHeaders())
        getBannerList.enqueue(object : Callback<BottomModel> {
            override fun onResponse(call: Call<BottomModel>, response: Response<BottomModel>) {
                val bannerType = ArrayList<Data>()
                val resBannerList = response.body()?.main_content?.item?.data
                resBannerList?.let { bannerList ->
                    for (item in 0 until bannerList.size)
                        bannerList[item]?.let{
                                bannerDetails ->
                            bannerType.add(bannerDetails)}
                }
                view.setBottomAdapter(bannerType)
                Log.i("INFO::","Success : ${response.body()}")
                view.hideProgress()

            }
            override fun onFailure(call: Call<BottomModel>, t: Throwable) {
                view.hideProgress()
                Log.i("INFO::","Error : ${t.stackTrace}")
            }
        })

    }
    private fun  prepareHeaders():HashMap<String, String>{
        val headerMap = HashMap<String, String>()
        headerMap[Constants.version] = Constants.verson_code
        headerMap[Constants.website] = Constants.websiteName
        headerMap[Constants.company] = Constants.companyName
        headerMap[Constants.termOfService] = Constants.termOfServiceName
        headerMap[Constants.generator] = Constants.companyName
        headerMap[Constants.policycode] = Constants.companyName
        headerMap[Constants.createdTime] = Constants.createdTimeDummy
        return headerMap
    }
}