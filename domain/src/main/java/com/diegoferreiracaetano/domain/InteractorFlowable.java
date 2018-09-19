package com.diegoferreiracaetano.domain;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public abstract class InteractorFlowable<T, R extends InteractorFlowable.Request> {

    protected abstract Flowable<T> create(R request);

    public Flowable<T> execute(R request) {
        return create(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public static abstract class Request {
    }
}
