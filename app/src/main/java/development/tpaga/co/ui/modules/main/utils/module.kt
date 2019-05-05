package development.tpaga.co.ui.modules.main.utils

import development.tpaga.co.ui.modules.main.data.AppDBPayment
import development.tpaga.co.ui.modules.main.data.PaymentResponseDao
import development.tpaga.co.ui.modules.main.net.ApiClient
import development.tpaga.co.ui.modules.main.ui.listPaymentResponse.ListPaymentResponseViewModel
import development.tpaga.co.ui.modules.main.ui.main.MainActivityViewModel
import development.tpaga.co.ui.modules.main.ui.productLink.ProductLinkViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moduleMain = module {
    single{ApiClient.intercaceStation()}
    single{AppDBPayment.databasePayment.paymentResponseDao()}
    viewModel { MainActivityViewModel(get(), get()) }
    viewModel { ProductLinkViewModel(get(), get()) }
    viewModel { ListPaymentResponseViewModel(get())}
}