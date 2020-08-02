package com.divide2.course.service.impl

import com.divide2.core.service.impl.ServiceImpl
import com.divide2.course.model.Profession
import com.divide2.course.model.Teacher
import com.divide2.course.repository.ProfessionRepository
import com.divide2.course.repository.TeacherRepository
import com.divide2.course.service.ProfessionService
import com.divide2.course.service.TeacherService
import org.hibernate.transform.Transformers
import org.springframework.stereotype.Service

@Service
class ProfessionServiceImpl :ServiceImpl<Profession,Int, ProfessionRepository>(), ProfessionService {

}