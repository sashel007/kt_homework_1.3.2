fun main() {

    month.set(1,500)
    discount(1,"Mastercard", 40)
    discount(1,"Maestro", 23333)
    discount(1,"Visa", 414)
//    discount(2,"Мир", 1233312)
//    discount(3,"VK Pay", 403)
//    discount(1,"Maestro", 23933)

}

const val MAX_DAY_LIMIT = 150_000
const val MAX_MONTH_LIMIT = 600_000

// массив из 30 дней, куда вносится сумма перевода за определенные дни
var month = Array(30,) { 0 }


fun discount(day: Int, cardType: String, currentSum: Int) {

    //проверим, не превышен ли лимит за месяц
    if ((currentSum + checkMonthSum()) <= MAX_MONTH_LIMIT) {
        //если не превышен, то проверим, не превышен ли лимит за указанный день
        if ((currentSum + month[day]) <= MAX_DAY_LIMIT) {
            //вложим сумму перевода в ячейку массива, отвечающую за сумму всех транзакций в течение указанного дня
            month[day] = month[day] + currentSum
            //рассчитаем скидку
            when (cardType) {
                "Mastercard" -> if (currentSum in 300..75000) "$currentSum" else "Сумма со скидкой" +
                        "составляет: ${currentSum * 0.94 + 20}"
                "Maestro" -> if (currentSum in 300..75000) "$currentSum" else "Сумма со скидкой" +
                        "составляет: ${currentSum * 0.94 + 20}"
                "Visa" -> if (currentSum >= 35) {
                                "Сумма со скидкой составляет: ${currentSum * 0.925}"
                            } else {
                                currentSum == 0
                                println("Минимальная сумма должна составлять 35 рублей")
                            }
                "Мир" -> if (currentSum >= 35) {
                    "Сумма со скидкой составляет: ${currentSum * 0.925}"
                    } else {
                        currentSum == 0
                        println("Минимальная сумма должна составлять 35 рублей")
                    }
                "VK Pay" -> "$currentSum"
                else -> "Неверно указана платежная система"
            }
        } else {
            print("Лимит за день превышен")
        }
    } else {
        print("Лимит за месяц превышен превышен")
    }
}

// метод, проверяющий какая сумма была уже переведена за месяц
fun checkMonthSum(): Int {
    return month.sum()
}

