package app.repository

abstract class IRepository<T> {
    protected val values: HashSet<T> = hashSetOf()

    fun add(value: T) {
        values.add(value)
    }

    fun remove(value: T) {
        values.remove(value)
    }
}