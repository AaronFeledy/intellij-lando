package com.github.aaronfeledy.landointellijplugin.ui.console

import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.ui.ConsoleView
import com.intellij.execution.ui.ConsoleViewContentType
import com.intellij.execution.filters.TextConsoleBuilderImpl
import com.intellij.openapi.project.Project

class LandoConsoleView(project: Project) {
    val consoleView: ConsoleView = TextConsoleBuilderImpl(project).console

    fun print(text: String, contentType: ConsoleViewContentType) {
        consoleView.print(text, contentType)
    }

    fun attachToProcess(processHandler: ProcessHandler) {
        consoleView.attachToProcess(processHandler)
    }
}