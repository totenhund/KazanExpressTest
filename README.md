# KazanExpressTest


### Short description

I implemented assignment based on Clean Architecture and MVVM pattern, Data binding. 
Requests are implemented with Retrofit2 library

### Project structure 

1. data/network - abstrack definition of data and API module
2. domain/interactors - actions that user triggers (requests)
3. repositories - interfaces that provide methods for accessing data
4. ui - fragments with ViewModels
5. util - base class extensions and data formatting

### Installation

git clone https://github.com/totenhund/KazanExpressTest.git


### Requests to AMock: Endpoints

1. History Operations: GET http://www.amock.io/api/totenhund/histories
2. Wallets: GET http://www.amock.io/api/totenhund/wallets

To change API go to data/network/Common.kt file and replace ```BASE_URL``` by your API

```
object Common {
    private const val BASE_URL = "YOUR_API"
    val apiModule: BalanceApi?
        get() = ApiModule.getClient(BASE_URL)?.create(BalanceApi::class.java)
}
```

Endpoints are located in data/network/api/BalanceApi.kt

```
interface BalanceApi {

    @GET("wallets")
    fun getWallets(): Call<WalletResult>

    @GET("histories")
    fun getHistory(): Call<HistoryResult>

}
```
