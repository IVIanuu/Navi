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
import android.view.View
import java.util.*

//shared
data class Create(val savedInstanceState: Bundle?)
object Start
object Resume
object Pause
object Stop
object Destroy

data class ActivityResult(
    val requestCode: Int,
    val resultCode: Int,
    val data: Intent?
)

data class RequestPermissionsResult(
    val requestCode: Int,
    val permissions: Array<out String>,
    val grantResults: IntArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RequestPermissionsResult) return false

        if (requestCode != other.requestCode) return false
        if (!Arrays.equals(permissions, other.permissions)) return false
        if (!Arrays.equals(grantResults, other.grantResults)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = requestCode
        result = 31 * result + Arrays.hashCode(permissions)
        result = 31 * result + Arrays.hashCode(grantResults)
        return result
    }
}

data class SaveInstanceState(val outState: Bundle)

data class ConfigurationChanged(val newConfiguration: Configuration?)

// activity
data class PostCreate(val savedInstanceState: Bundle?)
object Restart
object AttachedToWindow
object DetachedFromWindow
object PostResume
data class NewIntent(val intent: Intent)
data class RestoreInstanceState(val savedInstanceState: Bundle?)

// app compat activity
object ResumeFragments

// fragments
data class Attach(val context: Context)
data class ActivityCreated(val savedInstanceState: Bundle?)
data class ViewCreated(val view: View, val savedInstanceState: Bundle?)
data class ViewStateRestored(val savedInstanceState: Bundle?)
object DestroyView
object Detach