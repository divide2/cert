package com.divide2.course.controller

import com.divide2.core.er.Loginer
import com.divide2.course.model.User
import com.divide2.course.service.CertificateService
import com.divide2.course.service.CourseService
import com.divide2.course.service.UserService
import com.divide2.course.vo.CourseVO
import com.divide2.course.vo.OrgVO
import com.divide2.sys.model.Favorite
import com.divide2.sys.model.Org
import com.divide2.sys.service.FavoriteService
import com.divide2.sys.service.OrgService
import com.fasterxml.jackson.annotation.JsonView
import io.swagger.annotations.ApiOperation
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity
import javax.validation.Valid

@RestController
@RequestMapping("/v1/org")
class OrgController(
        var courseService: CourseService,
        var orgService: OrgService,
        val favoriteService: FavoriteService
) {


    @PutMapping
    @ApiOperation("修改机构的信息")
    fun update(@RequestBody @Valid orgDTO: OrgDTO):ResponseEntity<Any> {
        val org = orgService.getById(Loginer.getId())
        org.avatar = orgDTO.avatar
        org.address = orgDTO.address
        org.contactUser = orgDTO.contactUser
        org.contactWay = orgDTO.contactWay
        org.license = orgDTO.license
        org.name = orgDTO.name
        orgService.update(org)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/{id}/courses")
    fun findOrgCourses(@PathVariable id: Int,pageable: Pageable): ResponseEntity<Page<CourseVO>> {
        val courses = courseService.findByOrg(id, pageable)
        return ResponseEntity.ok(courses);
    }

    @GetMapping
    @JsonView(Org.OrgInfo::class)
    fun get(): ResponseEntity<Org> {
        val favorites = favoriteService.findByOrg(Loginer.getId())
        val org = orgService.getById(Loginer.getId())
        org.fans = favorites.size
        return ResponseEntity.ok(org)
    }
    @GetMapping("/{id}")
    fun get(@PathVariable id: Int): ResponseEntity<OrgVO> {

        val org = orgService.getById(id)

        val orgVO = OrgVO(
                id = org.id,
                name = org.name,
                avatar = org.avatar
        )
        return ResponseEntity.ok(orgVO)
    }

    @GetMapping("/fans")
    fun getFavorites(): ResponseEntity<List<Favorite>> {
        val favorites = favoriteService.findByOrg(Loginer.getId())
        return ResponseEntity.ok(favorites)
    }

}

class OrgDTO(
        var id: Int,
        var name: String,
        var avatar: String,
        var email: String,
        var license: String,
        var contactUser: String,
        var contactWay: String,
        var address: String
)
