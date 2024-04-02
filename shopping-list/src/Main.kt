import java.util.Scanner

open class Item(val name: String, val price: Double)

class Food(name: String, price: Double, val weight: String) : Item(name, price)

class Cloth(name: String, price: Double, val type: String) : Item(name, price)

class ShoppingList {
    private val items = mutableListOf<Item>()

    fun addItem(item: Item) {
        items.add(item)
        println("Ürün başarıyla eklendi.")
    }

    fun displayItems() {
        if (items.isEmpty()) {
            println("Alışveriş listeniz boş.")
        } else {
            println("Alışveriş Listeniz:")
            items.forEachIndexed { index, item ->
                println("${index + 1}. Name: ${item.name}, Price: ${item.price}")
                when (item) {
                    is Food -> println("   Weight: ${item.weight}")
                    is Cloth -> println("   Type: ${item.type}")
                }
            }
        }
    }

    fun deleteItem(index: Int) {
        if (index in 1..items.size) {
            items.removeAt(index - 1)
            println("Ürün başarıyla silindi.")
        } else {
            println("Geçersiz bir seçim yaptınız.")
        }
    }
}

fun main() {
    val shoppingList = ShoppingList()
    val scanner = Scanner(System.`in`)

    while (true) {
        println("\nMenü:")
        println("1) Add Item")
        println("2) Display Item")
        println("3) Delete Item")
        println("4) Exit")

        print("Yapmak istediğiniz işlemi seçiniz: ")
        val choice = scanner.nextInt()

        when (choice) {
            1 -> {
                println("Ürün tipini seçiniz (1: Food, 2: Cloth): ")
                val itemType = scanner.nextInt()

                print("Ürünün adını giriniz: ")
                val name = scanner.next()

                print("Ürünün fiyatını giriniz: ")
                val price = scanner.nextDouble()

                when (itemType) {
                    1 -> {
                        while (true) {
                            print("Ürünün kilosunu giriniz: ")
                            val weight = scanner.next()

                            if (weight.matches("-?\\d*(\\.\\d+)?".toRegex())) {
                                shoppingList.addItem(Food(name, price, weight))
                                break
                            }

                            println("Kilo değeri sayısal olarak girilmelidir.")
                        }
                    }

                    2 -> {
                        while (true) {
                            print("Ürünün tipini giriniz: ")
                            val type = scanner.next()

                            if (type.isNotBlank()) {
                                shoppingList.addItem(Cloth(name, price, type))
                                break
                            }

                            println("Ürünün tipi boş geçilemez.")
                        }
                    }

                    else -> println("Geçersiz bir seçim yaptınız. Lütfen tekrar deneyiniz.")
                }
            }

            2 -> shoppingList.displayItems()

            3 -> {
                print("Silmek istediğiniz ürünün numarasını giriniz: ")
                val index = scanner.nextInt()
                shoppingList.deleteItem(index)
            }

            4 -> {
                println("Programdan çıkılıyor...")
                scanner.close()
                return
            }

            else -> println("Geçersiz bir seçim yaptınız. Lütfen tekrar deneyiniz.")
        }
    }
}
