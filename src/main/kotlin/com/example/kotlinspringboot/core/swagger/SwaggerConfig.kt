package com.example.kotlinspringboot.core.swagger


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {
    companion object {
        private val CONTACT = Contact("Jay Ahn", "https://github.com/JayAhn2", "jay.ahn0423@gmail.com")
        val API_INFO = ApiInfo(
            "swagger",
            "Swagger Documentation",
            "1.0",
            "urn:tos",
            CONTACT,
            "Apache 2.0",
            "https://www.apache.org/licenses/LICENSE-2.0",
            arrayListOf()
        )
    }

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .apiInfo(API_INFO)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.example.kotlinspringboot"))
            .build()
    }
}
