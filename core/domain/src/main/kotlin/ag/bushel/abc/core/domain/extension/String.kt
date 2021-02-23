package ag.bushel.abc.core.domain.extension

import java.util.*

fun String.replace(oldValue: String, newValue: UUID): String = this.replace(oldValue, newValue.toString())
