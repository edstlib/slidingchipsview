package id.co.edtslib.slidingchipsview.example

data class Name(
    val short: String
) {
    override fun toString(): String {
        return short
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (other is Name) {
            return short == other.short
        }

        return false
    }
}
