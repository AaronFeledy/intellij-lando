package com.github.aaronfeledy.landointellijplugin.services

import com.github.aaronfeledy.landointellijplugin.LandoExec
import com.intellij.openapi.Disposable
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.components.Service

@Service
class LandoAppService() : Disposable {
    private val projectService = ProjectManager.getInstance().defaultProject.getService(LandoProjectService::class.java)

    val appRoot: String = projectService.baseDir
}
