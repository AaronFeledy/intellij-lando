package com.github.aaronfeledy.landointellijplugin.services

import com.intellij.openapi.project.Project
import com.github.aaronfeledy.landointellijplugin.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
