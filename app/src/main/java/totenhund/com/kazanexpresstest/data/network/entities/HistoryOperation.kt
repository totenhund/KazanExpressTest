package totenhund.com.kazanexpresstest.data.network.entities

data class HistoryOperation(
    val entry: String,
    val amount: String,
    val balance: String,
    val currency: String,
    val sender: String,
    val recipient: String
)