package com.surface.resourcecenter.ui.login.fragment;

/**
 *
 * @author pll
 * @date 2018/7/10
 */

public interface LoginPresenter {
    /**
     * 登录
     * @param phone
     * @param password
     */
    void loginCommit(String phone, String password);
}
