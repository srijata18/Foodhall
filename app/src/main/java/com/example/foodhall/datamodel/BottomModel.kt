package com.example.foodhall.datamodel

data class BottomModel(
    val header: Header?,
    val main_content: MainContent?,
    val response: Response?
)
data class MainContent(
    val item: Item?
)
data class Data(
    val action_id: String?,
    val action_type: String?,
    val alert_desc: Any?,
    val height: String?,
    val id: String?,
    val image: String?,
    val is_featured: String?,
    val priority: Int?,
    val tab_name: String?,
    val type_of_list: Int?,
    val vendor_id: Int?,
    val vendor_sub_type: String?,
    val vendor_type_id: String?,
    val width: String?
)


data class Item(
    val `data`: List<Data?>?
)