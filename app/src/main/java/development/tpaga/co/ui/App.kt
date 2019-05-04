package development.tpaga.co.ui

import android.app.Application
import development.tpaga.co.ui.modules.main.utils.moduleMain
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(moduleMain))
        }
    }
}