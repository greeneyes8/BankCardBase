package DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.mohse78.bankcardbase.Account;
import com.mohse78.bankcardbase.AccountDao;
import com.mohse78.bankcardbase.AccountTransaction;
import com.mohse78.bankcardbase.AccountTransactionDao;
import com.mohse78.bankcardbase.Bank;
import com.mohse78.bankcardbase.BankDao;
import com.mohse78.bankcardbase.Card;
import com.mohse78.bankcardbase.CardDao;
import com.mohse78.bankcardbase.DaoMaster;
import com.mohse78.bankcardbase.DaoSession;
import com.mohse78.bankcardbase.Person;
import com.mohse78.bankcardbase.PersonDao;
import com.mohse78.bankcardbase.SmsBankMetaData;
import com.mohse78.bankcardbase.SmsBankMetaDataDao;
import com.mohse78.bankcardbase.TransactionType;
import com.mohse78.bankcardbase.TransactionTypeDao;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by ab.mohammadi on 1/24/2017.
 */
public class DBFactory {

    public static final String DB_NAME = "CARDSDB";

    DaoSession session;
    PersonDao personDao;
    BankDao bankDao;
    AccountDao accountDao;
    AccountTransactionDao accountTransactionDao;
    CardDao cardDao;
    SmsBankMetaDataDao smsBankMetaDataDao;
    TransactionTypeDao transactionTypeDao;


    public DBFactory(Context context){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context , DB_NAME , null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster master = new DaoMaster(db);
        DaoSession session = master.newSession();
        accountDao = session.getAccountDao();
        personDao = session.getPersonDao();
        bankDao = session.getBankDao();
        accountDao = session.getAccountDao();
        accountTransactionDao  = session.getAccountTransactionDao();
        cardDao = session.getCardDao();
        smsBankMetaDataDao = session.getSmsBankMetaDataDao();
        transactionTypeDao = session.getTransactionTypeDao();
    }

    //PERSON CRUDs

    public long insertPerson(Person person){
        return personDao.insert(person);
    }

    public void updatePerson(Person person){
        personDao.update(person);
    }
    public List<Person> getAllPersons(){
        return personDao.loadAll();
    }
    public Person getPerson(long id){
        return personDao.load(id);
    }

    //BANK CRUDs

    public void insertBank(Bank bank){
        bankDao.insert(bank);
    }
    public void updateBank(Bank bank){
        bankDao.update(bank);
    }
    public List<Bank> getAllBanks(){
        return bankDao.loadAll();
    }
    public Bank getBank(long id){
        return bankDao.load(id);
    }

    //ACCOUNT CRUDs

    public long insertAccount(Account account){
        return accountDao.insert(account);
    }
    public void updateAccount(Account account){
        accountDao.update(account);
    }
    public List<Account> getAllAccounts(){
        return accountDao.loadAll();
    }
    public Account getAccount(long id){
        return accountDao.load(id);
    }

    //ACCOUNTTRANSACTION CRUDs

    public void insertAccountTransaction(AccountTransaction accountTransaction){
        accountTransactionDao.insert(accountTransaction);
    }
    public void updateAccountTransaction(AccountTransaction accountTransaction){
        accountTransactionDao.update(accountTransaction);
    }
    public List<AccountTransaction> getAllAccountTransaction(){
        return accountTransactionDao.loadAll();
    }
    public AccountTransaction getAccountTransaction(long id){
        return accountTransactionDao.load(id);
    }
    public List<AccountTransaction> getAllAccountTransactionForAccount(long account_id){
        List<AccountTransaction> accountTransactions = new ArrayList<>();
        accountTransactions = accountTransactionDao.queryBuilder()
                .where(AccountTransactionDao.Properties.Account_id.eq(account_id))
                .orderDesc(AccountTransactionDao.Properties.CreationDate)
                .list();
        //----------------GET ALL TRANSACTIONS FOR ACCOUNT---------------//

        return accountTransactions;
    }


    //CARD CRUDs
    public long insertCard(Card card , Account account){
        long account_id_new = insertAccount(account);
        if(account_id_new > -1){
            card.setAccount_id(account_id_new);
            return cardDao.insert(card);
        }
        return -1;
    }
    public void updateCard(Card card){
        cardDao.update(card);
    }
    public List<Card> getAllCards(){
        return cardDao.loadAll();
    }
    public Card getCard(long id){
        return cardDao.load(id);
    }

    //SMS CRUDs
    public void insertSMSMetaData(SmsBankMetaData smsBankMetaData){
        smsBankMetaDataDao.insert(smsBankMetaData);
    }

    //trasnaction type cruds
    public void insertTransactionType(TransactionType transactionType){
        transactionTypeDao.insert(transactionType);
    }
    public List<TransactionType> getAllTransactionTypes(){
        return transactionTypeDao.loadAll();
    }

    public List<TransactionType> getAllTransactionTypeDebit(){
        return   transactionTypeDao.queryBuilder()
                .where(TransactionTypeDao.Properties.Type.eq(1))
                .list();
    }
    public List<TransactionType> getAllTransactionTypeCredit(){
        return   transactionTypeDao.queryBuilder()
                .where(TransactionTypeDao.Properties.Type.eq(0))
                .list();
    }
    public TransactionType getTransactionType(Long id){
        return transactionTypeDao.load(id);
    }

    //DLETE
    public void deleteAllBanks(){
        bankDao.deleteAll();
    }
    public void deleteAllPersons(){
        personDao.deleteAll();
    }
    public void deleteAllTransactionTypes(){
        transactionTypeDao.deleteAll();
    }

}
