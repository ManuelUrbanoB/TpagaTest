package development.tpaga.co.ui

import android.app.Application
import development.tpaga.co.ui.modules.main.data.AppDBPayment
import development.tpaga.co.ui.modules.main.utils.moduleMain
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppDBPayment.init(this)
        startKoin {
            modules(listOf(moduleMain))
        }
    }
}