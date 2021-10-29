package com.harshitjain.test.viewmodels

import androidx.lifecycle.ViewModel
import com.harshitjain.test.models.Customer

class CustomerViewModel: ViewModel() {
    var CustomerList : MutableList<Customer> = mutableListOf<Customer>()

}