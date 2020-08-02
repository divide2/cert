package com.divide2.sys.service.impl

import com.divide2.core.er.Loginer
import com.divide2.core.er.Posi
import com.divide2.core.er.TencentMaper
import com.divide2.core.service.impl.ServiceImpl
import com.divide2.sys.dto.AddressDTO
import com.divide2.sys.model.Address
import com.divide2.sys.model.OperateCity
import com.divide2.sys.repository.AddressRepository
import com.divide2.sys.repository.OperateCityRepository
import com.divide2.sys.service.AddressService
import net.sourceforge.pinyin4j.PinyinHelper
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AddressServiceImpl(
        var addressRepository: AddressRepository,
        var tencentMaper: TencentMaper,
        var operateCityRepository: OperateCityRepository
) : ServiceImpl<Address, Int, AddressRepository>(), AddressService {

    override fun listByUser(userId: Int): List<Address> {
        return addressRepository.findByOrgIdAndDeleted(userId, false)
    }

    override fun add(dto: AddressDTO): Address {
        val posi = Posi(dto.longitude, dto.latitude)
        val addressInfo = tencentMaper.resolve(posi)
        val address = Address(
                addressInfo.result.addressComponent.province,
                addressInfo.result.addressComponent.city,
                dto.address,
                addressInfo.result.addressComponent.district,
                dto.longitude,
                dto.latitude,
                dto.detail,
                deleted = false
        )
        address.orgId = Loginer.getId()
        this.addOperateCity(address.city)
        return super.add(address)
    }

    fun addOperateCity(city: String) {
        val format = HanyuPinyinOutputFormat()
        format.vCharType = HanyuPinyinVCharType.WITH_V
        format.caseType = HanyuPinyinCaseType.UPPERCASE
        format.toneType = HanyuPinyinToneType.WITHOUT_TONE
        var cityName = ""
        if (city.endsWith("市")) {
            cityName = city.substring(0, city.length - 1)
        }
        val pinyin = PinyinHelper.toHanYuPinyinString(cityName, format, "_", false)
        var operateCity = operateCityRepository.findByName(cityName)
        if (operateCity == null) {
            operateCity = OperateCity(cityName, pinyin, pinyin.first().toString())
            operateCityRepository.save(operateCity)
        }
    }

    override fun update(id: Int, dto: AddressDTO): Address {
        val address = this.getById(id)
        address.updateAt = LocalDateTime.now()
        val posi = Posi(dto.longitude, dto.latitude)
        val addressInfo = tencentMaper.resolve(posi)
        address.city = addressInfo.result.addressComponent.city
        address.province = addressInfo.result.addressComponent.province
        address.district = addressInfo.result.addressComponent.district
        address.detail = dto.detail
        address.address = dto.address
        return super.update(address)
    }

    override fun listCity(): List<String> {
        return addressRepository.listCity()
    }

    override fun deleteById(id: Int) {
        val address = this.getById(id)
        address.deleted = true
        this.update(address)
    }
}