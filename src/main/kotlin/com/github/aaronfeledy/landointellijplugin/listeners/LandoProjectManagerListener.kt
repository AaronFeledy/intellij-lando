package com.github.aaronfeledy.landointellijplugin.listeners

import com.github.aaronfeledy.landointellijplugin.services.LandoProjectService
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManagerListener

class LandoProjectManagerListener : ProjectManagerListener {
    override fun projectOpened(project: Project) {
        LandoProjectService.getInstance(project)
    }
}