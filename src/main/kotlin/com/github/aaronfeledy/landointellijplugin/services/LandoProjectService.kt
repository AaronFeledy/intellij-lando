package com.github.aaronfeledy.landointellijplugin.services

import com.intellij.openapi.project.Project
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.project.guessProjectDir
import com.intellij.openapi.vfs.VirtualFile

// The name of the Lando configuration file
const val landoFileName = ".lando.yml"

/**
 * Service for managing Lando project-specific data and operations.
 *
 * This service is scoped at the Project level in the IntelliJ Platform, meaning a separate instance is created for each open Project.
 * It's responsible for managing the Lando configuration file (.lando.yml) and providing utility methods related to the Lando project.
 *
 * @property project The IntelliJ Project this service is associated with.
 */
@Service(Service.Level.PROJECT)
class LandoProjectService(val project: Project) {
    // The root directory of the project. It's where we expect to find the Lando configuration file.
    var projectDir: VirtualFile? = project.guessProjectDir()

    // The Lando configuration file in the project directory. It's presence determines whether the project uses Lando.
    var landoFile: VirtualFile? = projectDir?.findChild(landoFileName)

    /**
     * Checks if the current project uses Lando.
     *
     * This is determined by the existence of a Lando configuration file (.lando.yml) in the project directory.
     * This method is important for enabling or disabling Lando-specific features based on whether the project uses Lando.
     *
     * @return True if the project uses Lando, false otherwise.
     */
    fun usesLando(): Boolean {
        return landoFile?.exists()!!
    }

    companion object {
        /**
         * Retrieves the LandoProjectService instance associated with the given Project.
         *
         * This static method is a convenience for getting the LandoProjectService instance without having to manually fetch it from the ServiceManager.
         * It's used throughout the codebase whenever access to the LandoProjectService is needed.
         *
         * @param project The Project for which to retrieve the LandoProjectService.
         * @return The LandoProjectService instance associated with the given Project.
         */
        @JvmStatic
        fun getInstance(project: Project): LandoProjectService = project.service()
    }
}
