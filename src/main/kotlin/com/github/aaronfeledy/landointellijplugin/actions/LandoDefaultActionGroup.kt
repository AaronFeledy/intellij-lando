package com.github.aaronfeledy.landointellijplugin.actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.openapi.editor.Editor

/**
 * Creates an action group to contain menu actions. See plugin.xml declarations.
 */
class LandoDefaultActionGroup : DefaultActionGroup() {
    /**
     * Given [LandoDefaultActionGroup] is derived from [com.intellij.openapi.actionSystem.ActionGroup],
     * in this context `update()` determines whether the action group itself should be enabled or disabled.
     * Requires an editor to be active in order to enable the group functionality.
     *
     * @param event Event received when the associated group-id menu is chosen.
     * @see com.intellij.openapi.actionSystem.AnAction.update
     */
    override fun update(event: AnActionEvent) {
        // Enable/disable depending on whether user is editing
        val editor: Editor? = event.getData<Editor>(CommonDataKeys.EDITOR)
        event.presentation.isEnabled = editor != null
    }
}