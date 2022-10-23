package com.truely.truelymart.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.truely.truelymart.R
import com.truely.truelymart.data.model.Cart
import com.truely.truelymart.databinding.CartLayoutBinding
import com.truely.truelymart.ui.interfaces.OnItemClickedListener
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

class CartAdapter @Inject constructor():RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private val cartItem :MutableList<Cart> = mutableListOf()
    var onItemClickedListener:OnItemClickedListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CartViewHolder(CartLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.binding.txvUserId.apply {
            text = context.getString(R.string.user_id,cartItem[position].userId)
        }

        holder.binding.txvDate.text = getData(cartItem[position].date)
        holder.binding.rvCart.adapter = CartProductAdapter(cartItem[position].products)
        (holder.binding.rvCart.adapter as CartProductAdapter).onItemClickedListener = onItemClickedListener
    }

    override fun getItemCount() = cartItem.size

    fun updateCart(newCartItem:List<Cart>?){
        newCartItem?.let {
            cartItem.clear()
            cartItem.addAll(it)
            notifyDataSetChanged()
        }
    }

    private fun getData(newDate:String):String{
        val inputFormatter: DateTimeFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
        val outputFormatter: DateTimeFormatter =
            DateTimeFormatter.ofPattern("dd MMM yyy", Locale.ENGLISH)
        val date: LocalDate = LocalDate.parse(newDate, inputFormatter)
        return outputFormatter.format(date)
    }
    inner class CartViewHolder(val binding:CartLayoutBinding):RecyclerView.ViewHolder(binding.root)
}