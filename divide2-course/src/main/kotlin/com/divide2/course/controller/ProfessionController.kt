package com.divide2.course.controller

import com.divide2.course.model.Profession
import com.divide2.course.service.ProfessionService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import javax.validation.Valid

@RestController
@RequestMapping("/v1/professions")
@Api(tags=["行业"])
class ProfessionController(
        var professionService: ProfessionService
) {

    @PostMapping
    fun add(@RequestBody @Valid profession: Profession): ResponseEntity<Profession> {
        professionService.add(profession)
        return ResponseEntity.created(URI("")).body(profession)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Any> {
        professionService.deleteById(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping
    @ApiOperation("获取全部行业")
    fun list(): ResponseEntity<List<Profession>> {
        val list = professionService.list()
        return ResponseEntity.ok(list)
    }

    @PutMapping
    fun update(profession: Profession): ResponseEntity<Profession> {
        professionService.update(profession)
        return ResponseEntity.ok(profession)
    }

    @GetMapping("{id}")
    fun get(@PathVariable id: Int): ResponseEntity<Profession> {
        return ResponseEntity.ok(professionService.getById(id))
    }



}