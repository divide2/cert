package com.divide2.common.controller

import com.divide2.common.model.Slider
import com.divide2.common.service.SliderService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/v1/sliders")
class SliderController(
        val sliderService: SliderService
) {

    @GetMapping
    fun page(pageable: Pageable): ResponseEntity<Page<Slider>> {
        val page = sliderService.page(pageable)
        return ResponseEntity.ok(page)
    }

    @PostMapping
    fun add(@RequestBody slider: Slider): ResponseEntity<Any> {
        sliderService.add(slider)
        return ResponseEntity.created(URI("")).build()
    }

    @PutMapping("/{id}")
    fun add(@RequestBody slider: Slider, @PathVariable id: Int): ResponseEntity<Any> {
        val oldSlider = sliderService.getById(id)
        oldSlider.weight = slider.weight
        oldSlider.link = slider.link
        oldSlider.image = slider.image
        sliderService.update(oldSlider)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Any> {
        sliderService.deleteById(id)
        return ResponseEntity.noContent().build()
    }
}