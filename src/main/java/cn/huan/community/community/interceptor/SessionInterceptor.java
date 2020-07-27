package cn.huan.community.community.interceptor;

import cn.huan.community.community.domain.Account;
import cn.huan.community.community.service.AccountService;
import cn.huan.community.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private AccountService accountService;

    @Autowired
    private NotificationService notificationService;
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //System.out.println("经过session拦截器");
        //判断用户是否存在
        Cookie[] cookies = request.getCookies();
        Account account = null;
        if(cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies) {
                if("token".equals(cookie.getName())){
                    String token = cookie.getValue();
                    account = accountService.getByToken(token);
                    if(account != null){
                        request.getSession().setAttribute("user",account);
                        long unreadCount = notificationService.unread(account.getId());
                        request.getSession().setAttribute("unreadCount",unreadCount);
                    }
                    break;
                }
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                            @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                 @Nullable Exception ex) throws Exception {
    }
}
