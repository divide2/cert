package com.divide2.common.repository

import com.divide2.common.model.Tip
import org.springframework.data.jpa.repository.JpaRepository

interface TipRepository : JpaRepository<Tip, Int> {

}