package development.tpaga.co.ui.modules.main.data

import android.arch.persistence.room.*
import development.tpaga.co.ui.modules.main.models.PaymentResponse
import io.reactivex.Flowable

@Dao
interface PaymentResponseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(paymentResponse: PaymentResponse)

    @Update
    fun update(paymentResponse: PaymentResponse)

    @Query("SELECT * FROM paymentresponse WHERE token = :token LIMIT 1")
    fun getPaymentResponseToken(token:String):Flowable<PaymentResponse>

    @Query("SELECT * FROM paymentresponse")
    fun getAllPaymentResponse():Flowable<List<PaymentResponse>>

}