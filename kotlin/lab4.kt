//Створіть функцію, яка приймає список імен (List<String?>) та виводить
//унікальні не-null імена, відсортовані за алфавітом.

//Тут я вирішив скористатися аналогом Stream API в котліні.


// Моя функія приймає список String з "нулями",
fun printUniqueNames(names: List<String?>) {
    names.filterNotNull() //фільтрує нулі
        .distinct() //видаляє однакові імена, тобто робить весь список унікальним
        .sorted() //сортує за naturalOrder
        .forEach { println(it) } //виводить кожне ім'я
}

fun main() {
    val names = listOf("asd", "qwe", "zxc", null, "rty", null)
    printUniqueNames(names)
}
