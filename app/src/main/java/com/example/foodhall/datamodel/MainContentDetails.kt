package com.example.foodhall.datamodel

data class MainContentDetails(
    val header: Header?,
    val main_content: MainContentView?,
    val response: Response?
)

data class MainContentView(
    val `data`: List<DataDashboard?>?
)
data class DataDashboard(
    val category: List<CategoryModel?>?,
    val vendor_type_details: List<VendorTypeDetail?>?
)


data class CategoryModel (var name: String ?= "",
                          var count : Int ?= 0,
                          var image : String ?= "",
                          var id : String ?= "",
                          var vendor_type_id : Int ?= 0,
                          var vendor_id : Int ?= 0,
                          var deeplink : Int ?= 0)
