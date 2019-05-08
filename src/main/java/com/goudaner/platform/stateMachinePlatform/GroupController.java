package com.goudaner.platform.stateMachinePlatform;

import com.goudaner.platform.stateMachinePlatform.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//package com.goudaner.platform.stateMachinePlatform;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
@RestController
public class GroupController {
//
    @Autowired
    GroupService groupService;
//
//    @RequestMapping("/group/list")
//    public List<Group> list(){
//        return groupService.listAll();
//    }
//
//    @PostMapping("/group/create")
//    public boolean  create(@RequestBody Group group){
//        groupService.create(group);
//        return true;
//    }
//
    @RequestMapping("/group/{id}/{event}")
    public boolean handle(@PathVariable("id")Integer id, @PathVariable("event") String event){
        return groupService.handleAction(id,event);
    }
//
}
