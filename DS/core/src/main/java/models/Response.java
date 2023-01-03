package models;
public class Response {
    private String execType;
    private String url;



    public Response() {
    }
    public Response(String execType, String url) {
        this.execType = execType;
        this.url = url;
    }
    public String getExecType() {
        return execType;
    }

    public void setExecType(String execType) {
        this.execType = execType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
