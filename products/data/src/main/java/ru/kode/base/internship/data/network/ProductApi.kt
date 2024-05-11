package ru.kode.base.internship.data.network

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Path
import ru.kode.base.internship.data.network.request.CardNameRequest
import ru.kode.base.internship.data.network.response.BankAccountResponse
import ru.kode.base.internship.data.network.response.CardByIdResponse
import ru.kode.base.internship.data.network.response.DepositByIdResponse
import ru.kode.base.internship.data.network.response.DepositResponse

interface ProductApi {

  @PUT("api/core/card/{cardId}")
  suspend fun renameCard(
      @Path("cardId") cardId: String,
      @Body request: CardNameRequest,
      @Header("Authorization") authToken: String = "Bearer 123"
  )

  @GET("api/core/account/list")
  suspend fun getBankAccounts(
    @Header("Prefer") code: String = "code=200, example=android",
    @Header("Authorization") authToken: String = "Bearer 123"
  ): BankAccountResponse

  @GET("api/core/deposit/list")
  suspend fun getDepositList(
    @Header("Prefer") code: String = "code=200, example=android",
    @Header("Authorization") authToken: String = "Bearer 123"
  ): DepositResponse

  @GET("api/core/deposit/{depositId}")
  suspend fun getDepositById(
    @Path("depositId") depositId: Int,
    @Header("Prefer") code: String = "code=200, example=android-$depositId",
    @Header("Authorization") authToken: String = "Bearer 123"
  ): DepositByIdResponse

  @GET("api/core/card/{cardId}")
  suspend fun getCardById(
    @Path("cardId") cardId: Int,
    @Header("Prefer") code: String = "code-200, example=android-$cardId",
    @Header("Authorization") authToken: String = "Bearer 123"
  ): CardByIdResponse

}