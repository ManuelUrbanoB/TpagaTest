package development.tpaga.co.ui.modules.main.ui.productLink

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import development.tpaga.co.R
import development.tpaga.co.ui.modules.main.ui.main.MainActivity
import development.tpaga.co.ui.modules.main.utils.*
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_product_link.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.android.viewmodel.ext.android.viewModel

class ProductLinkActivity : AppCompatActivity() {

    lateinit var preferences: SharedPreferences
    val viewModel by viewModel<ProductLinkViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_link)
        preferences = this.getSharedPreferences("PreferencesPayment", Context.MODE_PRIVATE)
        setDataProduct()

    }

    /***
     * @function Función que permite colocar los datos el pedido en la vista
     */
    private fun setDataProduct(){
        val lastToken = preferences.getString("lastProductToken", "")
        if(lastToken == ""){
            startActivity<MainActivity>()
        }
        viewModel.getStatusPaymentRequest(authorizationUser,lastToken!!).subscribeBy(onNext = {
            purchase_description.text = it.purchase_description
            purchase_cost.text = it.cost.toString()
            if(it.status == "paid"){
                purchase_state.text = STATUS_PAYMENT_OK
            }else if(it.status == "failed"){
                purchase_state.text = STATUS_PAYMENT_FAILED
            }else if(it.status == "delivered"){
                purchase_state.text = STATUS_PAYMENT_DELIVERED
            }else{
                purchase_state.text = it.status
            }
            updatePaymentResponsePersistence(it.token,it.status)
        }, onError = {
            longToast(ERROR_SERVER_CREATE)
            longToast(STATUS_PAYMENT_ERROR)
            startActivity<MainActivity>()
        })
    }

    /***
     * @entries token
     * @entries status
     * @function Función que permite actualizar el estado de un pedido
     */
    private fun updatePaymentResponsePersistence(token:String, status: String){
        viewModel.getPaymentResponsePersistence(token).subscribeBy(onNext = {
            if(it.id != null){
                it.status = status
                viewModel.updatePaymentResponsePersistence(it)
            }
        },onError = {
            toast(PRODUCTO_NO_EXIST_PERSISTENCE)
        })
    }

    override fun onResume() {
        super.onResume()
        btnGoTOHome.setOnClickListener {
            startActivity<MainActivity>()
        }
    }
}
