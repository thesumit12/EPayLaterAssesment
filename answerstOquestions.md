1.	How long did you spend on the coding test? What would you add to your solution if you spent more time on it? If you didn't spend much time on the coding test then use this as an opportunity to explain what you would add.
I have spent around 8 hours on the coding test. I would have implemented following things if I would have spent more time on it: -
•	Wrote unit test cases, UI test cases
•	Used dependency Injection
•	Used recycler view animation and ripple effect animation, more intuitive UI
•	Used Design patterns used for on demand cases
•	Used configurations files, variants to make code more scalable

2.	What was the most useful feature that was added to the latest version of your chosen language? Please include a snippet of code that shows how you've used it.
I have used kotlin features with latest architecture components like live data, viewModel
class MainViewModel(context: Application) : BaseViewModel(context) {

    val balance = ObservableField<String>()
    val waitingDialogMsg = MutableLiveData<String>()
    var isNoData = ObservableBoolean(false)
    val transactionList = MutableLiveData<List<Transaction>>()

    init {
        waitingDialogMsg.value = "Getting Balance"
        addDisposable(getApi().getBalance(AppConstants.BALANCE_NAME, AppConstants.COLLECTION_ID)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                waitingDialogMsg.value = null
                balance.set(it?.balance+" "+it.currency)
                loadTransactions()
            },{
                waitingDialogMsg.value = null
                navigation.navigateTo(Navigation(Navigation.Screen.Error).addParam(Navigation.Param.Error, it.message))
            }))

    }

3.	What is your favourite framework / library / package that you love but couldn't use in the task? What do you like about it so much?
Since it was a short exercise I couldn’t use – Room Database, Picasso, Dagger etc

4.	What great new thing you learnt about in the past year and what are you looking forward to learn more about over the next year?
I have learnt Kotlin, MVVM and design patterns. I would love to keep myself updated with latest tools and software technologies and techniques.

5.	How would you track down a performance issue in production? Have you ever had to do this?
I’ll use google firebase  and google play developer console for tracking app performance

6.	How would you improve the APIs that you just used?
Pagination and background processing of data

7.	Please describe yourself in JSON format.
{
“name”: “Sumit Lakra”,
“email”: thesumit12@gmail.com,
“phone”: “+91-7757070403”

}

8.	What is the meaning of life?
For me being a technical person I would like to create something that will solve real life problems using latest tools and technologies.

