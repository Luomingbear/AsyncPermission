package com.bearever.async.permission.listener;



import com.bearever.async.permission.info.Permission;

import java.util.List;

/**
 * 权限请求失败的结果回调接口
 *
 * @author :  luoming    luomingbear@163.com
 * @date :  2019/8/13
 **/
public interface OnAsyncPermissionDeniedListener {
    /**
     * 请求权限的结果
     *
     * @param permissions 请求权限失败的结果列表
     */
    void onResult(List<Permission> permissions);
}
