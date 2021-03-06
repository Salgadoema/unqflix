package support

import domain.Id
import domain.Content
import domain.ExistsException
import domain.NotFoundException

fun <T : Id> getById(list: MutableCollection<T>, id: String): T =
    list.find { it.id == id } ?: throw NotFoundException("Unknown", "id", id)

fun <T : Id> addToList(item: T, items: MutableList<T>): Boolean {
    val none = items.none { it.idValue() == item.idValue() }
    return if (none) items.add(item) else throw ExistsException(item)
}

fun <T : Id> itemFromList(idSearched: String, items: MutableList<T>, name: String): T {
    return items.find { it.id == idSearched }
        ?: run { throw NotFoundException(name, "id", idSearched) }
}

fun <T : Content> searchIn(text: String, items: MutableList<T>): MutableList<T> {
    return items.filter { it.title.contains(text, true) }.toMutableList()
}
