package com.truely.truelymart.data.retrofit

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

class FlowCallAdapter<R>(private val responseType:Type) :CallAdapter<R,Flow<TruelyResponse<R>>>{
    override fun responseType() = responseType

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun adapt(call: Call<R>): Flow<TruelyResponse<R>> {
       return flow {
           emit(
               suspendCancellableCoroutine { continuation ->
                   call.enqueue(object : Callback<R> {
                       override fun onFailure(call: Call<R>, t: Throwable) {
                           continuation.resume(TruelyResponse(t),null)
                       }

                       override fun onResponse(call: Call<R>, response: Response<R>) {
                           try {
                               continuation.resume(TruelyResponse(response),null)
                           } catch (e: Exception) {
                               continuation.resume(TruelyResponse(e),null)
                           }
                       }
                   })
                   continuation.invokeOnCancellation { call.cancel() }
               }
           )
       }
    }

}