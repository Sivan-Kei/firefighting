package study.hitchhiking.interceptor;


import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import study.hitchhiking.utils.JWTUtil;
import study.hitchhiking.utils.response.ResponseData;

//自定义拦截器
public class TokenInterceptor implements HandlerInterceptor{
    /**
     * preHandle()返回true后，afterCompletion()才会执行
     */
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response,
                                Object handler, Exception arg3) throws Exception {
    }

    /**
     * 该方法在调用controller方法后，DispatcherServlet渲染视图之前被执行
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView model) throws Exception {
    }

    /**
     * 发起请求前调用
     * 该方法返回false的话，将不会往下执行
     */
    //拦截每个请求
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        if("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        response.setCharacterEncoding("utf-8");
        String token = null;
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if("token".equals(cookies[i].getName())){
                token = cookies[i].getValue();
            }
        }
        System.out.println("preHandle....." + token);
        ResponseData responseData;

        //token存在
        if(null != token) {
            String userID = JWTUtil.getUIDByToken(token);//解码
            if(null != userID){
                responseData = ResponseData.success();
                //responseMessage(response, response.getWriter(), responseData);
                return true;
            }
        }

        responseData = ResponseData.failed("无有效登录信息，请重新登录。");
        responseMessage(response, response.getWriter(), responseData);
        return false;
    }

    //请求不通过，返回错误信息给客户端
    private void responseMessage(HttpServletResponse response, PrintWriter out, ResponseData responseData) {
        response.setContentType("application/json; charset=utf-8");
        String json = JSONObject.toJSONString(responseData);
        out.print(json);
        out.flush();
        out.close();
    }

}