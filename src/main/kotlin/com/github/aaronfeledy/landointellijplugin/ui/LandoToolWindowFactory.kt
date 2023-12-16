package com.github.aaronfeledy.landointellijplugin.ui

import com.github.aaronfeledy.landointellijplugin.LandoBundle
import com.github.aaronfeledy.landointellijplugin.ui.console.JeditermConsoleView
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.openapi.wm.impl.content.ToolWindowContentUi
import com.intellij.ui.content.ContentFactory
import javax.swing.JPanel


class LandoToolWindowFactory : ToolWindowFactory {
    override fun init(toolWindow: ToolWindow) {
        toolWindow.component.putClientProperty(ToolWindowContentUi.ALLOW_DND_FOR_TABS, true)
        toolWindow.setToHideOnEmptyContent(false)
        toolWindow.stripeTitle = LandoBundle.message("tab.title.lando")
        toolWindow.isAvailable = true
    }

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
//        val manager = toolWindow.contentManager
//        val portPanel: JPanel = ConnectPanel(toolWindow)
//        val content: Content =
//            manager.factory.createContent(portPanel, SerialMonitorBundle.message("tab.title.connect"), true)
//        content.setCloseable(false)
//        manager.addContent(content)
        

//        val landoConsoleView = JeditermConsoleView(project)
//        val contentFactory = ContentFactory.getInstance()
//        val content = contentFactory.createContent(landoConsoleView.consoleView.component, "", false)
//        toolWindow.contentManager.addContent(content)
    }
}