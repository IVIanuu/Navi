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

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatDialogFragment
import android.view.View
import com.ivianuu.navi.Listener
import com.ivianuu.navi.NaviComponent
import kotlin.reflect.KClass

/**
 * Navi app compat dialog fragment
 */
open class NaviAppCompatDialogFragment : AppCompatDialogFragment(), NaviComponent {

    private val emitter = FragmentNaviEmitter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        emitter.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        emitter.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        emitter.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emitter.onViewCreated(view, savedInstanceState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        emitter.onViewStateRestored(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        emitter.onStart()
    }

    override fun onResume() {
        super.onResume()
        emitter.onResume()
    }

    override fun onPause() {
        emitter.onPause()
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        emitter.onSaveInstanceState(outState)
    }

    override fun onStop() {
        emitter.onStop()
        super.onStop()
    }

    override fun onDestroyView() {
        emitter.onDestroyView()
        super.onDestroyView()
    }

    override fun onDestroy() {
        emitter.onDestroy()
        super.onDestroy()
    }

    override fun onDetach() {
        emitter.onDetach()
        super.onDetach()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        emitter.onConfigurationChanged(newConfig)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        emitter.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        emitter.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun handlesEvents(events: Collection<KClass<*>>) =
        emitter.handlesEvents(events)

    override fun <T : Any> addListener(eventClass: KClass<T>, listener: Listener<T>) {
        emitter.addListener(eventClass, listener)
    }

    override fun removeListener(listener: Listener<*>) {
        emitter.removeListener(listener)
    }
}