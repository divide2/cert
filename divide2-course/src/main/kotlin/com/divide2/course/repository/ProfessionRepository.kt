package com.divide2.course.repository

import com.divide2.course.model.Profession
import com.divide2.course.model.Teacher
import org.springframework.data.jpa.repository.JpaRepository

interface ProfessionRepository:JpaRepository<Profession,Int> {
}