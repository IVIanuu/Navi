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

package com.ivianuu.navi.sample

import com.ivianuu.navi.Listener
import com.ivianuu.navi.NaviComponent
import com.ivianuu.navi.addListener
import com.ivianuu.navi.android.*
import com.ivianuu.navi.handlesEvents
import io.reactivex.subjects.BehaviorSubject

/**
 * @author Manuel Wrage (IVIanuu)
 */
class ActivityLifecycleProvider(private val component: NaviComponent) : Listener<Any> {

    private val lifecycle = BehaviorSubject.create<ActivityEvent>()

    init {
        if (!component.handlesEvents(Create::class, Start::class, Resume::class,
                Pause::class, Stop::class, Destroy::class)) {
            throw IllegalStateException("component does not handle required events")
        }

        component.addListener(this)
    }

    override fun call(event: Any) {
        val activityEvent: ActivityEvent = when(event) {
            is Create -> ActivityEvent.CREATE
            Start -> ActivityEvent.START
            Resume -> ActivityEvent.RESUME
            Pause -> ActivityEvent.PAUSE
            Stop -> ActivityEvent.STOP
            Destroy -> ActivityEvent.DESTROY
            else -> null
        } ?: return

        lifecycle.onNext(activityEvent)
    }

    fun lifecycle() = lifecycle
}

enum class ActivityEvent {
    CREATE, START, RESUME, PAUSE, STOP, DESTROY
}