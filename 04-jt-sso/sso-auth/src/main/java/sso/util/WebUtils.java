package sso.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class WebUtils {
    /**将数据以json格式写到客户端
     * @param response 响应对象,负责向客户端输出数据的对象
     * @param dataMap 封装了我们要向客户端输出的数据
     * @throws IOException
     */
    public static void writeJsonToClient(
            HttpServletResponse response,
            Map<String,Object> dataMap) throws IOException {
        //1.设置响应数据的编码
        response.setCharacterEncoding("utf-8");
        //2.告诉浏览器响应数据的内容类型以及编码
        response.setContentType("application/json;charset=utf-8");
        //3.将数据转换为json格式字符串
        String jsonStr= new ObjectMapper().writeValueAsString(dataMap);
        //4.获取输出流对象将json数据写到客户端
        //4.1获取输出流对象
        PrintWriter out=response.getWriter();
        //4.2通过输出流向网络的客户端写数据
        out.println(jsonStr);
        out.flush();
    }
}