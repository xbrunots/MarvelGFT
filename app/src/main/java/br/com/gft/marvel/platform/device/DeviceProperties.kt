package br.com.gft.marvel.platform.device

import br.com.gft.marvel.MarvelApplication

class DeviceProperties {
    companion object {
        fun getStatusBarHeight(): Int {
            var result = 0
            val resourceId =
                MarvelApplication.getContext().resources.getIdentifier("status_bar_height", "dimen", "android")
            if (resourceId > 0) {
                result = MarvelApplication.getContext().resources.getDimensionPixelSize(resourceId)
            }
            return result
        }

        fun getNavbarHeight(): Int {
            val resources = MarvelApplication.getContext().resources
            val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
            return if (resourceId > 0) {
                MarvelApplication.getContext().resources.getDimensionPixelSize(resourceId)
            } else 0
        }
    }
}