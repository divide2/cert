package com.divide2.course.service

import com.divide2.core.service.IService
import com.divide2.course.model.Certificate
import com.divide2.course.model.Teacher
import com.divide2.course.repository.CertificateRepository
import com.divide2.course.repository.TeacherRepository

interface TeacherService : IService<Teacher,Int, TeacherRepository>{


}