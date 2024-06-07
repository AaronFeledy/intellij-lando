package com.github.aaronfeledy.landointellijplugin.ui

import com.github.aaronfeledy.landointellijplugin.LandoBundle
import com.github.aaronfeledy.landointellijplugin.services.LandoStatusService
import com.github.aaronfeledy.landointellijplugin.ui.console.JeditermConsoleView
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.openapi.wm.impl.content.ToolWindowContentUi
import com.intellij.ui.content.ContentFactory


class LandoToolWindowFactory : ToolWindowFactory {
    override fun init(toolWindow: ToolWindow) {
        toolWindow.component.putClientProperty(ToolWindowContentUi.ALLOW_DND_FOR_TABS, true)
        toolWindow.setToHideOnEmptyContent(false)
        toolWindow.stripeTitle = LandoBundle.message("stripe.lando.title")
        toolWindow.isAvailable = true
    }

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val landoConsoleView = JeditermConsoleView(project)
        val contentFactory = ContentFactory.getInstance()
        val content = contentFactory.createContent(landoConsoleView.component, LandoBundle.message("tab.lando.title"), false)
        toolWindow.contentManager.addContent(content)

        // Pass the ConsoleView instance to LandoStatusService
        LandoStatusService.getInstance().consoleView = landoConsoleView
    }
}