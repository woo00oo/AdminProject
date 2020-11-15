package com.example.admin.controller;

import com.example.admin.model.SearchParam;
import com.example.admin.model.network.Header;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // localhost:8080/api
public class GetController {

    @RequestMapping(method = RequestMethod.GET, path = "/getMethod") // Localhost:8080/api/getMethod
    public String getRequest(){

        return "Hi getMethod";
    }

    @GetMapping("/getParameter") // localhost:8080/api/getParameter?id=1234&password=abcd
    public String getParameter(@RequestParam String id,@RequestParam(name = "password") String pwd){
        System.out.println("id: "+id);
        System.out.println("password: "+pwd);

        return id+pwd;
    }

    //localhost:8080/api/multiParameter?account=abcd&email=study@gmail.com&page=10
    @GetMapping("/getMultiParameter")
    public SearchParam getMultiParameter(SearchParam searchParam){
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        //Json 형태로 Spring이 반환 해줌.(jackson 라이브러리 내장)    
        return searchParam;
    }

    @GetMapping("/header")
    public Header getHeader(){
        Boolean result = true;
        return Header.OK(result);
    }
}
