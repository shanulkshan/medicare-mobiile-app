package com.example.practiceformadproject

import android.content.ClipDescription

data class CartModel (
    var cartId: String? = null,
    var name: String? = null,
    var description: String? =null,
    var manufactureName: String? = null,
    var quantity: String? = null,
    var price: String? = null
)