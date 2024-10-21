package com.kurayamigin.fantasy.tagging_service.utils;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Callable;

public class ReactiveUtils {
    public static <T> Mono<T> reactiveBlocking(Callable<T> blockingCall) {
        return Mono.fromCallable(blockingCall)
                .subscribeOn(Schedulers.boundedElastic());
    }

    public static <T> Mono<Void> reactiveBlocking(Runnable blockingCall) {
        return Mono.fromRunnable(blockingCall);
    }


}
