package com.divide2.common.service.impl

import com.divide2.common.model.Slider
import com.divide2.common.repository.SliderRepository
import com.divide2.common.service.SliderService
import com.divide2.core.service.impl.ServiceImpl
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class SliderServiceImpl(val sliderRepository: SliderRepository) : ServiceImpl<Slider, Int, SliderRepository>(), SliderService {
    override fun page(pageable: Pageable): Page<Slider> {
        val sort = Sort.by(Sort.Order.desc("weight"))
        val request = PageRequest.of(pageable.pageNumber, pageable.pageSize, sort)
        return sliderRepository.findAll(request)
    }
}