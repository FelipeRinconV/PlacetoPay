package com.evertec.everteplacetopay.ui

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.evertec.everteplacetopay.data.repository.Repository
import com.evertec.everteplacetopay.vo.Resource
import com.evertec.everteplacetopay.vo.json.processTransaction.output.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import com.evertec.everteplacetopay.*
import com.evertec.everteplacetopay.data.DataController
import com.evertec.everteplacetopay.data.model.*
import com.evertec.everteplacetopay.data.model.remote.GateWayQuery
import com.evertec.everteplacetopay.data.model.remote.GatewayQueryInput
import com.evertec.everteplacetopay.data.model.remote.ProcessTransactionInput
import com.evertec.everteplacetopay.data.model.remote.ProcessTransactionOutput

class MainViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {


    private val posJsonTransaction = MutableLiveData<ProcessTransactionOutput>()

    private val stringId = MutableLiveData<String>()


    //Result of the transaction
    fun generateTransaction() = posJsonTransaction.distinctUntilChanged().switchMap {
        liveData<Resource<ProcessTransactionInput>>(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                emit(repository.postGenerateTransaction(it))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }
    }

    fun removeObserverGenerateContract(lifecycleOwner: LifecycleOwner) {
        posJsonTransaction.removeObservers(lifecycleOwner)
        posJsonTransaction.value = null
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

        val auth = generateAuth()
        val card = Card(ccv, monthExpired, yearExpired, numCard)
        val instrument = Instrument(card)
        val ipAddress = getLocalIpAddress(context).toString()
        val locale = "es_EC"

        //TODO pedir el tipo de documento
        val payer = Payer(
            numDocument,
            "CC",
            email,
            numPhone,
            name,
            surName
        )

        val amount = Amount("COP", total.toDouble())
        val payment = Payment(amount, description, reference)
        val userAgent = "kotlin android"

        posJsonTransaction.value = ProcessTransactionOutput(
            auth,
            instrument,
            ipAddress,
            locale,
            payer,
            payment,
            userAgent
        )
    }

    fun insertTransaction(transactionEntity: TransactionEntity) {
        viewModelScope.launch {
            repository.insertTransaction(transactionEntity)
        }
    }


    fun deleteTransaction(transactionEntity: TransactionEntity) {
        viewModelScope.launch {
            repository.deleteTransaction(transactionEntity)

        }
    }

    fun getListTransaction() = liveData<Resource<List<TransactionEntity>>>(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emitSource(repository.getAllTransaction(DataController.id).map { Resource.Success(it) })
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    //Todo realizar el base 64 con otro metodo o realizarlo manual para no subir tanto la api
    fun getStateTransactionNow(gateWayQuery: GateWayQuery) =
        liveData<Resource<GatewayQueryInput>>(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                emit(repository.getStateTransaction(gateWayQuery))
            } catch (ex: Exception) {
                emit(Resource.Failure(ex))
            }
        }


    @RequiresApi(Build.VERSION_CODES.O)
    fun transactionEntityToGatewayQuery(transactionEntity: TransactionEntity): GateWayQuery {
        return GateWayQuery("COP", generateAuth(), transactionEntity.internalReference)
    }


    fun getTransactionById() = stringId.distinctUntilChanged().switchMap { id ->
        liveData<Resource<TransactionEntity>>(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                emitSource(repository.getTransactionById(id).map { Resource.Success(it) })
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }
    }


    fun searchTransaction(id: String) {
        stringId.value = id
    }

    fun updateTransaction(data: GatewayQueryInput, transactionEntity: TransactionEntity) {

        //TODO add more data in the change
        transactionEntity.state = data.status.status

        viewModelScope.launch {
            repository.insertTransaction(transactionEntity)
        }

    }

}






