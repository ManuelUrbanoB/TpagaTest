package development.tpaga.co.ui.modules.main.net

import retrofit2.Retrofit

object ApiClient {
    private var BASE_URL = "https://stag.wallet.tpaga.co/merchants/api/v1/"
    private var retrofit:Retrofit? = null
    private fun createInstance():Retrofit{
        if (retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build()
        }
        return retrofit!!
    }

    fun intercaceStation(): InterfacePayment = createInstance().create(InterfacePayment::class.java)
}