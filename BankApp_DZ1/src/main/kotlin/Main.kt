data class Account(val number: Int, var name: String, var balance: Double)

class Bank {
    private val accounts = mutableListOf<Account>()
    private var accountCounter = 1

    fun printMenu() {
        println("1. Просмотр списка счетов")
        println("2. Открытие счета")
        println("3. Пополнение счета")
        println("4. Перевод денег между счетами")
        println("5. Выход")
    }

    fun printAccounts() {
        if (accounts.isEmpty()) {
            println("Нет открытых счетов.")
        } else {
            println("Список счетов:")
            for (account in accounts) {
                println("${account.number}. ${account.name} - Баланс: ${account.balance}")
            }
        }
    }

    fun openAccount() {
        print("Введите название счета: ")
        val accountName = readLine()
        if (accountName != null) {
            if (!accountName.isEmpty()) {
                accounts.add(Account(accountCounter++, accountName, 0.0))
                println("Счет открыт успешно.")
            } else {
                println("Некорректное название счета");
            }
        }
    }

    fun deposit() {
        printAccounts()
        print("Введите номер счета для пополнения: ")
        val accountNumber = readlnOrNull()?.toIntOrNull()

        if (accountNumber != null && accountNumber > 0 && accountNumber <= accounts.size) {
            print("Введите сумму пополнения: ")
            val amount = readLine()?.toDoubleOrNull()

            if (amount != null && amount > 0) {
                val account = accounts[accountNumber - 1]
                account.balance += amount
                println("Счет успешно пополнен. Новый баланс: ${account.balance}")
            } else {
                println("Некорректная сумма пополнения.")
            }
        } else {
            println("Некорректный номер счета.")
        }
    }

    fun transfer() {
        printAccounts()
        print("Введите номер счета, с которого хотите перевести деньги: ")
        val fromAccountNumber = readLine()?.toIntOrNull()

        if (fromAccountNumber != null && fromAccountNumber > 0 && fromAccountNumber <= accounts.size) {
            print("Введите номер счета, на который хотите перевести деньги: ")
            val toAccountNumber = readLine()?.toIntOrNull()

            if (toAccountNumber != null && toAccountNumber > 0 && toAccountNumber <= accounts.size) {
                print("Введите сумму перевода: ")
                val amount = readLine()?.toDoubleOrNull()

                if (amount != null && amount > 0 && amount <= accounts[fromAccountNumber - 1].balance) {
                    val fromAccount = accounts[fromAccountNumber - 1]
                    val toAccount = accounts[toAccountNumber - 1]

                    fromAccount.balance -= amount
                    toAccount.balance += amount

                    println("Перевод успешно выполнен.")
                } else {
                    println("Некорректная сумма перевода.")
                }
            } else {
                println("Некорректный номер счета, на который хотите перевести деньги.")
            }
        } else {
            println("Некорректный номер счета, с которого хотите перевести деньги.")
        }
    }
}

fun main() {
    val bank = Bank()

    while (true) {
        bank.printMenu()
        print("Выберите опцию: ")
        when (readLine()) {
            "1" -> bank.printAccounts()
            "2" -> bank.openAccount()
            "3" -> bank.deposit()
            "4" -> bank.transfer()
            "5" -> {
                println("Выход.")
                return
            }
            else -> println("Некорректный ввод.")
        }
    }
}
