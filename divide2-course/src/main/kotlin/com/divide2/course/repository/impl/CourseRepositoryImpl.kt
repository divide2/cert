package com.divide2.course.repository.impl

import com.aliyuncs.utils.StringUtils
import com.divide2.course.model.PUBLISHED
import com.divide2.course.query.CourseQuery
import com.divide2.course.repository.custom.CourseRepositoryCustom
import com.divide2.course.vo.CourseVO
import com.divide2.course.vo.UserVO
import com.divide2.jooq.Tables.*
import org.jooq.DSLContext
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import java.math.BigDecimal
import javax.persistence.EntityManager

class CourseRepositoryImpl(
        var entityManager: EntityManager,
        var create: DSLContext
) : CourseRepositoryCustom {
    private val c = C_COURSE
    private val cc = C_COURSE_CERTIFICATE
    private val cf = C_CERTIFICATE
    private val p = C_PROFESSION
    private val uc = C_USER_COURSE
    private val u = SYS_USER
    private val o = SYS_ORG
    private val a = SYS_ADDRESS
    override fun findByOrgId(orgId: Int, pageable: Pageable, courseQuery: CourseQuery): Page<CourseVO> {

        val sql = create.select(c.ID, c.NAME, c.START_TIME, c.END_TIME, c.DESCRIPTION, c.ENROLMENT,c.STATUS, c.CAPACITY, c.AUDIT_MESSAGE, a.PROVINCE, a.ADDRESS, a.CITY, a.DISTRICT,
                p.ID.`as`("professionId"), p.NAME.`as`("professionName"), a.LONGITUDE, a.DETAIL.`as`("addressDetail"),
                a.LATITUDE, c.ARRANGEMENT, c.PRICE, c.IMAGES, c.DETAILS, cf.ID.`as`("certificateId"),
                cf.NAME.`as`("certificateName"), cf.LICENSOR.`as`("certificateLicensor"),
                cf.IMAGE.`as`("certificateImage"), c.ORG_ID, o.NAME.`as`("orgName"))
                .from(c).leftJoin(cc).on(c.ID.eq(cc.COURSE_ID))
                .leftJoin(cf).on(cf.ID.eq(cc.CERTIFICATE_ID))
                .leftJoin(p).on(p.ID.eq(c.PROFESSION_ID))
                .leftJoin(a).on(a.ID.eq(c.ADDRESS_ID))
                .leftJoin(o).on(o.ID.eq(c.ORG_ID))
        val total = create.fetchCount(sql)
        val query = sql.where(c.ORG_ID.eq(orgId))
                .and(c.STATUS.`in`(courseQuery.status!!.split(",")))
                .orderBy(c.ID.desc())
                .limit(pageable.offset.toInt(), pageable.pageSize)
        val resultList = query.fetchInto(CourseVO::class.java)
        return PageImpl(resultList, pageable, total.toLong())
    }

    override fun findJoined(userId: Int, pageable: Pageable, courseQuery: CourseQuery): Page<CourseVO> {
        val sql = create.select(c.ID, c.NAME, c.START_TIME, c.END_TIME, c.DESCRIPTION, c.ENROLMENT, c.CAPACITY, c.AUDIT_MESSAGE, a.PROVINCE, a.ADDRESS, a.CITY, a.DISTRICT,
                p.ID.`as`("professionId"), p.NAME.`as`("professionName"), a.LONGITUDE, a.DETAIL.`as`("addressDetail"),
                a.LATITUDE, c.ARRANGEMENT, c.PRICE, c.IMAGES, c.DETAILS, cf.ID.`as`("certificateId"),
                cf.NAME.`as`("certificateName"), cf.LICENSOR.`as`("certificateLicensor"),
                cf.IMAGE.`as`("certificateImage"), c.ORG_ID, o.NAME.`as`("orgName"))
                .from(c).leftJoin(cc).on(c.ID.eq(cc.COURSE_ID))
                .leftJoin(cf).on(cf.ID.eq(cc.CERTIFICATE_ID))
                .leftJoin(p).on(p.ID.eq(c.PROFESSION_ID))
                .leftJoin(uc).on(uc.COURSE_ID.eq(c.ID))
                .leftJoin(a).on(a.ID.eq(c.ADDRESS_ID))
                .leftJoin(o).on(o.ID.eq(c.ORG_ID))
        val total = create.fetchCount(sql)
        val where = sql.where(uc.USER_ID.eq(userId))
        if (courseQuery.status != null && courseQuery.status != "") {
            where.and(c.STATUS.eq(courseQuery.status))
        }
        val query = where.orderBy(uc.ID.desc()).limit(pageable.offset.toInt(), pageable.pageSize)
        val resultList = query.fetchInto(CourseVO::class.java)
        return PageImpl(resultList, pageable, total.toLong())
    }

    override fun get(id: Int): CourseVO? {
        val sql = create.select(c.ID, c.NAME, c.START_TIME, c.END_TIME, c.DESCRIPTION, c.STATUS, c.ENROLMENT, c.CAPACITY, c.AUDIT_MESSAGE, a.PROVINCE, a.CITY, a.DISTRICT,
                p.ID.`as`("professionId"), p.NAME.`as`("professionName"), a.LONGITUDE, a.ADDRESS,a.ID.`as`("addressId"), a.DETAIL.`as`("addressDetail"),
                a.LATITUDE, c.ARRANGEMENT, c.PRICE, c.IMAGES, c.DETAILS, cf.ID.`as`("certificateId"),
                cf.NAME.`as`("certificateName"), cf.LICENSOR.`as`("certificateLicensor"),
                cf.IMAGE.`as`("certificateImage"), c.ORG_ID, o.NAME.`as`("orgName"))
                .from(c).leftJoin(cc).on(c.ID.eq(cc.COURSE_ID))
                .leftJoin(cf).on(cf.ID.eq(cc.CERTIFICATE_ID))
                .leftJoin(p).on(p.ID.eq(c.PROFESSION_ID))
                .leftJoin(a).on(a.ID.eq(c.ADDRESS_ID))
                .leftJoin(o).on(o.ID.eq(c.ORG_ID))
        val query = sql.where(c.ID.eq(id))
        return query.fetchSingleInto(CourseVO::class.java)
    }

    override fun listCourseUsers(courseId: Int): List<UserVO> {
        return create.select(u.NICKNAME, u.AVATAR, u.ID, u.GENDER)
                .from(uc)
                .innerJoin(u).on(uc.USER_ID.eq(u.ID))
                .where(uc.COURSE_ID.eq(courseId)).orderBy(uc.ID).fetchInto(UserVO::class.java)
    }

    override fun findAll(pageable: Pageable, courseQuery: CourseQuery): Page<CourseVO> {
        val table = create.select(c.ID, c.NAME, c.START_TIME, c.END_TIME, c.DESCRIPTION, c.ENROLMENT, c.CAPACITY, c.AUDIT_MESSAGE, c.STATUS,a.PROVINCE, a.CITY, a.DISTRICT,
                p.ID.`as`("professionId"), p.NAME.`as`("professionName"), a.LONGITUDE, a.ADDRESS,
                a.DETAIL.`as`("addressDetail"),
                a.LATITUDE, c.ARRANGEMENT, c.PRICE, c.IMAGES, c.DETAILS, cf.ID.`as`("certificateId"),
                cf.NAME.`as`("certificateName"), cf.LICENSOR.`as`("certificateLicensor"),
                cf.IMAGE.`as`("certificateImage"), c.ORG_ID, o.NAME.`as`("orgName"))
                .from(c).leftJoin(cc).on(c.ID.eq(cc.COURSE_ID))
                .leftJoin(cf).on(cf.ID.eq(cc.CERTIFICATE_ID))
                .leftJoin(p).on(p.ID.eq(c.PROFESSION_ID))
                .leftJoin(a).on(a.ID.eq(c.ADDRESS_ID))
                .leftJoin(o).on(o.ID.eq(c.ORG_ID))
                .where(c.STATUS.eq(PUBLISHED))
        if (courseQuery.city != null && StringUtils.isNotEmpty(courseQuery.city)) {
            table.and(a.CITY.like(courseQuery.city + "%"))
        }
        if (courseQuery.free != null && courseQuery.free == true) {
            table.and(c.PRICE.eq(BigDecimal.ZERO))
        }
        if (courseQuery.free != null && courseQuery.free == false) {
            table.and(c.PRICE.ne(BigDecimal.ZERO))
        }
        if (courseQuery.hasCert != null) {
            table.and(c.HAS_CERT.eq(courseQuery.hasCert))
        }
        if (courseQuery.professionId != null) {
            table.and(c.PROFESSION_ID.eq(courseQuery.professionId))
        }
        if (StringUtils.isNotEmpty(courseQuery.name)) {
            table.and(c.NAME.like("%" + courseQuery.name + "%"))
        }
        val total = create.fetchCount(table)
        val content = table.orderBy(c.ID.desc())
                .limit(pageable.offset.toInt(), pageable.pageSize)
                .fetchInto(CourseVO::class.java)
        return PageImpl(content, pageable, total.toLong())
    }
}
