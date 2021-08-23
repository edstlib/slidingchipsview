package id.co.edtslib.slidingchipsview

interface SlidingChipsDelegate<T> {
    fun onSelected(item: T, position: Int)
}