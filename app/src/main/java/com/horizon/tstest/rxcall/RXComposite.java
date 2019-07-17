package com.horizon.tstest.rxcall;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/*By Phat La*/
public interface RXComposite {

    //The CompositeDisposable must belong to each caller implemented RXComposite
    CompositeDisposable getCompositeDisposable();

    //Handle observable with simple object
    default <T> void SimpleCall(Observable<T> observable, RXCallHandler<T> handler){
        getCompositeDisposable()
                .add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(handler::Success, handler::Failure, ()->{
                    //Nothing to deal with this
                }, handler::onSubscribe));
    }

    //Dispose all
    default void DisposeCalls(){
        getCompositeDisposable().clear();
    }
}
