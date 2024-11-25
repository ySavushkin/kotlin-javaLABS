import java.time.LocalDateTime

//Система управління складом
//Створити інтерфейс StorageItem. Реалізувати класи RawMaterial, Product,
//Equipment. Додати систему інвентаризації.

interface StorageItem {
    val id: String
    val name: String

    fun getDescription(): String
    fun checkStock(): Boolean
    fun calculateValue(): Double
}

abstract class BaseStorageItem(
    override val id: String,
    override val name: String
) : StorageItem {
    var quantity: Int = 0
    var unitPrice: Double = 0.0

    override fun checkStock(): Boolean = quantity > 0
    override fun calculateValue(): Double = quantity * unitPrice
}

class RawMaterial(id: String, name: String, val supplier: String) : BaseStorageItem(id, name) {
    override fun getDescription(): String = "Raw Material: $name from $supplier"
}

class Product(id: String, name: String, val category: String) : BaseStorageItem(id, name) {
    override fun getDescription(): String = "Product: $name in category $category"
}

class Equipment(id: String, name: String, val manufacturer: String) : BaseStorageItem(id, name) {
    override fun getDescription(): String = "Equipment: $name by $manufacturer"
}

fun validateItem(item: StorageItem) {
    if (item.name.isBlank()) throw IllegalArgumentException("Name cannot be blank")
}

fun logOperation(operation: String) {
    println("Log: $operation at ${LocalDateTime.now()}")
}

fun main() {
    val rawMaterial = RawMaterial("RM001", "", "SteelCo")
    rawMaterial.quantity = 100
    rawMaterial.unitPrice = 15.0

    val product = Product("P001", "Laptop", "Electronics")
    product.quantity = 50
    product.unitPrice = 800.0

    val product2 = Product("P002", "Phone", "Electronics")
    product2.quantity = 0
    product2.unitPrice = 400.0

    val equipment = Equipment("E001", "Forklift", "LiftCo")
    equipment.quantity = 5
    equipment.unitPrice = 15000.0

    try {
        validateItem(rawMaterial)
        logOperation("Added Raw Material: ${rawMaterial.getDescription()} with value ${rawMaterial.calculateValue()}")
    } catch (e: Exception) {
        println("Error: ${e.message}")
    }

    try {
        validateItem(product2)
        logOperation("Availability of a second product: ${product2.checkStock()}")
    } catch (e: Exception) {
        println("Error: ${e.message}")
    }

    try {
        validateItem(product)
        logOperation("Added Product: ${product.getDescription()} with value ${product.calculateValue()}")
    } catch (e: Exception) {
        println("Error: ${e.message}")
    }

    try {
        validateItem(equipment)
        logOperation("Added Equipment: ${equipment.getDescription()} with value ${equipment.calculateValue()}")
    } catch (e: Exception) {
        println("Error: ${e.message}")
    }
}
