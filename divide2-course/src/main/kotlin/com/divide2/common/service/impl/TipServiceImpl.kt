package com.divide2.common.service.impl

import com.divide2.common.model.Tip
import com.divide2.common.repository.TipRepository
import com.divide2.common.service.TipService
import com.divide2.core.service.impl.ServiceImpl
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class TipServiceImpl(val tipRepository: TipRepository) : ServiceImpl<Tip, Int, TipRepository>(), TipService {

    override fun page(pageable: Pageable): Page<Tip> {
        val sort = Sort.by(Sort.Order.desc("weight"))
        val request = PageRequest.of(pageable.pageNumber, pageable.pageSize, sort)
        return tipRepository.findAll(request)
    }
}