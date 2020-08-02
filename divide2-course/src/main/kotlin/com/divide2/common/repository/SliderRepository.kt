package com.divide2.common.repository

import com.divide2.common.model.Slider
import org.springframework.data.jpa.repository.JpaRepository

interface SliderRepository : JpaRepository<Slider, Int> {

}