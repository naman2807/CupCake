package com.example.cupcake.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrderViewModel : ViewModel() {
    private val _quantity = MutableLiveData<Int>(0)
}