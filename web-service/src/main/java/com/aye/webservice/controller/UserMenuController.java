package com.aye.webservice.controller;


import com.aye.commonlib.dto.request.UserMenuRequest;
import com.aye.commonlib.dto.request.UserSubMenuRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.service.UserMenuService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/menus")
public class UserMenuController {
    @Autowired
    UserMenuService userMenuService;

    @GetMapping("/findByMenuId/{id}")
    ResponseEntity<ApiRequestResponse> findByMenuId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(userMenuService.findByMenuId(id));
    }

    @GetMapping("/findByMeuName")
    ResponseEntity<ApiRequestResponse> findByMeuName(@RequestParam String menuName) {
        return ResponseEntity.ok(userMenuService.findByMeuName(menuName));
    }

    @GetMapping("/getUserAccess")
    ResponseEntity<ApiRequestResponse> getUserAccess(@RequestParam String userName) {
        return ResponseEntity.ok(userMenuService.getUserAccess(userName));
    }

    @GetMapping("/getUserAccessNew")
    ResponseEntity<ApiRequestResponse> getUserAccessNew(@RequestParam String userName) {
        return ResponseEntity.ok(userMenuService.getUserAccessNew(userName));
    }

    @GetMapping("/getAllManuName")
    ResponseEntity<ApiRequestResponse> getAllManuName() {
        return ResponseEntity.ok(userMenuService.getAllManuName());
    }

    @GetMapping("/getAllMenu")
    ResponseEntity<ApiRequestResponse> getAllMenu() {
        return ResponseEntity.ok(userMenuService.getAllMenu());
    }

    @PostMapping("/save")
    ResponseEntity<ApiRequestResponse> save(@Valid @RequestBody UserMenuRequest userMenuRequest) {
        return ResponseEntity.ok(userMenuService.save(userMenuRequest));
    }

    @PostMapping("/saveline")
    ResponseEntity<ApiRequestResponse> saveline(@Valid @RequestBody UserSubMenuRequest userSubMenuReq) {
        return ResponseEntity.ok(userMenuService.saveline(userSubMenuReq));
    }

    @GetMapping("findByUser")
    ResponseEntity<ApiRequestResponse> findByUser(@RequestParam String userName) {
        return ResponseEntity.ok(userMenuService.findByUser(userName));
    }

    @GetMapping("/findByMainMenu/{userMenuId}")
    ResponseEntity<ApiRequestResponse> findByMainMenu(@PathVariable("userMenuId") Integer userMenuId) {
        return ResponseEntity.ok(userMenuService.findByMainMenu(userMenuId));
    }

    @GetMapping("/findBysubmId/{id}")
    ResponseEntity<ApiRequestResponse> findBysubmId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(userMenuService.findBysubmId(id));
    }

    @GetMapping("/findSubMenuById/{submenuId}")
    ResponseEntity<ApiRequestResponse> findSubMenuById(@PathVariable("submenuId") Integer submenuId) {
        return ResponseEntity.ok(userMenuService.findSubMenuById(submenuId));
    }


    @GetMapping("/getUserAccessByUserName")
    ResponseEntity<ApiRequestResponse> getUserAccessByUserName(@RequestParam String username,
                                                               @RequestParam String roleType) {
        return ResponseEntity.ok(userMenuService.getUserAccessByUserName(username, roleType));
    }

    @GetMapping("/findByPage")
    ResponseEntity<ApiRequestResponse> findByPage(@RequestParam String pageName) {
        return ResponseEntity.ok(userMenuService.findByPage(pageName));
    }

    @GetMapping("/getUserReport/{temltDtId}")
    ResponseEntity<ApiRequestResponse> getUserReport(@PathVariable("temltDtId") Integer temltDtId) {
        return ResponseEntity.ok(userMenuService.getUserReport(temltDtId));
    }
}
