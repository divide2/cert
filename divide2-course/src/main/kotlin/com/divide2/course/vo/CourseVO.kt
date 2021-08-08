package com.divide2.course.vo

import java.math.BigDecimal
import java.time.LocalDate

class CourseVO {
    var id: Int = 0
    var name: String = ""
    var startTime: LocalDate? = null
    var endTime: LocalDate? = null
    var description: String = ""
    var professionId: Int = 0
    var professionName: String = ""

    var arrangement: String = ""
    var price: BigDecimal? = null
    var images: Array<String> = emptyArray()
    var details: String = ""
    var certificateId: Int = 0
    var certificateName: String = ""
    var certificateLicensor: String = ""
    var certificateImage: String = ""
    var orgId: Int = 0
    var orgName: String = ""
    var longitude: Double = 0.0
    var latitude: Double = 0.0
    var addressId: Int = 0
    var addressDetail: String = ""
    var address: String = ""
    var province: String = ""
    var city: String = ""
    var district: String = ""
    var capacity: Int = 0
    var enrolment: Int = 0
    var auditMessage: String = ""
    var status: String= ""
    var joined: Boolean = false
}

