package com.divide2.sys.controller

import com.divide2.core.er.Posi
import com.divide2.core.er.TencentMaper
import com.divide2.sys.dto.AddressDTO
import com.divide2.sys.model.Address
import com.divide2.sys.service.AddressService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import java.net.URI
import javax.validation.Valid

@RestController
@RequestMapping("/v1/address")
@Api(tags = ["地址"])
class AddressController(
        var tencentMaper: TencentMaper,
        var addressService: AddressService
) {

    @ApiOperation("通过经纬度获取城市")
    @GetMapping("/city/resolve")
    fun resolveAddress(@Valid posi: Posi): ResponseEntity<City> {
        val address = tencentMaper.resolve(posi)
        var city = address.result.addressComponent.city
        if (city.endsWith("市")) {
            city = city.substring(0, city.length - 1)
        }
        return ResponseEntity.ok(City(city))
    }

    @ApiOperation("添加")
    @PostMapping
    fun add(@Valid @RequestBody dto: AddressDTO): ResponseEntity<Address> {
        val address = addressService.add(dto)
        return ResponseEntity.created(URI.create("/v1/address/${address.id}")).body(address)
    }

    @ApiOperation("修改")
    @PutMapping("/{id}")
    fun update(@Valid @RequestBody dto: AddressDTO, @PathVariable id: Int): ResponseEntity<Address> {
        val address = addressService.update(id, dto)
        return ResponseEntity.ok(address)
    }

    @ApiOperation("查询")
    @GetMapping("/{id}")
    fun get(@PathVariable id: Int): ResponseEntity<Address> {
        val address = addressService.getById(id)
        return ResponseEntity.ok(address)
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除")
    fun delete(@PathVariable id: Int): ResponseEntity<Any> {
        addressService.deleteById(id)
        return ResponseEntity.noContent().build()
    }

}

data class City(var city: String)
