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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ivianuu.navi.addListener
import com.ivianuu.navi.android.NaviAppCompatActivity
import com.ivianuu.navi.android.NaviFragment
import com.ivianuu.navi.rx.observe

class MainActivity : NaviAppCompatActivity() {

    private val lifecycleProvider = ActivityLifecycleProvider(this)

    init {
        addListener<Any> {
            d { "on event $it" }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleProvider.lifecycle()
            .subscribe { d { "on lifecycle event $it" } }

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(MainFragment(), "main")
                .commitNow()
        }
    }

}

class MainFragment : NaviFragment() {

    init {
        addListener<Any> {
            d { "on event $it" }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return View(requireContext())
    }

}