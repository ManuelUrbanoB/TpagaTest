package development.tpaga.co.ui.modules.main.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import development.tpaga.co.R
import development.tpaga.co.databinding.TemplateProductBinding
import development.tpaga.co.ui.modules.main.models.PaymentRequest
import development.tpaga.co.ui.modules.main.utils.inflate
import io.reactivex.subjects.PublishSubject

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    var clickBuyProduct :PublishSubject<PaymentRequest> = PublishSubject.create<PaymentRequest>()

    var listItems :List<PaymentRequest> = emptyList()
    set(value) {
        field= value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ProductViewHolder = ProductViewHolder(p0.inflate(R.layout.template_product))

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(p0: ProductViewHolder, p1: Int) {
        p0.binding.payment = listItems[p1]
        p0.binding.clickBuy = clickBuyProduct
    }
    class ProductViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding: TemplateProductBinding = TemplateProductBinding.bind(view)
    }
}