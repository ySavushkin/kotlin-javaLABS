//Створіть функцію, яка приймає список слів і повертає список слів, які
//містять лише голосні букви

val words = listOf("aioiaoioi", "hello", "aEa", "hgbcv", "o", "aaaaaa")

//В цій лабораторній я зробив дві функції, одну звичайну, в яку ми кладемо List<String>, і на виході ми отримуємо
//цей же список тільки зафільтрований через предікат, за таким : ^[aeiouAEIOU]+$ регулярним виразом

//Друга функція розширення для нашого списка, а в аргументах ми кладемо вже предікат

//Результати виходять однакові, але в функції onlyVowels ми наш ліст змінюємо, а в myFilter ні, бо ми тільки перевіряємо
//предікатом умову для додавання нового айтему в ліст result

fun onlyVowels(list: List<String>): List<String> {
    return list.filter { s -> s.matches("^[aeiouAEIOU]+$".toRegex()) }
}

fun List<String>.myFilter(predicate: (String) -> Boolean): List<String> {
    val result = mutableListOf<String>();
    for (item in this) {
        if (predicate(item)) {
            result.add(item)
        }
    }
    return result
}

fun main(){
    println(words.myFilter { s -> s.matches("^[aeiouAEIOU]+$".toRegex()) });

    println(onlyVowels(words));
}