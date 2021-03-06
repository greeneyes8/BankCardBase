package com.mohse78.bankcardbase;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.mohse78.bankcardbase.Person;
import com.mohse78.bankcardbase.Bank;
import com.mohse78.bankcardbase.Card;
import com.mohse78.bankcardbase.Account;
import com.mohse78.bankcardbase.AccountTransaction;
import com.mohse78.bankcardbase.SmsBankMetaData;
import com.mohse78.bankcardbase.TransactionType;

import com.mohse78.bankcardbase.PersonDao;
import com.mohse78.bankcardbase.BankDao;
import com.mohse78.bankcardbase.CardDao;
import com.mohse78.bankcardbase.AccountDao;
import com.mohse78.bankcardbase.AccountTransactionDao;
import com.mohse78.bankcardbase.SmsBankMetaDataDao;
import com.mohse78.bankcardbase.TransactionTypeDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig personDaoConfig;
    private final DaoConfig bankDaoConfig;
    private final DaoConfig cardDaoConfig;
    private final DaoConfig accountDaoConfig;
    private final DaoConfig accountTransactionDaoConfig;
    private final DaoConfig smsBankMetaDataDaoConfig;
    private final DaoConfig transactionTypeDaoConfig;

    private final PersonDao personDao;
    private final BankDao bankDao;
    private final CardDao cardDao;
    private final AccountDao accountDao;
    private final AccountTransactionDao accountTransactionDao;
    private final SmsBankMetaDataDao smsBankMetaDataDao;
    private final TransactionTypeDao transactionTypeDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        personDaoConfig = daoConfigMap.get(PersonDao.class).clone();
        personDaoConfig.initIdentityScope(type);

        bankDaoConfig = daoConfigMap.get(BankDao.class).clone();
        bankDaoConfig.initIdentityScope(type);

        cardDaoConfig = daoConfigMap.get(CardDao.class).clone();
        cardDaoConfig.initIdentityScope(type);

        accountDaoConfig = daoConfigMap.get(AccountDao.class).clone();
        accountDaoConfig.initIdentityScope(type);

        accountTransactionDaoConfig = daoConfigMap.get(AccountTransactionDao.class).clone();
        accountTransactionDaoConfig.initIdentityScope(type);

        smsBankMetaDataDaoConfig = daoConfigMap.get(SmsBankMetaDataDao.class).clone();
        smsBankMetaDataDaoConfig.initIdentityScope(type);

        transactionTypeDaoConfig = daoConfigMap.get(TransactionTypeDao.class).clone();
        transactionTypeDaoConfig.initIdentityScope(type);

        personDao = new PersonDao(personDaoConfig, this);
        bankDao = new BankDao(bankDaoConfig, this);
        cardDao = new CardDao(cardDaoConfig, this);
        accountDao = new AccountDao(accountDaoConfig, this);
        accountTransactionDao = new AccountTransactionDao(accountTransactionDaoConfig, this);
        smsBankMetaDataDao = new SmsBankMetaDataDao(smsBankMetaDataDaoConfig, this);
        transactionTypeDao = new TransactionTypeDao(transactionTypeDaoConfig, this);

        registerDao(Person.class, personDao);
        registerDao(Bank.class, bankDao);
        registerDao(Card.class, cardDao);
        registerDao(Account.class, accountDao);
        registerDao(AccountTransaction.class, accountTransactionDao);
        registerDao(SmsBankMetaData.class, smsBankMetaDataDao);
        registerDao(TransactionType.class, transactionTypeDao);
    }
    
    public void clear() {
        personDaoConfig.getIdentityScope().clear();
        bankDaoConfig.getIdentityScope().clear();
        cardDaoConfig.getIdentityScope().clear();
        accountDaoConfig.getIdentityScope().clear();
        accountTransactionDaoConfig.getIdentityScope().clear();
        smsBankMetaDataDaoConfig.getIdentityScope().clear();
        transactionTypeDaoConfig.getIdentityScope().clear();
    }

    public PersonDao getPersonDao() {
        return personDao;
    }

    public BankDao getBankDao() {
        return bankDao;
    }

    public CardDao getCardDao() {
        return cardDao;
    }

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public AccountTransactionDao getAccountTransactionDao() {
        return accountTransactionDao;
    }

    public SmsBankMetaDataDao getSmsBankMetaDataDao() {
        return smsBankMetaDataDao;
    }

    public TransactionTypeDao getTransactionTypeDao() {
        return transactionTypeDao;
    }

}
