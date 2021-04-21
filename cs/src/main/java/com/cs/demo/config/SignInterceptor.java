package com.cs.demo.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cs.demo.eo.LoginEo;
import com.cs.demo.eo.LoginEoExample;
import com.cs.demo.mapper.LoginEoMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import static com.cs.demo.constants.ErrorConstants.SIGN_CHECK_ERROR;
import static com.cs.demo.constants.WebConstants.REQUEST_HEADER_ACCESS_TOKEN;

/**
 * @Description : 签名拦截器
 */
@Component
@Slf4j
public class SignInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginEoMapper loginEoMapper;



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.debug("SignInterceptor executing.....");
        //RequestWrapper requestWrapper = new RequestWrapper(request);
        try {
//            String body = requestWrapper.getBody();
//            Map mapParameter = JSON.parseObject(body, Map.class);
//            if (StringUtils.isBlank(body)) {
//                setResponse(response);
//                return Boolean.FALSE;
//            }
            Object sign = request.getHeader(REQUEST_HEADER_ACCESS_TOKEN);
            if (null == sign) {
                setResponse(response);
                return Boolean.FALSE;
            }
            LoginEoExample eoExample = new LoginEoExample();
            eoExample.createCriteria().andTokenEqualTo(sign.toString());
            eoExample.createCriteria().andLoginFlagEqualTo("1");
            List<LoginEo> loginEoList = loginEoMapper.selectByExample(eoExample);
            if (CollectionUtils.isEmpty(loginEoList)) {
                setResponse(response);
                return Boolean.FALSE;
            }
//            //增加到ThreadLocal中，方便后续取值
            ThreadLocalFactory.getThreadLocal(Long.class).set(loginEoList.get(0).getUserId());
            return true;
        } catch (Exception e) {
            log.error("签名验证失败:{},error:{}," , e);
        }
        setResponse(response);
        return Boolean.FALSE;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        ThreadLocalFactory.getThreadLocal(Long.class).remove();
    }

    public static HttpServletResponse setResponse(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        //获取字符流
        PrintWriter out = response.getWriter();
        out.append(JSONObject.toJSONString(SIGN_CHECK_ERROR));
        return response;
    }
}
