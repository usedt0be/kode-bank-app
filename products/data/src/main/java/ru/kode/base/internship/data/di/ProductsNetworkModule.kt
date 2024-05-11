package ru.kode.base.internship.data.di

import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.kode.base.core.di.AppScope
import ru.kode.base.core.di.SingleIn
import ru.kode.base.internship.data.network.ProductApi
import javax.inject.Named

@Module
@ContributesTo(AppScope::class)
object ProductsNetworkModule {
  @Provides
  @SingleIn(AppScope::class)
  fun provideProductApi(@Named("retrofitProduct") retrofit: Retrofit): ProductApi {
    return retrofit.create(ProductApi::class.java)
  }
}