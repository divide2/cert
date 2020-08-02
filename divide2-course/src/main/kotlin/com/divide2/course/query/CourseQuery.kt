package com.divide2.course.query

data class CourseQuery(
        var hasCert: Boolean?,
        var professionId: Int?,
        var free: Boolean?,
        var status: String?,
        var city: String?,
        var name: String?
)