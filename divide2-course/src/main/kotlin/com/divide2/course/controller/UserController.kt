package com.divide2.course.controller

import com.divide2.core.er.Loginer
import com.divide2.core.token.AccessToken
import com.divide2.core.token.StoreUser
import com.divide2.core.token.TokenStore
import com.divide2.course.dto.JoinDTO
import com.divide2.course.model.User
import com.divide2.course.query.CourseQuery
import com.divide2.course.service.CourseService
import com.divide2.course.service.UserService
import com.divide2.course.vo.CourseVO
import com.divide2.sys.model.Favorite
import com.divide2.sys.model.Org
import com.divide2.sys.service.FavoriteService
import com.divide2.sys.service.OrgService
import com.fasterxml.jackson.annotation.JsonView
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import javax.validation.Valid

/**
 * @author bvvy
 * @date 2019/10/1
 */
@Api(tags = ["普通用户"])
@RestController
@RequestMapping("/v1/user")
class UserController(
        var tokenStore: TokenStore,
        var userService: UserService,
        var courseService: CourseService,
        val favoriteService: FavoriteService,
        val orgService: OrgService

) {
    @GetMapping("/joined")
    @ApiOperation("获取所有报名的课程")
    fun findJoined(pageable: Pageable, courseQuery: CourseQuery): ResponseEntity<Page<CourseVO>> {
        val courses = courseService.findJoined(Loginer.getId(), pageable, courseQuery)
        return ResponseEntity.ok(courses)
    }

    @ApiOperation("报名课程")
    @PostMapping("/join/{courseId}")
    fun join(@PathVariable courseId: Int): ResponseEntity<Any> {
        val join = JoinDTO(courseId, Loginer.getId())
        courseService.join(join)
        return ResponseEntity.created(URI("")).build()
    }

    @ApiOperation("个人信息")
    @GetMapping
    @JsonView(User.UserVO::class)
    fun getUserInfo(): ResponseEntity<User> {
        val favorites = favoriteService.findByUser(Loginer.getId())
        val user = userService.getById(Loginer.getId())
        user.favorite = favorites.size
        return ResponseEntity.ok(user)
    }

    @ApiOperation("保存微信信息")
    @PostMapping("/wechat")
    fun saveLoginUser(@RequestBody @Valid loginUserDTO: LoginUserDTO): ResponseEntity<AccessToken> {
        val user = User(
                nickname = loginUserDTO.nickName,
                avatar = loginUserDTO.avatarUrl,
                city = loginUserDTO.city,
                province = loginUserDTO.province,
                gender = loginUserDTO.gender,
                language = loginUserDTO.language,
                phoneNumber = loginUserDTO.phoneNumber
        )
        userService.add(user)
        val accessToken = tokenStore.retrieveToken(StoreUser(user.id))
        return ResponseEntity.ok(accessToken)
    }

    @GetMapping("/favorites")
    @JsonView(Org.UserOrgVO::class)
    fun getFavorites(): ResponseEntity<List<Favorite>> {
        val favorites = favoriteService.findByUser(Loginer.getId())
        return ResponseEntity.ok(favorites)
    }

    @PostMapping("/toggle/favorites/org/{orgId}")
    fun addFavorites(@PathVariable orgId: Int): ResponseEntity<Any> {
        favoriteService.toggle(Loginer.getId(), orgId)
        return ResponseEntity.created(URI("")).build<Any>()
    }

    @GetMapping("/org/{orgId}")
    @JsonView(Org.UserOrgVO::class)
    fun getUserOrg(@PathVariable orgId: Int): ResponseEntity<Org> {
        val org= orgService.getById(orgId)
        val favorite = favoriteService.findByUserAndOrg(Loginer.getId(), orgId)
        org.isFavorite = favorite != null
        return ResponseEntity.ok(org)

    }

}

