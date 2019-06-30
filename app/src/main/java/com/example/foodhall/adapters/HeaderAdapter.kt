package com.example.foodhall.adapters

import android.app.Activity
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.foodhall.R
import com.example.foodhall.datamodel.VendorTypeDetail

class HeaderAdapter(private val activity: Activity, private val imagesArray: ArrayList<VendorTypeDetail>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val inflater = activity.layoutInflater
        val viewItem = inflater.inflate(R.layout.row_item_header, container, false)
        val imageView = viewItem.findViewById(R.id.iv_headerName) as ImageView
        Glide.with(activity)
            .load(imagesArray[position].app_image)
            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
            .into(imageView)
        (container as ViewPager).addView(viewItem)
        return viewItem
    }

    override fun getCount(): Int {
        return imagesArray.size
    }


    override fun isViewFromObject(view: View, p1: Any): Boolean {
        return view === p1 as View
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (container as ViewPager).removeView(`object` as View)
    }
}