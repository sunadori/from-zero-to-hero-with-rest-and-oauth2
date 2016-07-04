package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.hateoas.Resources;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Date;

/**
 * UIアプリケーション
 */
@SpringBootApplication
@EnableOAuth2Sso
@Controller
public class UiServerApplication {
    /**
     * OAuth2を使用するRestクライアント
     */
    @Autowired
    OAuth2RestTemplate restTemplate;

    /**
     * @Value("${xxx}")で、プロパティファイルからxxxのキーに該当する値を取得して代入する
     * ${xxx:defaultValue}で指定するとxxxが存在しない場合に、defaultValueが代入される
     */
    @Value("${message.api:http://localhost:18080/messages}")
    String messageApi;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    String home(Model model) {
        // OAuth2のRESTクライアントでAPIを呼び出す。認証エラーの場合は、プロパティファイルに定義されているAuthServerへリダイレクトされる
        @SuppressWarnings("unchecked")
        Resources<Message> messages = restTemplate.getForObject(messageApi + "?sort=createdAt,DESC", Resources.class);
        // 取得結果をModelに代入
        model.addAttribute("messages", messages.getContent());
        return "index";
    }

    @RequestMapping(path = "/messages", method = RequestMethod.POST)
    String post(@RequestParam String text) {
        // OAuth2のRESTクライアントで、データのPOST
        restTemplate.postForObject(messageApi, Collections.singletonMap("text", text), Void.class);
        return "redirect:/";
    }

    static class Message {
        public String text;
        public String username;
        public Date createdAt;
    }

    /**
     * エントリポイント
     * @param args 起動引数
     */
	public static void main(String[] args) {
		SpringApplication.run(UiServerApplication.class, args);
	}
}
