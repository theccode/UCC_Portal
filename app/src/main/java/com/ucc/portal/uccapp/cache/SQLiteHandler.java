package com.ucc.portal.uccapp.cache;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserAccountTable.Cols.KEY_ACCESS_LEVEL;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserAccountTable.Cols.KEY_CREATED_AT;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserAccountTable.Cols.KEY_HEX_CODE;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserAccountTable.Cols.KEY_ID;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserAccountTable.Cols.KEY_PASSWORD;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserAccountTable.Cols.KEY_REG_NO;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserAccountTable.Cols.KEY_SECURITY;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserAccountTable.Cols.KEY_SENT;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserAccountTable.Cols.KEY_STATUS;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserAccountTable.TABLE_USER_ACCOUNT;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_ADMISSION_NO;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_ALT_EMAIL;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_APPLICANT_ID;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_BANK_ACCOUNT;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_BANK_BRANCH_ID;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_BIOMETRIC_ENROLMENT;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_BIOMETRIC_ENROLMENT_DATE_;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_CARD_PRINT;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_CELL_PHONE;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_CENTRE_ID;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_CERT_NO;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_CGPA;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_CGPA_RAW;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_COMPLETED;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_DISABILITY_ID;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_DOA;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_DOB;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_DOC;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_EMAIL;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_ENTRY_LEVEL;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_ENTRY_MODE_ID;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_FNAME;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_GPS_ADDRESS;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_GRADUATE;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_HALL_ID;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_HOME_ADD;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_HOME_PHONE;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_HOME_TOWN;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_ID_CARD_STATUS;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_INST_EMAIL;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_LEVEL;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_LNAME;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_LOAN_TAKE_TIMES;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_MAJOR_ID;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_MARITAL_STATUS;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_MNAME;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_NATIONALITY;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_NATIONALITY_ID;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_NON_RES_ADD;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_PAY_TYPE;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_POB_REGION;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_POB_TOWN;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_POST_BOX;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_POST_TOWN;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_PREVIOUS_NAME;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_PROGID;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_REF_NO;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_RESIDENT_STATUS;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_ROOM_NO;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_SCHOOL_ID;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_SEX;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_SSNIT;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_STUDY_LEAVE;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_STUD_ID;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_TAKING_SSNIT;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_TEACHERS_REG_NO;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_TITLE;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_UPDATED_AT;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_VERIFICATION_DATE;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_VERIFIED;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_WITH_DRAWN;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.Cols.KEY_WORK_PLACE_ID;
import static com.ucc.portal.uccapp.cache.UCCStudPortalDBSchema.UserDetailsTable.TABLE_USER_DETAILS;

public class SQLiteHandler extends SQLiteOpenHelper {
    private static final String TAG = SQLiteHandler.class.getSimpleName();
    private static final int DATABASE_VERSION = 1;
    private static final String  DATABASE_NAME = "android_api";

    public SQLiteHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryStringUserAccount = "CREATE TABLE " + TABLE_USER_ACCOUNT
                + "( "
                + KEY_ID  + "INTEGER  PRIMARY KEY,"
                + KEY_REG_NO + " TEXT UNIQUE, "
                + KEY_PASSWORD + " TEXT, "
                + KEY_ACCESS_LEVEL + " TEXT, "
                + KEY_STATUS + " TEXT, "
                + KEY_SECURITY + " TEXT,"
                + KEY_CREATED_AT + " TIMESTAMP, "
                + KEY_HEX_CODE + " TEXT, "
                + KEY_SENT + " TINYINT "
                + ")";
        db.execSQL(queryStringUserAccount);
        String queryStringUserDetails = "CREATE TABLE " + TABLE_USER_DETAILS
                + "( "
                + KEY_STUD_ID + " INTEGER, "
                + KEY_REG_NO + " TEXT, "
                + KEY_LNAME + " TEXT, "
                + KEY_FNAME + " TEXT, "
                + KEY_MNAME + " TEXT, "
                + KEY_VERIFIED + " TINYINT, "
                + KEY_VERIFICATION_DATE + " DATETIME, "
                + KEY_TITLE +  " TEXT, "
                + KEY_SEX + " CHARACTER, "
                + KEY_MARITAL_STATUS + " TEXT, "
                + KEY_WORK_PLACE_ID + " TEXT, "
                + KEY_DOB +  " TEXT, "
                + KEY_DOA + " TEXT, "
                + KEY_DOC + " TEXT, "
                + KEY_PROGID + " TEXT, "
                + KEY_MAJOR_ID + " TEXT, "
                + KEY_LEVEL + " CHARACTER, "
                + KEY_ENTRY_LEVEL + " CHARACTER, "
                + KEY_ENTRY_MODE_ID + " TINYINT, "
                + KEY_HALL_ID + " TEXT, "
                + KEY_CENTRE_ID + " INTEGER, "
                + KEY_RESIDENT_STATUS + " TEXT, "
                + KEY_ROOM_NO + " TEXT, "
                + KEY_NON_RES_ADD + " TEXT, "
                + KEY_GPS_ADDRESS + " TEXT, "
                + KEY_SSNIT + " TEXT, "
                + KEY_POB_TOWN + " TEXT, "
                + KEY_POB_REGION + " TEXT, "
                + KEY_NATIONALITY + " TEXT, "
                + KEY_NATIONALITY_ID + " TINYINT, "
                + KEY_HOME_TOWN + " TEXT, "
                + KEY_POST_BOX + " TEXT, "
                + KEY_POST_TOWN + " TEXT, "
                + KEY_HOME_ADD + " TEXT, "
                + KEY_HOME_PHONE + " TEXT, "
                + KEY_CELL_PHONE + " TEXT, "
                + KEY_EMAIL + " TEXT, "
                + KEY_PREVIOUS_NAME + " TEXT, "
                + KEY_STATUS + " INTEGER, "
                + KEY_STUDY_LEAVE + " INTEGER, "
                + KEY_SCHOOL_ID + " INTEGER, "
                + KEY_APPLICANT_ID + " INTEGER, "
                + KEY_PAY_TYPE + " CHARACTER, "
                + KEY_TAKING_SSNIT + " CHARACTER, "
                + KEY_LOAN_TAKE_TIMES + " INTEGER, "
                + KEY_BANK_BRANCH_ID + " INTEGER, "
                + KEY_BANK_ACCOUNT + " TEXT, "
                + KEY_COMPLETED + " INTEGER, "
                + KEY_GRADUATE + " INTEGER, "
                + KEY_WITH_DRAWN + " INTEGER, "
                + KEY_BIOMETRIC_ENROLMENT + " INTEGER, "
                + KEY_BIOMETRIC_ENROLMENT_DATE_ + " DATETIME, "
                + KEY_CARD_PRINT + " INTEGER, "
                + KEY_CGPA + " DECIMAL(3,1), "
                + KEY_CGPA_RAW + " DECIMAL(5,4), "
                + KEY_CERT_NO + " CHARACTER, "
                + KEY_ID_CARD_STATUS + " INTEGER, "
                + KEY_DISABILITY_ID + " INTEGER, "
                + KEY_REF_NO + " TEXT, "
                + KEY_ADMISSION_NO + " TEXT, "
                + KEY_TEACHERS_REG_NO + " TEXT, "
                + KEY_ALT_EMAIL + " TEXT, "
                + KEY_INST_EMAIL + " TEXT, "
                + KEY_CREATED_AT + " TIMESTAMP, "
                + KEY_UPDATED_AT + " DATETIME "
                + ")";
        db.execSQL(queryStringUserDetails);
        Log.d(TAG, "Database tables created successfully.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_ACCOUNT);
        onCreate(db);
    }
    public void addUserCredentials(String reg_no, String password, String access_level, String status, String security, String created_at, String hex_code, String sent){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_REG_NO, reg_no);
        values.put(KEY_PASSWORD, password);
        values.put(KEY_ACCESS_LEVEL, access_level);
        values.put(KEY_STATUS, status);
        values.put(KEY_SECURITY, security);
        values.put(KEY_CREATED_AT, created_at);
        values.put(KEY_HEX_CODE, hex_code);
        values.put(KEY_SENT, sent);
        long i = db.insert(TABLE_USER_ACCOUNT, null, values);
        db.close();
        Log.d(TAG, " A new user has been added: " + i);
    }
    public void addUserDetails(String studid, String regno, String lname, String fname, String mname, String verified, String verificationDate, String title, String sex,
                               String maritalStatus, String workplaceId, String dob, String doa, String doc, String progId, String majorId, String level, String entryLevel,
                               String entryModeId, String hallId, String centreId, String residentStatus, String roomNo, String nonResAdd, String gpsAddress,
                               String ssnit, String pobTown, String pobRegion, String nationality, String nationalityId, String homeTown, String postBox, String postTown,
                               String homeAdd, String homePhone, String cellPhone, String email, String previousName, String status, String studyLeave, String schoolId, String applicantId,
                               String payType, String takingSSNIT, String loanTakeTimes, String bankBranchId, String bankAccount, String completed, String graduate,
                               String withDrawn, String biometricEnrolment, String biometricEnrolmentDate, String cardPrint, String cgpa, String cgpaRaw, String certNo, String idCardStatus,
                               String disabilityId, String refNo, String admissionNo, String teachersRegNo, String altEmail, String instEmail, String createdAt, String updatedAt){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_STUD_ID, studid);
        values.put(KEY_REG_NO, regno);
        values.put(KEY_LNAME, lname);
        values.put(KEY_FNAME, fname);
        values.put(KEY_MNAME, mname);
        values.put(KEY_VERIFIED, verified);
        values.put(KEY_VERIFICATION_DATE, verificationDate);
        values.put(KEY_TITLE, title);
        values.put(KEY_SEX, sex);
        values.put(KEY_MARITAL_STATUS, maritalStatus);
        values.put(KEY_WORK_PLACE_ID, workplaceId);
        values.put(KEY_DOB, dob);
        values.put(KEY_DOA, doa);
        values.put(KEY_DOC, doc);
        values.put(KEY_PROGID, progId);
        values.put(KEY_MAJOR_ID, majorId);
        values.put(KEY_LEVEL, level);
        values.put(KEY_ENTRY_LEVEL, entryLevel);
        values.put(KEY_ENTRY_MODE_ID, entryModeId);
        values.put(KEY_HALL_ID, hallId);
        values.put(KEY_CENTRE_ID, centreId);
        values.put(KEY_RESIDENT_STATUS, residentStatus);
        values.put(KEY_ROOM_NO, roomNo);
        values.put(KEY_NON_RES_ADD, nonResAdd);
        values.put(KEY_GPS_ADDRESS, gpsAddress);
        values.put(KEY_SSNIT, ssnit);
        values.put(KEY_POB_TOWN, pobTown);
        values.put(KEY_POB_REGION, pobRegion);
        values.put(KEY_NATIONALITY, nationality);
        values.put(KEY_NATIONALITY_ID, nationalityId);
        values.put(KEY_HOME_TOWN, homeTown);
        values.put(KEY_POST_BOX, postBox);
        values.put(KEY_POST_TOWN, postTown);
        values.put(KEY_HOME_ADD, homeAdd);
        values.put(KEY_HOME_PHONE, homePhone);
        values.put(KEY_CELL_PHONE, cellPhone);
        values.put(KEY_EMAIL, email);
        values.put(KEY_PREVIOUS_NAME, previousName);
        values.put(KEY_STATUS, status);
        values.put(KEY_STUDY_LEAVE, studyLeave);
        values.put(KEY_SCHOOL_ID, schoolId);
        values.put(KEY_APPLICANT_ID, applicantId);
        values.put(KEY_PAY_TYPE, payType);
        values.put(KEY_TAKING_SSNIT, takingSSNIT);
        values.put(KEY_LOAN_TAKE_TIMES, loanTakeTimes);
        values.put(KEY_BANK_BRANCH_ID, bankBranchId);
        values.put(KEY_BANK_ACCOUNT, bankAccount);
        values.put(KEY_COMPLETED, completed);
        values.put(KEY_GRADUATE, graduate);
        values.put(KEY_WITH_DRAWN, withDrawn);
        values.put(KEY_BIOMETRIC_ENROLMENT, biometricEnrolment);
        values.put(KEY_BIOMETRIC_ENROLMENT_DATE_, biometricEnrolmentDate);
        values.put(KEY_CARD_PRINT, cardPrint);
        values.put(KEY_CGPA, cgpa);
        values.put(KEY_CGPA_RAW, cgpaRaw);
        values.put(KEY_CERT_NO, certNo);
        values.put(KEY_ID_CARD_STATUS, idCardStatus);
        values.put(KEY_DISABILITY_ID, disabilityId);
        values.put(KEY_REF_NO, refNo);
        values.put(KEY_ADMISSION_NO, admissionNo);
        values.put(KEY_TEACHERS_REG_NO, teachersRegNo);
        values.put(KEY_ALT_EMAIL, altEmail);
        values.put(KEY_INST_EMAIL, instEmail);
        values.put(KEY_CREATED_AT, createdAt);
        values.put(KEY_UPDATED_AT, updatedAt);
        long i = db.insert(TABLE_USER_DETAILS, null, values);
        Log.d(TAG, "User details has been added "+i);
    }
    public HashMap<String,String> getUserCredentials(){
        HashMap<String, String> user = new HashMap<>();
        String selectQuery = "SELECT * FROM " + TABLE_USER_ACCOUNT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            user.put("reg_no", cursor.getString(1));
            user.put("password", cursor.getString(2));
            user.put("access_level", cursor.getString(3));
            user.put("status", cursor.getString(4));
        }
        cursor.close();
        db.close();
        Log.d(TAG, "Fetching user from database " + user.toString());
        return user;
    }
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> userDetails = new HashMap<>();
        String selectQuery  = "SELECT * FROM " + TABLE_USER_DETAILS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0){
            userDetails.put("regno", cursor.getString(1));
            userDetails.put("lname", cursor.getString(2));
            userDetails.put("fname", cursor.getString(3));
            userDetails.put("mname", cursor.getString(4));
            userDetails.put("gender", cursor.getString(8));
            userDetails.put("dob", cursor.getString(11));
            userDetails.put("programme", cursor.getString(14));
            userDetails.put("major", cursor.getString(15));
            userDetails.put("level", cursor.getString(16));
            userDetails.put("hallId", cursor.getString(19));
            userDetails.put("roomNo", cursor.getString(22));
            userDetails.put("address", cursor.getString(23));
            userDetails.put("gpsAddress", cursor.getString(24));
            userDetails.put("pobTown", cursor.getString(26));
            userDetails.put("homeTown", cursor.getString(30));
            userDetails.put("homeAddress", cursor.getString(31));
            userDetails.put("postTown", cursor.getString(32));
            userDetails.put("homeAdd", cursor.getString(33));
            userDetails.put("homePhone", cursor.getString(34));
            userDetails.put("cellPhone", cursor.getString(35));
            userDetails.put("email", cursor.getString(36));
        }
        cursor.close();
        db.close();
        Log.d(TAG, "Fetching User Details From DB"+ userDetails.toString());
        return userDetails;
    }
    public void deleteUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER_ACCOUNT, null, null);
        db.delete(TABLE_USER_DETAILS, null, null);
        Log.d(TAG, "Deleted all users");
    }
}
