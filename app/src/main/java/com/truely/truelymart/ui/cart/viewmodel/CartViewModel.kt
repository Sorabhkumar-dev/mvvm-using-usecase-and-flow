package com.truely.truelymart.ui.cart.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.truely.truelymart.data.model.Cart
import com.truely.truelymart.data.retrofit.Result
import com.truely.truelymart.domain.usecases.GetCartItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val getCartItemUseCase: GetCartItemUseCase) : ViewModel() {
    private val _cartItemFlow: MutableStateFlow<Result<List<Cart>>> =
        MutableStateFlow(Result.Loading())
    val cartItemFlow: StateFlow<Result<List<Cart>>> = _cartItemFlow

    init {
        getCartItem()
    }
    fun getCartItem(){
        viewModelScope.launch {
            getCartItemUseCase().collect {
                when(it){
                    is Result.Error -> _cartItemFlow.emit(Result.Error(it.code,it.message))
                    is Result.Loading -> _cartItemFlow.emit(Result.Loading())
                    is Result.Success -> _cartItemFlow.emit(Result.Success(it.body,it.code,it.message))
                }
            }
        }
    }
}