package com.mohse78.bankcardbase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.mohse78.bankcardbase.Card;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table Cards.
*/
public class CardDao extends AbstractDao<Card, Long> {

    public static final String TABLENAME = "Cards";

    /**
     * Properties of entity Card.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Bank_id = new Property(1, Long.class, "bank_id", false, "BANK_ID");
        public final static Property Account_id = new Property(2, Long.class, "account_id", false, "ACCOUNT_ID");
        public final static Property Person_id = new Property(3, Long.class, "person_id", false, "PERSON_ID");
        public final static Property CardNumber = new Property(4, String.class, "cardNumber", false, "CARD_NUMBER");
        public final static Property Cvv2 = new Property(5, String.class, "cvv2", false, "CVV2");
        public final static Property ExpDate = new Property(6, String.class, "expDate", false, "EXP_DATE");
        public final static Property BankBranchCode = new Property(7, String.class, "bankBranchCode", false, "BANK_BRANCH_CODE");
    };


    public CardDao(DaoConfig config) {
        super(config);
    }
    
    public CardDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'Cards' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'BANK_ID' INTEGER," + // 1: bank_id
                "'ACCOUNT_ID' INTEGER," + // 2: account_id
                "'PERSON_ID' INTEGER," + // 3: person_id
                "'CARD_NUMBER' TEXT," + // 4: cardNumber
                "'CVV2' TEXT," + // 5: cvv2
                "'EXP_DATE' TEXT," + // 6: expDate
                "'BANK_BRANCH_CODE' TEXT);"); // 7: bankBranchCode
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'Cards'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Card entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long bank_id = entity.getBank_id();
        if (bank_id != null) {
            stmt.bindLong(2, bank_id);
        }
 
        Long account_id = entity.getAccount_id();
        if (account_id != null) {
            stmt.bindLong(3, account_id);
        }
 
        Long person_id = entity.getPerson_id();
        if (person_id != null) {
            stmt.bindLong(4, person_id);
        }
 
        String cardNumber = entity.getCardNumber();
        if (cardNumber != null) {
            stmt.bindString(5, cardNumber);
        }
 
        String cvv2 = entity.getCvv2();
        if (cvv2 != null) {
            stmt.bindString(6, cvv2);
        }
 
        String expDate = entity.getExpDate();
        if (expDate != null) {
            stmt.bindString(7, expDate);
        }
 
        String bankBranchCode = entity.getBankBranchCode();
        if (bankBranchCode != null) {
            stmt.bindString(8, bankBranchCode);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Card readEntity(Cursor cursor, int offset) {
        Card entity = new Card( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // bank_id
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // account_id
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3), // person_id
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // cardNumber
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // cvv2
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // expDate
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7) // bankBranchCode
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Card entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setBank_id(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setAccount_id(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setPerson_id(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setCardNumber(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setCvv2(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setExpDate(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setBankBranchCode(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Card entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Card entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}