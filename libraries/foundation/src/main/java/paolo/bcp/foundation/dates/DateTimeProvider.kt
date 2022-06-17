package paolo.bcp.foundation.dates


interface DateTimeProvider<T> {
    fun now(): T
}
