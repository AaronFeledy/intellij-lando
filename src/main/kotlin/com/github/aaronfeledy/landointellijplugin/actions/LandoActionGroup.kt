package com.github.aaronfeledy.landointellijplugin.actions

import com.intellij.openapi.actionSystem.ActionGroup
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import icons.LandoIcons

/**
 * Determines the Lando menu group Action times. LandoActionGroup] is based on [ActionGroup] because menu
 * children are determined on rules other than just positional constraints.
 *
 * @see ActionGroup
 */
class LandoActionGroup : ActionGroup() {
  /**
   * Returns an array of menu actions for the group.
   *
   * @param e Event received when the associated group-id menu is chosen.
   * @return AnAction[] An instance of [AnAction], in this case containing a single instance of the
   * [LandoStartAction] class.
   */
  override fun getChildren(e: AnActionEvent?): Array<AnAction> {
    return arrayOf(
      LandoStartAction("Start", "Start your Lando environment", LandoIcons.Lando)
    )
  }
}