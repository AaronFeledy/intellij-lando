package com.github.aaronfeledy.landointellijplugin.services

import com.intellij.openapi.project.Project
import com.intellij.openapi.components.Service

@Service(Service.Level.PROJECT)
class LandoProjectService(val project: Project) {
    val baseDir: String = project.basePath ?: throw IllegalStateException("Project base path is null")
}
