package com.truely.truelymart.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.truely.truelymart.R
import com.truely.truelymart.data.model.Product
import com.truely.truelymart.databinding.ProductsLayoutBinding
import javax.inject.Inject

class ProductAdapter @Inject constructor():RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    private val products:MutableList<Product> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ProductViewHolder(ProductsLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.binding.imgProduct.apply {
            Glide.with(context)
                .load(products[position].image)
                .error(R.drawable.ic_launcher)
                .into(this)
        }
        holder.binding.txvProductTitle.text = products[position].title
        holder.binding.txvProductDescription.text = products[position].description
        holder.binding.txvProductPrice.text = products[position].price.toString()
        holder.binding.txvProductCategory.text = products[position].category
        holder.binding.txvProductRating.apply {
            text = context.getString(R.string.rating,products[position].rating.rate,products[position].rating.count)
        }
    }

    override fun getItemCount() = products.size

    fun updateProducts(newProducts:List<Product>){
        products.clear()
        products.addAll(newProducts)
        notifyDataSetChanged()
    }
    inner class ProductViewHolder(val binding:ProductsLayoutBinding):RecyclerView.ViewHolder(binding.root)

}