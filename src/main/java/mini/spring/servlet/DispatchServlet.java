package mini.spring.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatchServlet extends HttpServlet {

    private Properties contextConfig =new Properties();

    private Map<String,Object> beanMap = new ConcurrentHashMap<String, Object>();

    private List<String> beanNams =new ArrayList<String>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("--------调用dopost ------");
        super.doPost(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        //开始初始化

        //1 定位
        doLoadConfig(config.getInitParameter("contextConfigLocation"));


        //2 加载
        doScanner();


        // 3 注册
        doRegister();


        // 4 自动依赖注入
        doAutowired();

        //如果是springmvc 会多一个handlerMapping

        //将@RequestMapping中配置的url和一个method匹配上，URL-method mapping  使用url 找到method ，通过反射区调用
        initHandlerMapping();
    }

    private void initHandlerMapping() {

    }

    private void doRegister() {
    }

    private void doAutowired() {
    }



    private void doScanner() {
    }

    private void doLoadConfig(String location) {
        //在spring中是通过Reader去查找和定位
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(location.replace("classpath:",""));
        try {
            contextConfig.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null!=is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
