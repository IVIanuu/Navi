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

/**
 * Emitter of Navi events which contains all the actual logic
 */
open class NaviEmitter(events: Collection<KClass<*>> = emptyList()) : NaviComponent {

    private val handledEvents= mutableSetOf<KClass<*>>()

    private val listeners = mutableListOf<ListenerEntry<*>>()

    init {
        handledEvents.addAll(events)
    }

    override fun handlesEvents(events: Collection<KClass<*>>) =
        handledEvents.containsAll(events)

    override fun <T : Any> addListener(eventClass: KClass<T>, listener: Listener<T>) {
        val entry = ListenerEntry(eventClass, listener)
        // todo check duplicates
        listeners.add(entry)
    }

    override fun removeListener(listener: Listener<*>) {
        val entry = listeners.firstOrNull { it.listener == listener }
        listeners.remove(entry)
    }

    fun emitEvent(event: Any) {
        if (handledEvents.none { it.java.isAssignableFrom(event.javaClass) }) {
            throw IllegalArgumentException("unsupported event ${event.javaClass.name}")
        }

        listeners
            .filter { it.eventClass.java.isAssignableFrom(event.javaClass) }
            .forEach { it.emitEvent(event) }
    }

    protected fun addHandledEvents(events: Collection<KClass<*>>) {
        handledEvents.addAll(events)
    }

    private class ListenerEntry<T : Any>(
        val eventClass: KClass<T>,
        val listener: Listener<T>
    ) {
        fun emitEvent(event: Any) {
            listener.call(event as T)
        }
    }

}