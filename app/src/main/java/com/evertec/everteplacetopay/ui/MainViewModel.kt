package com.evertec.everteplacetopay.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.evertec.everteplacetopay.data.model.Transaction
import com.evertec.everteplacetopay.data.model.TransactionEntity
import com.evertec.everteplacetopay.data.repository.Repository
import com.evertec.everteplacetopay.vo.Resource
import com.evertec.everteplacetopay.vo.json.output.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import com.evertec.everteplacetopay.*
import com.google.gson.Gson

class MainViewModel @ViewModelInject constructor(private val repository: Repository) : ViewModel() {

    private val posJsonTransaction = MutableLiveData<PostJsonTransaction>()

    val currentTransaction = posJsonTransaction.switchMap {
        liveData<Resource<Transaction>>(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                emit(repository.postGenerateTransaction(it))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }
    }


    fun printText(): String {
        return repository.printText()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun getNewPosJsonTransaction(

    ): String {


        val login = "6dd490faf9cb87a9862245da41170ff2"
        val passwordKey = "024h1IlD"
        //val nonce = getNonce(false)
        val nonce = getNonce()
        val nonceBase64 = getNonceBase64(nonce)
        // val nonceBase64 = base64(nonce.encodeToByteArray())
        val seedMethod = getSeed()
        // val seedMethod = "2021-03-06T09:56:56-05:00"

        val trankey = getDigits(false, passwordKey, nonce, seedMethod)
        val auth = Auth(login, trankey, nonceBase64, seedMethod)

        var gson = Gson()
        val jsonString = gson.toJson(auth)

        return jsonString;

        //  val credit = Credit(ccv.toInt(), 0, "ap", 20)
        /*
 val card = Card(numCard.toInt(), monthExpired.toInt(), yearExpired.toInt(), ccv.toInt())
 val instrument = Instrument(card, credit, "otp")
 val auth = Auth("login", "transkey", "nonce", "seed")
 val amountInput = AmountInput("cop", 200.0)
 val payment = Payment("REFRENCE", "description", amountInput)
 // val payer = Payer()
 val postJsonTransaction = PostJsonTransaction(auth, "cop", payment, "", "", instrument)
 */


    }


    fun insertTransaction(transactionEntity: TransactionEntity) {
        viewModelScope.launch {
            repository.insertTransaction(transactionEntity)
        }
    }


}