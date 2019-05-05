package development.tpaga.co.ui.modules.main.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import development.tpaga.co.R
import development.tpaga.co.databinding.TemplatePaymentResponseBinding
import development.tpaga.co.ui.modules.main.models.PaymentResponse
import development.tpaga.co.ui.modules.main.utils.inflate

class PaymentResponseAdapter  : RecyclerView.Adapter<PaymentResponseAdapter.PaymentResponseViewHolder>() {
    var paymentResponses: List<PaymentResponse> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PaymentResponseViewHolder  = PaymentResponseViewHolder(p0.inflate(
        R.layout.template_payment_response))

    override fun getItemCount(): Int  = paymentResponses.size

    override fun onBindViewHolder(p0: PaymentResponseViewHolder, p1: Int) {
        when (paymentResponses[p1].status){
            "created" -> paymentResponses[p1].status = "Creado"
            "failed" -> paymentResponses[p1].status = "Rechazado"
            "paid" -> paymentResponses[p1].status = "Pagado"
            "delivered" -> paymentResponses[p1].status = "Entregado"
        }
        p0.binding.payment = paymentResponses[p1]
    }

    class PaymentResponseViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding: TemplatePaymentResponseBinding = TemplatePaymentResponseBinding.bind(view)
    }
}