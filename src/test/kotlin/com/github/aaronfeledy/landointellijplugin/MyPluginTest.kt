package com.github.aaronfeledy.landointellijplugin

import com.github.aaronfeledy.landointellijplugin.services.LandoProjectService
import com.intellij.openapi.components.service
import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.fixtures.BasePlatformTestCase

@TestDataPath("\$CONTENT_ROOT/src/test/testData")
class MyPluginTest : BasePlatformTestCase() {

    fun testProjectService() {
        val projectService = project.service<LandoProjectService>()

        assertNotNull(projectService)
    }
}
