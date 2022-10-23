package com.truely.truelymart.data.model

data class Cart(
    val __v: Int,
    val date: String,
    val id: Int,
    val products: List<ProductX>,
    val userId: Int
)