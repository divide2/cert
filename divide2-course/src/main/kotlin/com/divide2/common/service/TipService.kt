package com.divide2.common.service

import com.divide2.common.model.Tip
import com.divide2.common.repository.TipRepository
import com.divide2.core.service.IService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

interface TipService : IService<Tip, Int, TipRepository> {


}