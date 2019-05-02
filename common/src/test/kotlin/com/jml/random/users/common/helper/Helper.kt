package com.jml.random.users.common.helper

import org.threeten.bp.zone.TzdbZoneRulesProvider
import org.threeten.bp.zone.ZoneRulesProvider

fun Any.loadTimeZone() {
    if (ZoneRulesProvider.getAvailableZoneIds().isEmpty()) {
        val stream = this.javaClass.classLoader.getResourceAsStream("TZDB.dat")
        stream.use(::TzdbZoneRulesProvider).apply {
            ZoneRulesProvider.registerProvider(this)
        }
    }
}