package com.truely.truelymart.ui.testimonal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.truely.truelymart.data.model.User
import com.truely.truelymart.data.retrofit.Result
import com.truely.truelymart.domain.usecases.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestimonialsViewModel @Inject constructor(private val getUsersUseCase: GetUsersUseCase):ViewModel() {
    private val _usersFlow:MutableStateFlow<Result<List<User>>> = MutableStateFlow(Result.Loading())
    val usersFlow:StateFlow<Result<List<User>>>  = _usersFlow
    init {
        getUsers()
    }
    fun getUsers(){
        viewModelScope.launch {
            getUsersUseCase().collect{
                when(it){
                    is Result.Error -> _usersFlow.emit(Result.Error(it.code,it.message))
                    is Result.Loading -> _usersFlow.emit(Result.Loading())
                    is Result.Success -> _usersFlow.emit(Result.Success(it.body,it.code,it.message))
                }
            }
        }
    }
}