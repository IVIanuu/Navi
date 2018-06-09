/*
 * Copyright 2018 Manuel Wrage
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ivianuu.navi.rx

import com.ivianuu.navi.Listener
import com.ivianuu.navi.NaviComponent
import io.reactivex.Observable
import kotlin.reflect.KClass

fun <T : Any> NaviComponent.observe(eventClass: KClass<T>): Observable<T> {
    return Observable.create { e ->
        val listener = object : Listener<T> {
            override fun call(event: T) {
                if (!e.isDisposed) {
                    e.onNext(event)
                }
            }
        }

        e.setCancellable { removeListener(listener) }

        if (!e.isDisposed) {
            addListener(eventClass, listener)
        }
    }
}

inline fun <reified T : Any> NaviComponent.observe(): Observable<T> {
    return observe(T::class)
}