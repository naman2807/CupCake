package com.example.cupcake.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class OrderViewModel : ViewModel() {
    private val _quantity = MutableLiveData<Int>(0)
    val quantity : LiveData<Int> get() = _quantity

    private val _flavor = MutableLiveData<String>("")
    val flavor : LiveData<String> get() = _flavor

    private val _date = MutableLiveData<String>("")
    val date : LiveData<String> get() = _date

    private val _price = MutableLiveData<Double>(0.0)
    val price : LiveData<Double> get() = _price

    fun setQuantity(numberCupCakes : Int){
        _quantity.value = numberCupCakes
    }

    fun setFlavor(flavor : String){
        _flavor.value = flavor
    }

    fun setDate(pickDate : String){
        _date.value = pickDate
    }

    fun isFlavorSet() : Boolean{
        return _flavor.value.isNullOrEmpty()
    }

    private fun getPickOptions():List<String>{
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E, MMM d", Locale.getDefault())
        val calender = Calendar.getInstance()
        repeat(4){
            options.add(formatter.format(calender.time))
            calender.add(Calendar.DATE, 1)
        }
    }
}