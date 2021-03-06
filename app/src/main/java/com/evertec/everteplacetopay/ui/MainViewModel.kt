package com.evertec.everteplacetopay.ui

import android.content.Context
import android.net.wifi.WifiManager
import android.os.Build
import android.text.format.Formatter.formatIpAddress
import androidx.annotation.RequiresApi
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.evertec.everteplacetopay.data.model.TransactionEntity
import com.evertec.everteplacetopay.data.repository.Repository
import com.evertec.everteplacetopay.vo.Resource
import com.evertec.everteplacetopay.vo.json.processTransaction.output.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import com.evertec.everteplacetopay.*
import com.evertec.everteplacetopay.data.model.ProcessTransactionInput
import com.evertec.everteplacetopay.data.model.ProcessTransactionOutput
import com.google.gson.Gson
import okhttp3.OkHttpClient
import java.util.*

class MainViewModel @ViewModelInject constructor(private val repository: Repository) : ViewModel() {


    private val posJsonTransaction = MutableLiveData<String>()

    //Result of the transaction
    val currentTransaction = posJsonTransaction.switchMap {
        liveData<Resource<ProcessTransactionInput>>(Dispatchers.IO) {
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


    /**
     * Method that generates json for gateway transaction
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun getNewPosJsonTransaction(
        name: String,
        surName: String,
        numDocument: String,
        email: String,
        numPhone: String,
        numCard: String,
        monthExpired: String,
        yearExpired: String,
        ccv: String,
        currency: String,
        total: String,
        description: String,
        reference: String,
        context: Context
    ) {

        val login = "6dd490faf9cb87a9862245da41170ff2"
        val passwordKey = "024h1IlD"
        val nonce = getNonce()
        val nonceBase64 = getNonceBase64(nonce)
        val seedMethod = getSeed()
        val trankey = getDigits(false, passwordKey, nonce, seedMethod)
        val auth = Auth(login, trankey, nonceBase64, seedMethod)

        val card = Card(ccv, monthExpired, yearExpired, "36545400000008")
        val instrument = Instrument(card)

        val ipAddress = getLocalIpAddress(context).toString()
        //Todo extraer locale
        val locale = "es_EC"

        //TODO pedir el tipo de documento
        val payer = Payer(numDocument, "CC", email, numPhone, name, surName)
        val amount = Amount(currency, 2000.000)
        val payment = Payment(amount, description, reference)

        val userAgent = "kotlin " + System.getProperties().getProperty("kotlin.version")


        val stringJson = Gson().toJson(
            ProcessTransactionOutput(
                auth,
                instrument,
                ipAddress,
                locale,
                payer,
                payment,
                userAgent
            )
        )
        posJsonTransaction.value = stringJson

    }


    fun insertTransaction(transactionEntity: TransactionEntity) {
        viewModelScope.launch {
            repository.insertTransaction(transactionEntity)
        }
    }


}