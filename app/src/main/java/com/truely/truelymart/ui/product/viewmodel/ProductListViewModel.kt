package com.truely.truelymart.ui.product.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.truely.truelymart.data.model.Product
import com.truely.truelymart.data.retrofit.Result
import com.truely.truelymart.domain.usecases.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(private val getProductsUseCase: GetProductsUseCase):ViewModel() {
    private var _productFlow: MutableStateFlow<Result<List<Product>>> = MutableStateFlow(Result.Loading())
    var productFlow :StateFlow<Result<List<Product>>> = _productFlow
    init {
        getProducts()
    }
   fun getProducts(){
        viewModelScope.launch {
           getProductsUseCase().collect{
               when(it){
                   is Result.Error ->_productFlow.emit(it)
                   is Result.Loading -> _productFlow.emit(it)
                   is Result.Success -> _productFlow.emit(it)
               }
           }
        }
    }
}