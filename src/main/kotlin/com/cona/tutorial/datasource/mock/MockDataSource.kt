package com.cona.tutorial.datasource.mock

import com.cona.tutorial.datasource.BankDataSource
import com.cona.tutorial.model.Bank
import org.springframework.stereotype.Repository
import java.lang.IllegalArgumentException

@Repository
class MockDataSource : BankDataSource {
    private val banks = mutableListOf(
        Bank("FirstBank", 1.0, 4),
        Bank("somethink", 2.4, 2),
        Bank("real", 2.4, 2),
        Bank("true", 2.4, 2),
        Bank("is", 2.4, 2),
    )

    override fun getBanks(): Collection<Bank> = banks.toList()

    override fun getBank(accountNumber: String): Bank = banks.firstOrNull { it.accountNumber == accountNumber }
        ?: throw NoSuchElementException("Could not find a bank with account number $accountNumber")

    override fun createBank(bank: Bank): Bank {
        if (banks.any { it.accountNumber == bank.accountNumber }) throw IllegalArgumentException("Bank with account number ${bank.accountNumber} already exists")
        banks.add(bank)
        return bank
    }

    override fun updateBank(bank: Bank): Bank {
        val currentBank = banks.firstOrNull { it.accountNumber == bank.accountNumber }
            ?: throw NoSuchElementException("Could not find a bank with account number ${bank.accountNumber}")

        banks.remove(currentBank)
        banks.add(bank)
        return bank
    }

    override fun deleteBank(accountNumber: String) {
        val currentBank = banks.firstOrNull { it.accountNumber == accountNumber }
            ?: throw NoSuchElementException("$accountNumber bank not found!")
        banks.remove(currentBank)
    }

}