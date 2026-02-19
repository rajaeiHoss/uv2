package com.hjq.base.action

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat

interface ResourcesAction {
    fun getContext(): Context

    fun getResources(): Resources {
        return getContext().resources
    }

    fun getString(id: Int): String {
        return getContext().getString(id)
    }

    fun getString(id: Int, vararg args: Any): String {
        return getResources().getString(id, *args)
    }

    fun getDrawable(id: Int): Drawable? {
        return ContextCompat.getDrawable(getContext(), id)
    }

    fun getColor(id: Int): Int {
        return ContextCompat.getColor(getContext(), id)
    }

    fun <S> getSystemService(serviceClass: Class<S>): S? {
        return ContextCompat.getSystemService(getContext(), serviceClass)
    }

    object CC {
        @JvmStatic
        fun `$default$getResources`(resourcesAction: ResourcesAction): Resources {
            return resourcesAction.getContext().resources
        }

        @JvmStatic
        fun `$default$getString`(resourcesAction: ResourcesAction, id: Int): String {
            return resourcesAction.getContext().getString(id)
        }

        @JvmStatic
        fun `$default$getString`(resourcesAction: ResourcesAction, id: Int, vararg args: Any): String {
            return resourcesAction.getResources().getString(id, *args)
        }

        @JvmStatic
        fun `$default$getDrawable`(resourcesAction: ResourcesAction, id: Int): Drawable? {
            return ContextCompat.getDrawable(resourcesAction.getContext(), id)
        }

        @JvmStatic
        fun `$default$getColor`(resourcesAction: ResourcesAction, id: Int): Int {
            return ContextCompat.getColor(resourcesAction.getContext(), id)
        }

        @JvmStatic
        fun <S> `$default$getSystemService`(
            resourcesAction: ResourcesAction,
            serviceClass: Class<S>
        ): S? {
            return ContextCompat.getSystemService(resourcesAction.getContext(), serviceClass)
        }
    }
}
