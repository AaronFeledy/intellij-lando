package com.github.aaronfeledy.landointellijplugin.services

import com.github.aaronfeledy.landointellijplugin.LandoExec
import com.github.aaronfeledy.landointellijplugin.ServiceData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.intellij.execution.process.ProcessHandler
import com.intellij.openapi.components.Service
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.Disposable
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.util.ConcurrencyUtil
import java.util.*
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

// Logger instance for the LandoAppService class
val logger = Logger.getInstance(LandoAppService::class.java)

/**
 * Service class for managing Lando applications.
 * This class is responsible for fetching and maintaining the status of Lando applications.
 */
@Service
class LandoAppService() : Disposable {
    // Instance of the LandoProjectService
    val thisProject: LandoProjectService =
        LandoProjectService.getInstance(ProjectManager.getInstance().openProjects[0])

    // ScheduledExecutorService for periodically checking the status of the Lando application
    private val statusWatcher: ScheduledExecutorService = ConcurrencyUtil.newSingleScheduledThreadExecutor("Lando Status Watcher")
        .apply { scheduleWithFixedDelay({ fetchStatus() }, 0, 2000, TimeUnit.MILLISECONDS) }

    // Root directory of the Lando application
    var appRoot: VirtualFile? = thisProject.projectDir

    // Map for storing the status of the services in the Lando application
    var services: MutableMap<String, ServiceData> = HashMap()

    /**
     * Fetches the status of the Lando application.
     * @return ProcessHandler for the process fetching the status.
     */
    private fun fetchLandoAppStatus(): ProcessHandler {
        logger.debug("Fetching Lando app status...")
        return LandoExec("list").fetchJson().run()
    }

    /**
     * Fetches the information of the services in the Lando application.
     * @return ProcessHandler for the process fetching the service information.
     */
    private fun fetchLandoServiceInfo(): ProcessHandler {
        logger.debug("Fetching Lando service info...")
        return LandoExec("info").fetchJson().run()
    }

    /**
     * Checks the status of the Lando application and updates the services map.
     */
    private fun fetchStatus() {
        logger.debug("Checking Lando status...")
        val serviceInfoHandler = fetchLandoServiceInfo()
        serviceInfoHandler.waitFor(30000)
        if (!serviceInfoHandler.isProcessTerminated) {
            serviceInfoHandler.destroyProcess()
            logger.warn("Timeout while fetching Lando service info")
        }
        val serviceInfoJson = serviceInfoHandler.processInput.toString()
        val serviceData = parseServiceData(serviceInfoJson)

        services = serviceData.associateBy { it.service }.toMutableMap()
    }

    /**
     * Parses the JSON data of the service information into a list of ServiceData objects.
     * @param jsonData JSON data of the service information.
     * @return List of ServiceData objects.
     */
    private fun parseServiceData(jsonData: String): List<ServiceData> {
        val gson = Gson()
        val serviceListType = object : TypeToken<List<ServiceData>>() {}.type
        return gson.fromJson(jsonData, serviceListType)
    }

    /**
     * Disposes the LandoAppService, shutting down the status watcher.
     */
    override fun dispose() {
        statusWatcher.shutdown()
    }

    companion object {
        fun getInstance(): LandoAppService = ApplicationManager.getApplication().getService(LandoAppService::class.java)
    }
}
