package com.github.aaronfeledy.landointellijplugin

data class ServiceData(
    val service: String,
    val urls: List<String>,
    val type: String,
    val healthy: String,
    val via: String,
    val webroot: String,
    val config: Map<String, Any>,
    val version: String,
    val meUser: String,
    val hasCerts: Boolean,
    val api: Int,
    val hostnames: List<String>
)
