package com.truely.truelymart.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.truely.truelymart.R
import com.truely.truelymart.data.model.User
import com.truely.truelymart.databinding.UserLayoutBinding
import javax.inject.Inject

class UserAdapter @Inject constructor():RecyclerView.Adapter<UserAdapter.UserViewHolder>(){
    private val users :MutableList<User> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        UserViewHolder(UserLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        users[position].apply {
            holder.binding.txvUserAddress.apply {
                text = context.getString(R.string.user_address,address.number,address.street,address.city,address.zipcode)
            }
            holder.binding.txvUserEmail.apply {
                text = context.getString(R.string.user_email,email)
            }
            holder.binding.txvUserPhone.apply {
                text = context.getString(R.string.user_phone,phone)
            }
            holder.binding.txvUserName.apply {
                text = context.getString(R.string.user_name,name.firstname,name.lastname)
            }
        }
    }

    override fun getItemCount() = users.size

    fun updateUsers(newUsers:List<User>?){
        if (newUsers != null) {
            users.clear()
            users.addAll(newUsers)
            notifyDataSetChanged()
        }
    }
    inner class UserViewHolder(val binding:UserLayoutBinding):RecyclerView.ViewHolder(binding.root)
}