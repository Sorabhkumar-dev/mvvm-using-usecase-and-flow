package com.truely.truelymart.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.truely.truelymart.R
import com.truely.truelymart.data.model.ProductX
import com.truely.truelymart.databinding.CartProductLayoutBinding
import com.truely.truelymart.ui.interfaces.OnItemClickedListener
import javax.inject.Inject

class CartProductAdapter (val products:List<ProductX> = mutableListOf()):RecyclerView.Adapter<CartProductAdapter.CartProductViewHolder>() {

    var onItemClickedListener:OnItemClickedListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        CartProductViewHolder(CartProductLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: CartProductViewHolder, position: Int) {
        holder.binding.txvProductId.apply {
            text = context.getString(R.string.product_id,products[position].productId)
        }
        holder.binding.txvQuantity.apply {
            text = context.getString(R.string.quantity,products[position].quantity)
        }
        holder.binding.root.setOnClickListener { onItemClickedListener?.onItemClicked(products[position].productId.toString()) }

    }

    override fun getItemCount() = products.size

    inner class CartProductViewHolder(val binding:CartProductLayoutBinding):RecyclerView.ViewHolder(binding.root)
}