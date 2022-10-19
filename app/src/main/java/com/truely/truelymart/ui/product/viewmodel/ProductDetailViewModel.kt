package com.truely.truelymart.ui.product.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.truely.truelymart.data.model.ProductInfo
import com.truely.truelymart.data.retrofit.Result
import com.truely.truelymart.domain.usecases.GetProductInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(private val getProductInfoUseCase: GetProductInfoUseCase): ViewModel() {
    private val _productInfFlow: MutableStateFlow<Result<ProductInfo>> =
        MutableStateFlow(Result.Loading())
    val productInfoFlow :StateFlow<Result<ProductInfo>> = _productInfFlow

    fun getProductInfo(productId:String) {
        viewModelScope.launch {
            getProductInfoUseCase(productId).collect {
                when (it) {
                    is Result.Error -> _productInfFlow.emit(it)
                    is Result.Loading ->_productInfFlow.emit(it)
                    is Result.Success -> _productInfFlow.emit(it)
                }
            }
        }
    }
}