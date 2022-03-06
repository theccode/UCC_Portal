package com.ucc.portal.uccapp.ui.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.ucc.portal.uccapp.Network.Router;
import com.ucc.portal.uccapp.R;
import com.ucc.portal.uccapp.activities.DashboardActivity;
import com.ucc.portal.uccapp.activities.LoginActivity;
import com.ucc.portal.uccapp.cache.SQLiteHandler;
import com.ucc.portal.uccapp.databinding.FragmentLoginBinding;
import com.ucc.portal.uccapp.helpers.AppController;
import com.ucc.portal.uccapp.helpers.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class LoginFragment  extends Fragment {
    private ProgressDialog mProgressDialog;
    private SessionManager mSession;
    private SQLiteHandler db;
    private static final String TAG = LoginActivity.class.getSimpleName();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setCancelable(false);
        mSession = new SessionManager(getContext());
        db = new SQLiteHandler(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentLoginBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        if (mSession.isLoggedIn()){
            Intent intent = new Intent(getActivity(), DashboardActivity.class);
            startActivity(intent);
            requireActivity().finish();
        }
        binding.btnLogin.setOnClickListener(v -> {
            String indexNumber = Objects.requireNonNull(binding.etIndexNumber.getText()).toString();
            String encrypted_password = Objects.requireNonNull(binding.etPassword.getText()).toString().trim();

            if (!indexNumber.isEmpty() && !encrypted_password.isEmpty()){
                checkLogin(indexNumber, encrypted_password);
            } else {
                Toast.makeText(getContext(), "Invalid email and password.", Toast.LENGTH_SHORT).show();
            }
        });
        return binding.getRoot();
    }

    private void checkLogin(final String username, final String password) {
        String str_request_tag = "req_login";
        mProgressDialog.setMessage("Logging in ...");
        showDialog();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Router.LoginEndPoint.URL_LOGIN, response -> {
            Log.d(TAG, "Login Response: " + response);
            hideDialog();
            try {
                JSONObject jsonObject = new JSONObject(response.substring(response.indexOf("{"), response.indexOf("}")+2));
                boolean error = jsonObject.getBoolean("error");
                if (!error) {
                    mSession.setLogIn(true);
                    JSONObject users = jsonObject.getJSONObject("user");
                    Log.d("USER", users.toString());
//                    User Credentials
                    String _reg_no = users.getString("regno");
                    String _password = users.getString("password");
                    String _access_level = users.getString("access_level");
                    String _status = users.getString("status");
                    String _security = users.getString("security");
                    String _hex_code = users.getString("hex_code");
                    String _created_at = users.getString("created_at");
                    String _sent = users.getString("sent");
//                    User Details
                    String studId = users.getString("studid");
                    String regNo = users.getString("regno");
                    String lName = users.getString("lname");
                    String fName = users.getString("fname");
                    String mName = users.getString("mname");
                    String verified = users.getString("verified");
                    String verificationDate = users.getString("verification_date");
                    String title = users.getString("title");
                    String sex = users.getString("sex");
                    String maritalStatus = users.getString("marital_status");
                    String workPlaceId = users.getString("workplace_id");
                    String dob = users.getString("dob");
                    String doa = users.getString("doa");
                    String doc = users.getString("doc");
                    String progId = users.getString("progid");
                    String majorId = users.getString("majorid");
                    String level = users.getString("level");
                    String entryLevel = users.getString("entrylevel");
                    String entryModeId = users.getString("entry_modeid");
                    String hallId = users.getString("hallid");
                    String centreId = users.getString("centreid");
                    String residentStatus = users.getString("resident_status");
                    String roomNo = users.getString("room_no");
                    String nonResAdd = users.getString("non_res_add");
                    String gpsAddress = users.getString("gps_address");
                    String ssnit = users.getString("ssnit");
                    String pobTown = users.getString("pob_town");
                    String pobRegion = users.getString("pob_region");
                    String nationality = users.getString("nationality");
                    String nationalityId = users.getString("nationalityid");
                    String homeTown = users.getString("hometown");
                    String postBox = users.getString("post_box");
                    String postTown = users.getString("post_town");
                    String homeAdd = users.getString("homeadd");
                    String homePhone = users.getString("homephone");
                    String cellPhone  = users.getString("cellphone");
                    String email = users.getString("email");
                    String previousName = users.getString("previous_name");
                    String status = users.getString("status");
                    String studyLeave = users.getString("studyleave");
                    String schoolId = users.getString("schoolid");
                    String applicantId = users.getString("applicant_id");
                    String payType = users.getString("paytype");
                    String takingSSNIT  = users.getString("taking_ssnit");
                    String loanTakeTimes = users.getString("loan_take_times");
                    String bankBranchId = users.getString("bank_branch_id");
                    String bankAccount  = users.getString("bank_account");
                    String completed = users.getString("completed");
                    String graduate = users.getString("graduate");
                    String withdrawn = users.getString("withdrawn");
                    String biometricEnrolment = users.getString("biometric_enrolment");
                    String biometricEnrolmentDate = users.getString("biometric_enrolment_date");
                    String cardPrint = users.getString("card_print");
                    String cgpa = users.getString("cgpa");
                    String cgpaRaw = users.getString("cgpa_raw");
                    String certNo = users.getString("cert_no");
                    String idCardStatus = users.getString("idcardstatus");
                    String disabilityId = users.getString("disabilityid");
                    String refNo = users.getString("ref_no");
                    String admissionNo = users.getString("admission_no");
                    String teachersRegNo = users.getString("teachers_regno");
                    String altEmail = users.getString("alt_email");
                    String instEmail = users.getString("inst_email");
                    String createdAt = users.getString("created_at");
                    String updatedAt = users.getString("updated_at");


                    db.addUserCredentials(_reg_no, _password, _access_level, _status, _security, _hex_code, _created_at, _sent);
                    db.addUserDetails(studId, regNo, lName, fName, mName, verified, verificationDate, title, sex, maritalStatus,
                            workPlaceId, dob, doa, doc, progId, majorId, level, entryLevel, entryModeId, hallId, centreId, residentStatus,
                            roomNo, nonResAdd, gpsAddress, ssnit, pobTown, pobRegion, nationality, nationalityId, homeTown, postBox, postTown,
                            homeAdd, homePhone, cellPhone, email, previousName, status, studyLeave, schoolId, applicantId, payType, takingSSNIT,
                            loanTakeTimes, bankBranchId, bankAccount, completed, graduate, withdrawn, biometricEnrolment, biometricEnrolmentDate,
                            cardPrint, cgpa, cgpaRaw, certNo, idCardStatus, disabilityId, refNo, admissionNo, teachersRegNo, altEmail, instEmail,
                            createdAt, updatedAt);
                    startActivity(new Intent(getActivity(), DashboardActivity.class));
                    requireActivity().finish();
                } else {
                    String err = jsonObject.getString("error_msg");
                    Toast.makeText(getContext(), ""+ err, Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                Toast.makeText(getContext(), "..." + e.getMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }, error -> {
            Log.d(TAG, "Login Error: " + error.getMessage());
            Toast.makeText(getContext(), ""+error.getMessage(), Toast.LENGTH_LONG).show();
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() {
                HashMap<String, String> params = new HashMap<>();
                params.put("reg_no", username);
                params.put("password", password);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest, str_request_tag);
    }

    private void hideDialog() {
        if (mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }

    private void showDialog() {
        if (!mProgressDialog.isShowing()){
            mProgressDialog.show();
        }
    }
}
