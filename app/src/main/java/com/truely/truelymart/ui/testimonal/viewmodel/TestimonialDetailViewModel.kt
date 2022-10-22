package com.truely.truelymart.ui.testimonal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.truely.truelymart.data.model.User
import com.truely.truelymart.data.retrofit.Result
import com.truely.truelymart.domain.usecases.GetTestimonialDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestimonialDetailViewModel @Inject constructor(private val getTestimonialDetailUseCase: GetTestimonialDetailUseCase):ViewModel() {
    private val _testimonialFlow:MutableStateFlow<Result<User>> =  MutableStateFlow(Result.Loading())
    val testimonialFlow:StateFlow<Result<User>> = _testimonialFlow

    fun getTestimonialDetail(testimonialId:String){
        viewModelScope.launch {
            getTestimonialDetailUseCase(testimonialId).collect{
                when(it){
                    is Result.Error -> _testimonialFlow.emit(Result.Error(it.code,it.message))
                    is Result.Loading -> _testimonialFlow.emit(Result.Loading())
                    is Result.Success -> _testimonialFlow.emit(Result.Success(it.body,it.code,it.message))
                }
            }
        }
    }
}