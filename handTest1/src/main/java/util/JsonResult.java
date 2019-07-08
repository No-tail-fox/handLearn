package util;


import java.io.Serializable;

public class JsonResult implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static final int SUCCESS=200;
    public static final int USERNAMEERROR=1;
    public static final int PASSWORDERROR=2;
    public static final int OTHERERROR=3;

    //返回是否正确，正确为0，错误为1
    private int state;
    //错误消息
    private String message;
    //正确时返回的数据
    private Object data;

    public JsonResult() {}

    public JsonResult(Object data){
        this.state=SUCCESS;
        this.data=data;
    }

    public JsonResult(Exception e) {

        this.message=e.getMessage();
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "JsonResult{" + "state=" + state + ", message='" + message + '\'' + ", data=" + data + '}';
    }
}
