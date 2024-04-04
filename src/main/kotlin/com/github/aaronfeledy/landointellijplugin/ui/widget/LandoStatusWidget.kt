package com.github.aaronfeledy.landointellijplugin.ui.widget

import com.github.aaronfeledy.landointellijplugin.LandoBundle
import com.github.aaronfeledy.landointellijplugin.services.LandoAppService
import com.github.aaronfeledy.landointellijplugin.services.LandoProjectService
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.StatusBar
import com.intellij.openapi.wm.StatusBarWidget
import com.intellij.util.Consumer
import icons.LandoIcons
import java.awt.event.MouseEvent
import javax.swing.Icon


open class LandoStatusWidget(private val project: Project) : StatusBarWidget, StatusBarWidget.IconPresentation {

    companion object {
        const val ID: String = "LandoStatusWidget"
    }

    override fun ID(): String = ID

    override fun getPresentation(): StatusBarWidget.WidgetPresentation = this

    override fun install(statusBar: StatusBar) {}

    override fun dispose() {}

    override fun getTooltipText(): String {
        return if (LandoAppService.getInstance().started) {
            LandoBundle.message("widget.lando.status.tooltip.started")
        } else {
            LandoBundle.message("widget.lando.status.tooltip.stopped")
        }
    }

    override fun getClickConsumer(): Consumer<MouseEvent> {
        return Consumer {
            TODO("Not yet implemented")
        }
    }

    override fun getIcon(): Icon {
        return if (checkLandoFileAndStatus()) LandoIcons.Status.Started else LandoIcons.Status.Stopped
    }

    private fun checkLandoFileAndStatus(): Boolean {
        LandoProjectService.getInstance(project).let { thisProject ->
            if (thisProject.usesLando()){
                LandoAppService.getInstance().let { landoApp ->
                    if (landoApp.started) {
                        return true
                    }
                }
            }
        }
        return false
    }
}