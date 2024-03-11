package icons

import com.intellij.openapi.util.IconLoader
import javax.swing.Icon


object LandoIcons {
    @JvmField
    val Lando: Icon = IconLoader.getIcon("/icons/lando_16.svg", LandoIcons::class.java)

    @JvmField
    val Lando13: Icon = IconLoader.getIcon("/icons/lando_13.svg", LandoIcons::class.java)

    object Status {
        @JvmField
        val Started: Icon = IconLoader.getIcon("/icons/status_started.svg", LandoIcons::class.java)
        @JvmField
        val Stopped: Icon = IconLoader.getIcon("/icons/status_stopped.svg", LandoIcons::class.java)
    }
}