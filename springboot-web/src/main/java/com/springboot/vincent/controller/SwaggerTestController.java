package com.springboot.vincent.controller;

import com.springboot.vincent.Entity.User;
import io.swagger.annotations.*;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;


import java.awt.print.Book;
import java.util.*;

/**
 * Created by weix on 2018/3/5.
 * @Api：修饰整个类，描述Controller的作用
   @ApiOperation：描述一个类的一个方法，或者说一个接口
   @ApiParam：单个参数描述
   @ApiModel：用对象来接收参数
   @ApiProperty：用对象接收参数时，描述对象的一个字段
   @ApiResponse：HTTP响应其中1个描述
   @ApiResponses：HTTP响应整体描述
   @ApiIgnore：使用该注解忽略这个API
   @ApiError ：发生错误返回的信息
   @ApiParamImplicitL：一个请求参数
   @ApiParamsImplicit 多个请求参数
   http://localhost:8008/swagger-ui.html
 */
@RestController
@Api("SwaggerTestController相关api")
@RequestMapping("/swagge")
public class SwaggerTestController {

    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

    @ApiOperation(value="获取用户列表", notes="")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "The resource not found")
    }
    )
    @RequestMapping(value="/getUserList", method=RequestMethod.GET)
    public List<User> getUserList() {
        List<User> r = new ArrayList<User>(users.values());
        return r;
    }

    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "The resource not found")
    }
    )
    @RequestMapping(value="/postUser", method=RequestMethod.POST)
    public String postUser(@RequestBody User user) {
        users.put(user.getId(), user);
        return "success";
    }

    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType="path")
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return users.get(id);
    }

   @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType="path"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
   @ApiResponses(value = {
           @ApiResponse(code = 200, message = "OK"),
           @ApiResponse(code = 404, message = "The resource not found")
   }
   )
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @RequestBody User user) {
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return "success";
    }


    @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType="path")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "The resource not found"),
            @ApiResponse(code = 405, message = "ERROR is swagger")
    }
    )
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }



}
