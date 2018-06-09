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

package com.ivianuu.navi

import kotlin.reflect.KClass

fun NaviComponent.handlesEvents(vararg events: KClass<*>): Boolean {
    return handlesEvents(events.toSet())
}

inline fun <reified T : Any> NaviComponent.addListener(listener: Listener<T>) {
    addListener(T::class, listener)
}

fun <T : Any> NaviComponent.addListener(eventClass: KClass<T>, listener: (T) -> Unit): Listener<T> {
    val l = object : Listener<T> {
        override fun call(event: T) {
            listener.invoke(event)
        }
    }
    addListener(eventClass, l)
    return l
}

inline fun <reified T : Any> NaviComponent.addListener(noinline listener: (T) -> Unit): Listener<T> {
    return addListener(T::class, listener)
}