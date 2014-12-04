package com.mparticle.internal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/* package-private */class MParticleDatabase extends SQLiteOpenHelper {

    private static final int DB_VERSION = 3;
    private static final String DB_NAME = "mparticle.db";

    interface BreadcrumbTable {
        public final static String TABLE_NAME = "breadcrumbs";
        public final static String SESSION_ID = "session_id";
        public final static String API_KEY = "api_key";
        public final static String MESSAGE = "message";
        public final static String CREATED_AT = "breadcrumb_time";
        public final static String CF_UUID = "cfuuid";
    }

    private static final String CREATE_BREADCRUMBS_DDL =
            "CREATE TABLE IF NOT EXISTS " + BreadcrumbTable.TABLE_NAME + " (" + BaseColumns._ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    BreadcrumbTable.SESSION_ID + " STRING NOT NULL, " +
                    BreadcrumbTable.API_KEY + " STRING NOT NULL, " +
                    BreadcrumbTable.MESSAGE + " TEXT, " +
                    BreadcrumbTable.CREATED_AT + " INTEGER NOT NULL, " +
                    BreadcrumbTable.CF_UUID + " TEXT" +
                    ");";

    interface SessionTable {
        public final static String TABLE_NAME = "sessions";
        public final static String SESSION_ID = "session_id";
        public final static String API_KEY = "api_key";
        public final static String START_TIME = "start_time";
        public final static String END_TIME = "end_time";
        public final static String SESSION_FOREGROUND_LENGTH = "session_length";
        public final static String ATTRIBUTES = "attributes";
        public final static String CF_UUID = "cfuuid";
    }

    private static final String CREATE_SESSIONS_DDL =
            "CREATE TABLE IF NOT EXISTS " + SessionTable.TABLE_NAME + " (" + BaseColumns._ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    SessionTable.SESSION_ID + " STRING NOT NULL, " +
                    SessionTable.API_KEY + " STRING NOT NULL, " +
                    SessionTable.START_TIME + " INTEGER NOT NULL," +
                    SessionTable.END_TIME + " INTEGER NOT NULL," +
                    SessionTable.SESSION_FOREGROUND_LENGTH + " INTEGER NOT NULL," +
                    SessionTable.ATTRIBUTES + " TEXT, " +
                    SessionTable.CF_UUID + " TEXT" +
                    ");";


    interface MessageTable {
        public final static String TABLE_NAME = "messages";
        public final static String SESSION_ID = "session_id";
        public final static String API_KEY = "api_key";
        public final static String MESSAGE = "message";
        public final static String STATUS = "upload_status";
        public final static String CREATED_AT = "message_time";
        public final static String MESSAGE_TYPE = "message_type";
        public final static String CF_UUID = "cfuuid";
    }

    private static final String CREATE_MESSAGES_DDL =
            "CREATE TABLE IF NOT EXISTS " + MessageTable.TABLE_NAME + " (" + BaseColumns._ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    MessageTable.SESSION_ID + " STRING NOT NULL, " +
                    MessageTable.API_KEY + " STRING NOT NULL, " +
                    MessageTable.MESSAGE + " TEXT, " +
                    MessageTable.STATUS + " INTEGER, " +
                    MessageTable.CREATED_AT + " INTEGER NOT NULL, " +
                    MessageTable.MESSAGE_TYPE + " TEXT, " +
                    MessageTable.CF_UUID + " TEXT" +
                    ");";

    interface UploadTable {
        public final static String TABLE_NAME = "uploads";
        public final static String API_KEY = "api_key";
        public final static String MESSAGE = "message";
        public final static String CREATED_AT = "message_time";
        public final static String CF_UUID = "cfuuid";
        public final static String SESSION_ID = "session_id";
    }

    private static final String CREATE_UPLOADS_DDL =
            "CREATE TABLE IF NOT EXISTS " + UploadTable.TABLE_NAME + " (" + BaseColumns._ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    UploadTable.API_KEY + " STRING NOT NULL, " +
                    UploadTable.MESSAGE + " TEXT, " +
                    UploadTable.CREATED_AT + " INTEGER NOT NULL, " +
                    UploadTable.CF_UUID + " TEXT, " +
                    UploadTable.SESSION_ID + " TEXT" +
                    ");";

    interface CommandTable {
        public final static String TABLE_NAME = "commands";
        public final static String URL = "url";
        public final static String METHOD = "method";
        public final static String POST_DATA = "post_data";
        public final static String HEADERS = "headers";
        public final static String CREATED_AT = "timestamp";
        public final static String SESSION_ID = "session_id";
        public final static String API_KEY = "api_key";
        public final static String CF_UUID = "cfuuid";
    }

    private static final String CREATE_COMMANDS_DDL =
            "CREATE TABLE IF NOT EXISTS " + CommandTable.TABLE_NAME + " (" + BaseColumns._ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CommandTable.URL + " STRING NOT NULL, " +
                    CommandTable.METHOD + " STRING NOT NULL, " +
                    CommandTable.POST_DATA + " TEXT, " +
                    CommandTable.HEADERS + " TEXT, " +
                    CommandTable.CREATED_AT + " INTEGER, " +
                    CommandTable.SESSION_ID + " TEXT, " +
                    CommandTable.API_KEY + " STRING NOT NULL, " +
                    CommandTable.CF_UUID + " TEXT" +
                    ");";

    interface GcmMessageTable {
        public final static String CONTENT_ID = "content_id";
        public final static String CAMPAIGN_ID = "campaign_id";
        public final static String TABLE_NAME = "gcm_messages";
        public final static String PAYLOAD = "payload";
        public final static String CREATED_AT = "message_time";
        public final static String DISPLAYED_AT = "displayed_time";
        public final static String EXPIRATION = "expiration";
        public final static String BEHAVIOR = "behavior";
        public final static String APPSTATE = "appstate";
    }

    private static final String CREATE_GCM_MSG_DDL =
            "CREATE TABLE IF NOT EXISTS " + GcmMessageTable.TABLE_NAME + " (" + GcmMessageTable.CONTENT_ID +
                    " TEXT PRIMARY KEY, " +
                    GcmMessageTable.PAYLOAD + " TEXT NOT NULL, " +
                    GcmMessageTable.APPSTATE + " TEXT NOT NULL, " +
                    GcmMessageTable.CREATED_AT + " INTEGER NOT NULL, " +
                    GcmMessageTable.EXPIRATION + " INTEGER NOT NULL, " +
                    GcmMessageTable.BEHAVIOR + " INTEGER NOT NULL," +
                    GcmMessageTable.CAMPAIGN_ID + " TEXT NOT NULL, " +
                    GcmMessageTable.DISPLAYED_AT + " INTEGER NOT NULL" +
                    ");";

    MParticleDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SESSIONS_DDL);
        db.execSQL(CREATE_MESSAGES_DDL);
        db.execSQL(CREATE_UPLOADS_DDL);
        db.execSQL(CREATE_COMMANDS_DDL);
        db.execSQL(CREATE_BREADCRUMBS_DDL);
        db.execSQL(CREATE_GCM_MSG_DDL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //so far upgrades have only been additive, so just make sure we've got all the tables.
        db.execSQL(CREATE_SESSIONS_DDL);
        db.execSQL(CREATE_MESSAGES_DDL);
        db.execSQL(CREATE_UPLOADS_DDL);
        db.execSQL(CREATE_COMMANDS_DDL);
        db.execSQL(CREATE_BREADCRUMBS_DDL);
        db.execSQL(CREATE_GCM_MSG_DDL);
    }
}
