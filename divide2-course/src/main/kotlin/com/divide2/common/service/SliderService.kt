package com.divide2.common.service

import com.divide2.common.model.Slider
import com.divide2.common.repository.SliderRepository
import com.divide2.core.service.IService

interface SliderService : IService<Slider, Int, SliderRepository> {
}