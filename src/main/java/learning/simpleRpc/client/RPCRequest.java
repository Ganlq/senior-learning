package learning.simpleRpc.client;

/**
 * 调用端发送请求实体类
 * 也作为回调时同步等待的对象锁
 */
public class RPCRequest {

    private String requestID;
    private String className;
    private String methodName;

    //private Class[] parameterTypes;
    private Object[] parameters;

    //结果异步返回时 赋值
    private Object result;

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
