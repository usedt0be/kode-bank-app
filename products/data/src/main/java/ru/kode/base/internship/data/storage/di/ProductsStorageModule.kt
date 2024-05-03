package ru.kode.base.internship.data.storage.di

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import app.cash.sqldelight.logs.LogSqliteDriver
import com.squareup.anvil.annotations.ContributesTo
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.kode.base.core.annotations.ApplicationContext
import ru.kode.base.core.di.AppScope
import ru.kode.base.core.di.SingleIn
import ru.kode.base.internship.data.network.ProductApi
import ru.kode.base.internship.data.repository.CardsRepositoryImpl
import ru.kode.base.internship.data.repository.DepositsRepositoryImpl
import ru.kode.base.internship.data.storage.dataSource.BankAccountDataSource
import ru.kode.base.internship.data.storage.dataSource.BankAccountDataSourceImpl
import ru.kode.base.internship.data.storage.dataSource.CardDataSource
import ru.kode.base.internship.data.storage.dataSource.CardDataSourceImpl
import ru.kode.base.internship.data.storage.dataSource.DepositDataSource
import ru.kode.base.internship.data.storage.dataSource.DepositDataSourceImpl
import ru.kode.base.internship.domain.repository.BankAccountRepository
import ru.kode.base.internship.domain.repository.CardRepository
import ru.kode.base.internship.domain.repository.DepositRepository
import ru.kode.base.internship.products.data.ProductsDB
import timber.log.Timber
import javax.inject.Named

@Module
@ContributesTo(AppScope::class)
object ProductsStorageModule {
  @Provides
  @SingleIn(AppScope::class)
  @Named("products")
  fun provideProductSqlDriver(
    @ApplicationContext
    context: Context,
  ): SqlDriver {
    return AndroidSqliteDriver(schema = ProductsDB.Schema, context, name = "products.db").let { driver ->
      if (ENABLE_LOGGING) {
        LogSqliteDriver(driver) { log ->
          Timber.e(log)
        }
      } else {
        driver
      }
    }
  }

  @Provides
  @SingleIn(AppScope::class)
  fun provideProductsDB(@Named("products") driver: SqlDriver):ProductsDB{
    return ProductsDB(driver)
  }

  @Provides
  @SingleIn(AppScope::class)
  fun provideCardRepository( cardDataSource: CardDataSource, bankAccountDataSource: BankAccountDataSource): CardRepository {
    return CardsRepositoryImpl(cardDataSource, bankAccountDataSource )
  }

}


private const val ENABLE_LOGGING = false