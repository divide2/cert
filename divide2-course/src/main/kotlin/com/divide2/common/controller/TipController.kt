package com.divide2.common.controller

import com.divide2.common.model.Tip
import com.divide2.common.service.TipService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/v1/tips")
class TipController(
        val tipService: TipService
) {

    @GetMapping
    fun page(pageable: Pageable): ResponseEntity<Page<Tip>> {
        val page = tipService.page(pageable)
        return ResponseEntity.ok(page)
    }

    @PostMapping
    fun add( @RequestBody tip: Tip): ResponseEntity<Any> {
        tipService.add(tip)
        return ResponseEntity.created(URI("")).build()
    }

    @PutMapping("/{id}")
    fun add( @RequestBody tip: Tip, @PathVariable id: Int): ResponseEntity<Any> {
        val oldTip = tipService.getById(id)
        oldTip.status = tip.status
        oldTip.weight = tip.weight
        oldTip.message = tip.message
        tipService.update(oldTip)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Any> {
        tipService.deleteById(id)
        return ResponseEntity.noContent().build()
    }
}