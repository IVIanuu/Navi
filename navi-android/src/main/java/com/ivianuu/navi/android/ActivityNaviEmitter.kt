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

package com.ivianuu.navi.android

import android.content.Intent
import android.os.Bundle
import com.ivianuu.navi.NaviEmitter
import kotlin.reflect.KClass

/**
 * [NaviEmitter] for [Activity]'s
 */
open class ActivityNaviEmitter : AndroidNaviEmitter() {

    init {
        addHandledEvents(EVENTS)
    }

    fun onPostCreate(saveInstanceState: Bundle?) {
        emitEvent(PostCreate(saveInstanceState))
    }

    fun onRestart() {
        emitEvent(Restart)
    }

    fun onRestoreInstanceState(saveInstanceState: Bundle?) {
        emitEvent(RestoreInstanceState(saveInstanceState))
    }

    fun onPostResume() {
        emitEvent(PostResume)
    }

    fun onAttachedToWindow() {
        emitEvent(AttachedToWindow)
    }

    fun onDetachedFromWindow() {
        emitEvent(DetachedFromWindow)
    }

    fun onNewIntent(intent: Intent) {
        emitEvent(NewIntent(intent))
    }

    companion object {
        val EVENTS = setOf(
            PostCreate::class,
            PostResume::class,
            RestoreInstanceState::class,
            AttachedToWindow::class,
            DetachedFromWindow::class
        )
    }
}