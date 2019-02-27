package com.cloud.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.cloud.model.user.LoginAppUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.Map;

public class AppUserUtil {

    /**
     * 获取登陆的 LoginAppUser
     * 可以将token放入参数中，也可以放入head里面
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static LoginAppUser getLoginAppUser() {
        //获取当前登录的凭证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //如果这个凭证属于OAuth2Authentication
        if (authentication instanceof OAuth2Authentication) {
            OAuth2Authentication oAuth2Auth = (OAuth2Authentication) authentication;
            authentication = oAuth2Auth.getUserAuthentication();

            if (authentication instanceof UsernamePasswordAuthenticationToken) {
                UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;
                Object principal = authentication.getPrincipal();
                if (principal instanceof LoginAppUser) {
                    return (LoginAppUser) principal;
                }

                Map map = (Map) authenticationToken.getDetails();
                map = (Map) map.get("principal");

                //最终将json转换成LoginAppUser对象
                return JSONObject.parseObject(JSONObject.toJSONString(map), LoginAppUser.class);
            }
        }

        return null;
    }
}
