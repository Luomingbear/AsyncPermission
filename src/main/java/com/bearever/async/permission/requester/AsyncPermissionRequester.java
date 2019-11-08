package com.bearever.async.permission.requester;




import androidx.fragment.app.Fragment;

import com.bearever.async.permission.AsyncPermissionRequest;


/**
 * 权限请求的发起者
 *
 * @author :  luoming    luomingbear@163.com
 * @date :  2019/8/13
 **/
public abstract class AsyncPermissionRequester extends Fragment {
    /**
     * 请求权限
     *
     * @param request
     */
    public abstract void request(AsyncPermissionRequest request);
}
