package com.divide2.config

import org.jooq.codegen.GenerationTool
import org.springframework.boot.CommandLineRunner
import org.springframework.core.io.ClassPathResource

/**
 * @author hangye
 * @date 2019/11/4
 */
//@Component
class Generator : CommandLineRunner {
    override fun run(vararg args: String?) {
        val resource = ClassPathResource("jooq.xml")
        try {
            GenerationTool.generate(GenerationTool.load(resource.inputStream))
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}

//fun main(args: Array<String>) {
//    Generator().run()
//}