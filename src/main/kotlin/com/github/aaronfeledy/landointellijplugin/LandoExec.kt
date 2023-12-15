package com.github.aaronfeledy.landointellijplugin

import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.ui.ConsoleViewContentType
import com.intellij.openapi.project.Project
import com.github.aaronfeledy.landointellijplugin.ui.console.JeditermConsoleView
import java.io.File
import java.io.OutputStream

object AnsiEscapeCodeParser {
    fun parse(text: String): String {
        // Basic ANSI escape code removal (this can be expanded to handle colors if needed)
        return text.replace(Regex("\\x1B\\[[;\\d]*m"), "")
    }
}

class LandoExec(private val project: Project, private val command: String) {
    private val baseDirectory = project.basePath ?: throw IllegalStateException("Project base path is null")

    fun run() {
        val processBuilder = ProcessBuilder("lando", command)
        processBuilder.directory(File(baseDirectory))
        processBuilder.redirectErrorStream(true)

        val process = processBuilder.start()
        val landoConsoleView = JeditermConsoleView(project, process)

        val processHandler = object : ProcessHandler() {
            override fun destroyProcessImpl() {
                process.destroy()
            }

            override fun detachProcessImpl() {
                destroyProcessImpl()
            }

            override fun detachIsDefault(): Boolean = false

            override fun getProcessInput(): OutputStream? = null

            override fun startNotify() {
                super.startNotify()
                Thread {
                    val reader = process.inputStream.bufferedReader()
                    reader.useLines { lines ->
                        lines.forEach { line ->
                            val formattedLine = AnsiEscapeCodeParser.parse(line)
                            landoConsoleView.print(formattedLine, ConsoleViewContentType.NORMAL_OUTPUT)
                        }
                    }
                }.start()
            }
        }

        landoConsoleView.attachToProcess(processHandler)
        processHandler.startNotify()
    }
}