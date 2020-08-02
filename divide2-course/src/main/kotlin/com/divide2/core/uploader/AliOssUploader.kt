package com.divide2.core.uploader

import com.aliyun.oss.OSSClient
import com.aliyun.oss.model.PutObjectRequest
import com.divide2.core.exception.SystemException
import com.divide2.core.exception.ValidationException
import com.divide2.core.properties.AliyunProperties
import org.apache.commons.io.FilenameUtils
import org.apache.http.client.utils.DateUtils
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.util.*
import javax.imageio.ImageIO

/**
 * @author bvvy
 * @date 2018/7/17
 */
@Component
class AliOssUploader(
        var properties: AliyunProperties
) {

    val GIF = "gif"

    /**
     * 上传图片
     *
     * @param file file
     * @return file
     */
    fun imageUpload(file: MultipartFile): String {
        val ext = FilenameUtils.getExtension(file.getOriginalFilename())
        //排除gif文件,避免bug
        if (GIF === ext) {
            throw  ValidationException("no_gif_image")
        }
        val inputStream = file.inputStream
        // 验证是不是图片文件
        val bi = ImageIO.read(inputStream) ?: throw  ValidationException("not_image")
        val keeper = UploadKeeper(inputStream, file.bytes, ext)
        return this.upload(keeper)
    }

    /**
     * 上传
     *
     * @param keeper keeper
     * @return file path
     */
    fun upload(keeper: UploadKeeper): String {

        val ossClient = OSSClient(properties.oss?.endPoint, properties.accessKeyId, properties.accessKeySecret)
        val result = ossClient.putObject(
                PutObjectRequest(properties.oss?.bucketName,
                        keeper.path,
                        ByteArrayInputStream(keeper.bytes))) ?: throw SystemException("upload_fail")
        return properties.oss?.fileHost + "/" + keeper.path
    }
    class UploadKeeper(
            var inputStream: InputStream,
            var bytes: ByteArray,
            var fileType: String
    ) {
        var path = DateUtils.formatDate(Date(), "yyyy/MM/dd") + "/${Date().time}.$fileType"
    }
}