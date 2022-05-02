package com.github.aaronfeledy.landointellijplugin.actions

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.process.CapturingProcessHandler
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project;
import javax.swing.Icon


/**
 * Action class to start up a Lando environment.
 */
class LandoStartAction : AnAction {
  /**
   * This default constructor is used by the IntelliJ Platform framework to instantiate this class based on plugin.xml
   * declarations. Only needed in [LandoStartAction] class because a second constructor is overridden.
   *
   * @see AnAction
   */
  constructor() : super() {}

  /**
   * This constructor is used to support dynamically added menu actions.
   * It sets the text, description to be displayed for the menu item.
   * Otherwise, the default AnAction constructor is used by the IntelliJ Platform.
   *
   * @param text        The text to be displayed as a menu item.
   * @param description The description of the menu item.
   * @param icon        The icon to be used with the menu item.
   */
  constructor(text: String?, description: String?, icon: Icon?) : super(text, description, icon) {}

  /**
   * Executes `lando start`.
   *
   * @param event Event received when the associated menu item is chosen.
   */
  override fun actionPerformed(event: AnActionEvent) {
    // TODO: This currently only uses the Windows binary for Lando
    val landoCmd = "C:\\Program Files\\Lando\\bin\\lando.exe"
    val tenMinutes = 10 * 60 * 1000

    val project: Project? = event.project
    val projectPath: String? = project?.basePath
    val commandLine = GeneralCommandLine(landoCmd, "start").withWorkDirectory(projectPath)

    val processHandler = CapturingProcessHandler(commandLine)
    val output = processHandler.runProcess(tenMinutes)
    if (output.exitCode != 0 || output.isCancelled || output.isTimeout) {
      LOG.info(
        "command: " + processHandler.commandLine + " has failed:" +
            "ec=" + output.exitCode + ",cancelled=" + output.isCancelled + ",timeout=" + output.isTimeout
            + ",stderr=" + output.stderr + ",stdout=" + output.stdout
      )
    }
  }

  /**
   * Determines whether this menu item is available for the current context.
   * Requires a project to be open.
   *
   * @param e Event received when the associated group-id menu is chosen.
   */
  override fun update(e: AnActionEvent) {
    // Set the availability based on whether a project is open
    val project = e.project
    e.presentation.isEnabledAndVisible = project != null
  }

  companion object {
    val LOG = Logger.getInstance(
      LandoStartAction::class.java.name
    )
  }
}