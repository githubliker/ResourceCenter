package com.surface.resourcecenter.ui.login.fragment;

/**
 *
 * @author pll
 * @date 2018/7/10
 */

public interface LoginView {
    /**
     * 检测账号密码是否符合规则
     * @param message
     */
    void loginDataError(String message);

    /**
     * 登录成功回调
     */
    void loginSuccessForUI();
}
