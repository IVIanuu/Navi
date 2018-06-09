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
import android.content.res.Configuration
import android.os.Bundle
import com.ivianuu.navi.NaviEmitter
import kotlin.reflect.KClass

/**
 * @author Manuel Wrage (IVIanuu)
 */
open class AndroidNaviEmitter : NaviEmitter() {

    init {
        addHandledEvents(EVENTS)
    }

    fun onCreate(savedInstanceState: Bundle?) {
        emitEvent(Create(savedInstanceState))
    }

    fun onStart() {
        emitEvent(Start)
    }

    fun onResume() {
        emitEvent(Resume)
    }

    fun onPause() {
        emitEvent(Pause)
    }

    fun onStop() {
        emitEvent(Stop)
    }

    fun onSaveInstanceState(outState: Bundle) {
        emitEvent(SaveInstanceState(outState))
    }

    fun onDestroy() {
        emitEvent(Destroy)
    }

    fun onConfigurationChanged(newConfiguration: Configuration?) {
        emitEvent(ConfigurationChanged(newConfiguration))
    }

    fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        emitEvent(ActivityResult(requestCode, resultCode, data))
    }

    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        emitEvent(RequestPermissionsResult(requestCode, permissions, grantResults))
    }

    companion object {
        val EVENTS = setOf(
            Create::class,
            Start::class,
            Resume::class,
            Pause::class,
            Stop::class,
            Destroy::class,
            ConfigurationChanged::class,
            ActivityResult::class,
            RequestPermissionsResult::class,
            SaveInstanceState::class
        )
    }

}