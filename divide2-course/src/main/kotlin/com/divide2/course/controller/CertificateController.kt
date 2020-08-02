package com.divide2.course.controller

import com.divide2.core.er.Loginer
import com.divide2.course.model.Certificate
import com.divide2.course.service.CertificateService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import javax.validation.Valid

@Api(tags = ["证书"])
@RestController
@RequestMapping("/v1/org/certificates")
class CertificateController(
        var certificateService: CertificateService
) {

    @GetMapping
    @ApiOperation("获取全部证书")
    fun list(): ResponseEntity<List<Certificate>> {
        val certificates = certificateService.listByUser(Loginer.getId())
        return ResponseEntity.ok(certificates)
    }

    @PostMapping
    @ApiOperation("添加证书")
    fun addCertificate(@RequestBody @Valid certificateDTO: Certificate): ResponseEntity<Certificate> {
        certificateDTO.createUserId = Loginer.getId()
        val certificate = certificateService.add(certificateDTO)
        return ResponseEntity.created(URI("")).body(certificate)
    }

    @ApiOperation("删除证书")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Any> {
        certificateService.deleteById(id)
        return ResponseEntity.noContent().build()
    }

    @PutMapping("/{id}")
    @ApiOperation("修改证书")
    fun update(@RequestBody @Valid certificateDTO: Certificate, @PathVariable id: Int): ResponseEntity<Certificate> {
        val certificate = certificateService.getById(id)
        certificate.image = certificateDTO.image
        certificate.licensor = certificateDTO.licensor
        certificate.name = certificateDTO.name
        certificateService.update(certificateDTO)
        return ResponseEntity.ok(certificate)
    }

    @GetMapping("/{id}")
    @ApiOperation("获取单个证书详情")
    fun get(@PathVariable id: Int): ResponseEntity<Certificate> {
        val certificate = certificateService.getById(id)
        return ResponseEntity.ok(certificate)
    }

}