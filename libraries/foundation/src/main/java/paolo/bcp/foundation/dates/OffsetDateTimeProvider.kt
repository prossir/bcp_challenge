package paolo.bcp.foundation.dates

import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneOffset
import javax.inject.Inject


class OffsetDateTimeProvider @Inject constructor() : DateTimeProvider<OffsetDateTime> {
    override fun now(): OffsetDateTime = OffsetDateTime.now(ZoneOffset.systemDefault())
}