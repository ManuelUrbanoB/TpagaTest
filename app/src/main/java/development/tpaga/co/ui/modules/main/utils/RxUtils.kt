package development.tpaga.co.ui.modules.main.utils

import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


fun <T> Flowable<T>.applySchedulers():Flowable<T>{
    return compose{it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()) }
}