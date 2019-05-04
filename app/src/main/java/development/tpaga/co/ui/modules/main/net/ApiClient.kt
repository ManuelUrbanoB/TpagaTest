package development.tpaga.co.ui.modules.main.net

import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private var BASE_URL = "https://stag.wallet.tpaga.co/merchants/api/v1/"
    private var retrofit:Retrofit? = null
    private var gson = GsonBuilder()
        .setLenient()
        .create()
    private fun createInstance():Retrofit{
        if (retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
        }
        return retrofit!!
    }

    fun intercaceStation(): InterfacePayment = createInstance().create(InterfacePayment::class.java)
}