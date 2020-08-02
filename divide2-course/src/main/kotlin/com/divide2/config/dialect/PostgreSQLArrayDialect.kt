package com.divide2.config.dialect

import com.vladmihalcea.hibernate.type.array.StringArrayType
import org.hibernate.dialect.PostgreSQL95Dialect
import java.sql.Types


open class PostgreSQLArrayDialect() : PostgreSQL95Dialect() {
    init {
        this.registerHibernateType(
                Types.ARRAY, StringArrayType.INSTANCE.name
        )
    }

}