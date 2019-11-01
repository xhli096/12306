package J12306.api;


import J12306.config.UrlsEnum;
import J12306.http.Session;

/**
 * 获取js脚本
 * Create by Kalvin on 2019/9/20.
 */
public class GetJS {

    private Session session;

    public GetJS(Session session) {
        this.session = session;
    }

    public void send() {
        this.session.httpClient.send(UrlsEnum.GET_JS);
    }
}
