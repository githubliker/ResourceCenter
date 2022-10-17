package com.surface.resourcecenter.ui.login.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.surface.resourcecenter.R;
import com.surface.resourcecenter.data.sp.SpManager;
import com.surface.resourcecenter.ui.home.HomeActivity;


/**
 *
 * @author likedong
 * @date 16/6/30
 */
public class LoginFragment extends Fragment implements LoginView {

    public static String TAG = "LoginFragment";
    public static String PHONELOGIN = "phoneLogin";
    public static String THIRDPLATFORM = "thirdPartyPlatformLogin";
    private TextView forgotPassword;
    private Button commit;
    private EditText phone;
    private EditText password;
    private LoginPresenterImpl presenter;

    public static Fragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rc_fragment_login, container, false);
        forgotPassword = view.findViewById(R.id.login_forget_password);
        commit = (Button) view.findViewById(R.id.login_commit);
        phone = view.findViewById(R.id.login_num);
        password = view.findViewById(R.id.login_password);
        initView();
        initListener();
        readSave();
        return view;
    }

    private void readSave() {

        String number = SpManager.getInstance().get("login_phoneNum");
        String login_password = SpManager.getInstance().get("login_password");
        phone.setText(number);
        if (!TextUtils.isEmpty(number)){
            phone.setSelection(number.length());
        }
        password.setText(login_password);
    }

    private void initView() {
        presenter = new LoginPresenterImpl(this,getContext());
    }

    private void initListener() {
        /**
         * 忘记密码
         */
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(getActivity(), GetCodeActivity.class));
            }
        });

        /**
         * 提交登录
         */
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                commit.setEnabled(false);
                String number = phone.getText().toString();
                String login_password = password.getText().toString();
                presenter.loginCommit(number,login_password);
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 登录数据错误
     * @param message
     */
    @Override
    public void loginDataError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        commit.setEnabled(true);
    }

    /**
     * 登录成功
     */
    @Override
    public void loginSuccessForUI() {
        commit.setEnabled(true);
        if(getActivity() != null) {
            Toast.makeText(getActivity(), "登录成功", Toast.LENGTH_SHORT).show();
            getActivity().finish();
//            Router.launchMainActivity(getActivity());
            HomeActivity.launch(getContext());
            String number = phone.getText().toString();
            String login_password = password.getText().toString();
            SpManager.getInstance().set("login_phoneNum", number);
            SpManager.getInstance().set("login_password", login_password);
        }

    }
}

