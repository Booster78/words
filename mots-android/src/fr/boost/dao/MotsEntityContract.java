package fr.boost.dao;

import android.provider.BaseColumns;

public final class MotsEntityContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public MotsEntityContract() {}

    /* Inner class that defines the table contents */
    public static abstract class MotsEntity implements BaseColumns {
        public static final String TABLE_NAME = "Mots";
        public static final String COLUMN_NAME_ROW_ID = "rowId";
        public static final String COLUMN_NAME_MOTS_ID = "motsId";
        public static final String COLUMN_NAME_MOTS_VALUES = "motsValues";
    }
}