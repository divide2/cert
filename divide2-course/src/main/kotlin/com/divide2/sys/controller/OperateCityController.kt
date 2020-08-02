package com.divide2.sys.controller

import com.divide2.sys.model.OperateCity
import com.divide2.sys.service.OperateCityService
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/v1/operate")
@RestController()
class OperateCityController(val operateCityService: OperateCityService) {

    @GetMapping("/cities")
    @ApiOperation("获取运营的城市")
    fun cities(): ResponseEntity<List<OperateCity>> {
        val cities = operateCityService.findAll()
        return ResponseEntity.ok(cities)
    }
}
