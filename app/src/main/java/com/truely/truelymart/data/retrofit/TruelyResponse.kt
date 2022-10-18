package com.truely.truelymart.data.retrofit

import com.truely.truelymart.utils.Constant
import retrofit2.Response

class TruelyResponse<T> {
    val code: Int
    val body: T?
    val message: String?
    val isSuccessfull: Boolean

    constructor(response: Response<T>){
        code = response.code()
        body = response.body()
        isSuccessfull = response.isSuccessful
        message = response.message()
    }

    constructor(error:Throwable){
        code = 500
        body = null
        message =error.message ?: Constant.ERROR_MESSAGE
        isSuccessfull = false
    }
}