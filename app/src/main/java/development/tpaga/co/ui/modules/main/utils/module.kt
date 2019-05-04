package development.tpaga.co.ui.modules.main.utils

import development.tpaga.co.ui.modules.main.net.ApiClient
import development.tpaga.co.ui.modules.main.ui.MainActivityViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moduleMain = module {
    single{ApiClient.intercaceStation()}
    viewModel { MainActivityViewModel(get()) }
}