package com.horizon.tstest.rxcall;


import io.reactivex.disposables.Disposable;

/*By Phat La*/
public abstract class RXCallHandler<T> {
    public abstract void onSubscribe(Disposable d);
    public abstract void onSuccess(T s);
    public abstract void onFailure(Throwable t);
    public abstract void onFinally();

    public boolean isForward(){
        return true;
    }

    //Observable Functions
    final void Success(T s){
        if(isForward()){
            onSuccess(s);
            onFinally();
        }
    }

    final void Failure(Throwable t){
        if(isForward()){
            onFailure(t);
            onFinally();
        }
    }
}
