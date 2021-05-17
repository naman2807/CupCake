package com.example.cupcake.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

private const val PRICE_PER_CUP_CAKE = 2.00
private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00

class OrderViewModel : ViewModel() {
    private val _quantity = MutableLiveData<Int>()
    val quantity : LiveData<Int> get() = _quantity

    private val _flavor = MutableLiveData<String>()
    val flavor : LiveData<String> get() = _flavor

    private val _date = MutableLiveData<String>()
    val date : LiveData<String> get() = _date

    private val _price = MutableLiveData<Double>()
    val price : LiveData<String> = Transformations.map(_price){
        NumberFormat.getCurrencyInstance().format(_price)
    }

    val dateOptions = getPickOptions()

    init {
        resetOrder()
    }

    fun setQuantity(numberCupCakes : Int){
        _quantity.value = numberCupCakes
        updatePrice()
    }

    fun setFlavor(flavor : String){
        _flavor.value = flavor
    }

    fun setDate(pickDate : String){
        _date.value = pickDate
        updatePrice()
    }

    fun isFlavorSet() : Boolean{
        return _flavor.value.isNullOrEmpty()
    }

    // since the value of quantity.value could be null, use an elvis operator (?:) .
    // The elvis operator (?:) means that if the expression on the left is not null, then use it.
    // Otherwise if the expression on the left is null, then use the expression to the right of the elvis operator
    // (which is 0 in this case).
    private fun updatePrice(){
        _price.value = (quantity.value ?: 0) *(PRICE_PER_CUP_CAKE)
        if(_date.value.equals(dateOptions[0])){
            val finalPrice = _price.value!! + PRICE_FOR_SAME_DAY_PICKUP
            _price.value = finalPrice
        }

    }

    private fun getPickOptions():List<String>{
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E, MMM d", Locale.getDefault())
        val calender = Calendar.getInstance()
        repeat(4){
            options.add(formatter.format(calender.time))
            calender.add(Calendar.DATE, 1)
        }
        return options
    }

    private fun resetOrder(){
        _quantity.value = 0
        _flavor.value = ""
        _date.value = dateOptions[0]
        _price.value = 0.0
    }
}