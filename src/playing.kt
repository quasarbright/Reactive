import java.lang.IllegalStateException

sealed class Expr

data class PlusExpr(val left : Expr, val right : Expr) : Expr()

data class MinusExpr(val left : Expr, val right : Expr) : Expr()

data class TimesExpr(val left : Expr, val right : Expr) : Expr()

data class DivideExpr(val left : Expr, val right : Expr) : Expr()

data class NegateExpr(val child : Expr) : Expr()

data class VarExpr(val name : String) : Expr()

sealed class Value : Expr()

data class IntVal(val value : Int) : Value()

data class DoubleVal(val value : Double) : Value()

data class ErrorVal(val msg : String) : Value()

fun numToValue(n : Number) : Value {
    return when (n) {
        is Double -> DoubleVal(n)
        is Int -> IntVal(n)
        else -> throw IllegalStateException()
    }
}

fun valueToNumber(v : Value) : Number {
    return when(v) {
        is IntVal -> v.value
        is DoubleVal -> v.value
        is ErrorVal -> throw IllegalStateException()
    }
}

fun eval(e : Expr, env : (String) -> Value) : Value {
    fun rec(e : Expr) : Value = eval(e, env)
    return when(e) {
        is PlusExpr -> {
            val left = rec(e.left)
            val right = rec(e.right)
            when(left) {
                is DoubleVal -> when (right) {
                    is DoubleVal -> DoubleVal(left.value + right.value)
                    is IntVal -> DoubleVal(left.value + right.value)
                    is ErrorVal -> right
                }
                is IntVal -> when (right) {
                    is DoubleVal -> DoubleVal(left.value + right.value)
                    is IntVal -> IntVal(left.value + right.value)
                    is ErrorVal -> right
                }
                is ErrorVal -> left
            }
        }
        is MinusExpr -> {
            val left = rec(e.left)
            val right = rec(e.right)
            when(left) {
                is DoubleVal -> when (right) {
                    is DoubleVal -> DoubleVal(left.value - right.value)
                    is IntVal -> DoubleVal(left.value - right.value)
                    is ErrorVal -> right
                }
                is IntVal -> when (right) {
                    is DoubleVal -> DoubleVal(left.value - right.value)
                    is IntVal -> IntVal(left.value - right.value)
                    is ErrorVal -> right
                }
                is ErrorVal -> left
            }
        }
        is TimesExpr -> {
            val left = rec(e.left)
            val right = rec(e.right)
            when(left) {
                is DoubleVal -> when (right) {
                    is DoubleVal -> DoubleVal(left.value * right.value)
                    is IntVal -> DoubleVal(left.value * right.value)
                    is ErrorVal -> right
                }
                is IntVal -> when (right) {
                    is DoubleVal -> DoubleVal(left.value * right.value)
                    is IntVal -> IntVal(left.value * right.value)
                    is ErrorVal -> right
                }
                is ErrorVal -> left
            }
        }
        is DivideExpr -> {
            val left = rec(e.left)
            val right = rec(e.right)
            when(left) {
                is DoubleVal -> when (right) {
                    is DoubleVal -> DoubleVal(left.value / right.value)
                    is IntVal -> DoubleVal(left.value / right.value)
                    is ErrorVal -> right
                }
                is IntVal -> when (right) {
                    is DoubleVal -> DoubleVal(left.value / right.value)
                    is IntVal -> IntVal(left.value / right.value)
                    is ErrorVal -> right
                }
                is ErrorVal -> left
            }
        }
        is NegateExpr -> {
            when (val child = rec(e.child)) {
                is DoubleVal -> DoubleVal(-child.value)
                is IntVal -> IntVal(-child.value)
                is ErrorVal -> child
            }
        }
        is VarExpr -> env(e.name)
        is IntVal -> e
        is DoubleVal -> e
        is ErrorVal -> e
    }
}

fun main() {
    val e = PlusExpr(IntVal(1), VarExpr("x"))
    println(e)
    println(eval(e) { _ -> IntVal(2) })
}