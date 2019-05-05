package development.tpaga.co.ui.modules.main.utils

import development.tpaga.co.ui.modules.main.net.ApiClient
import development.tpaga.co.ui.modules.main.ui.main.MainActivityViewModel
import development.tpaga.co.ui.modules.main.ui.productLink.ProductLinkViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moduleMain = module {
    single{ApiClient.intercaceStation()}
    viewModel { MainActivityViewModel(get()) }
    viewModel { ProductLinkViewModel(get()) }
}