package com.divide2.core.web

import com.divide2.core.uploader.AliOssUploader
import org.springframework.http.ResponseEntity
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI


@RestController
class SimpleController(val aliOssUploader: AliOssUploader) {

    @PostMapping("/v1/upload/image")
    fun upload(@RequestPart file: MultipartFile): ResponseEntity<String> {
        val url = aliOssUploader.imageUpload(file)
        return ResponseEntity.created(URI(url)).body(url)
    }

}
