package com.example.cupcake.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrderViewModel : ViewModel() {
    private val _quantity = MutableLiveData<Int>(0)
    private val _flavor = MutableLiveData<String>("")
    private val _date = MutableLiveData<String>("")
}