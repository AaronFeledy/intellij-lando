package com.github.aaronfeledy.landointellijplugin.services

import com.intellij.openapi.project.Project
import com.github.aaronfeledy.landointellijplugin.LandoBundle

class LandoProjectService(project: Project) {

    init {
        println(LandoBundle.message("projectService", project.name))
    }
}
