package com.divide2.core.er

import com.divide2.core.properties.TencentMapProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.stereotype.Component
import org.springframework.util.DigestUtils
import org.springframework.web.client.RestTemplate
import javax.validation.constraints.NotNull

@Component
class TencentMaper(
        var tencentMapProperties: TencentMapProperties){
    fun resolve(posi: Posi): AddressResult {
        val restTemplate = RestTemplate()
        val tencentMapUrl = "https://apis.map.qq.com"
        val locationUrl = "/ws/geocoder/v1?key=${tencentMapProperties.key}&location=${posi.latitude},${posi.longitude}"
        val encryptUrl = locationUrl + tencentMapProperties.secret
        val sig = DigestUtils.md5DigestAsHex(encryptUrl.toByteArray())
        val finalUrl = "$tencentMapUrl$locationUrl&sig=$sig"

        val resp = restTemplate.getForEntity(finalUrl, AddressResult::class.java)
        return resp.body!!
    }

}
data class Posi(@NotNull var longitude: Double,
                @NotNull var latitude: Double)

data class AddressResult(var status: Int, var message: String,
                         @JsonProperty("request_id")
                         var requestId: String,
                         var result: AddressDetail)

data class AddressDetail(var address: String,
                         @JsonProperty("address_component")
                         var addressComponent: AddressComponent)

data class AddressComponent(var nation: String,
                            var province: String,
                            var city: String,
                            var district: String,
                            var street: String,
                            @JsonProperty("street_number")
                            var streetNumber: String)
