package com.divide2.course.controller

import com.divide2.course.model.Teacher
import com.divide2.course.service.TeacherService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import javax.validation.Valid

@RestController
@RequestMapping("/v1/teacher")
class TeacherController(
        var teacherService: TeacherService
) {

    @PostMapping
    fun add(@RequestBody @Valid teacher: Teacher): ResponseEntity<Teacher> {
        teacherService.add(teacher)
        return ResponseEntity.created(URI("")).body(teacher)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Any> {
        teacherService.deleteById(id)
        return ResponseEntity.noContent().build()
    }

    @PutMapping
    fun update(teacher: Teacher): ResponseEntity<Teacher>? {
        teacherService.update(teacher)
        return ResponseEntity.ok(teacher)
    }



}