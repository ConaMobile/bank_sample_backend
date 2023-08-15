package com.cona.tutorial.service

import com.cona.tutorial.datasource.mock.MockDataSource
import com.cona.tutorial.model.Bank
import org.springframework.stereotype.Service

@Service
class BankService(private val dataSource: MockDataSource) {

    fun getBanks(): Collection<Bank> = dataSource.getBanks()

    fun getBank(accountNumber: String): Bank = dataSource.getBank(accountNumber)

    fun addBank(bank: Bank): Bank = dataSource.createBank(bank)

    fun updateBank(bank: Bank): Bank = dataSource.updateBank(bank)

    fun deleteBank(accountNumber: String) = dataSource.deleteBank(accountNumber)

}