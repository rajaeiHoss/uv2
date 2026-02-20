package org.aspectj.lang.reflect

interface PointcutBasedPerClause : PerClause {
    fun getPointcutExpression(): PointcutExpression
}
