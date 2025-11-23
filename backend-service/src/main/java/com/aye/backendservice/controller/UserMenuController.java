package com.aye.backendservice.controller;

import com.aye.backendservice.service.UserMenuBService;
import com.aye.commonlib.dto.request.UserMenuRequest;
import com.aye.commonlib.dto.request.UserSubMenuRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/menus")
public class UserMenuController {
    @Autowired
    UserMenuBService userMenuBService;

    @GetMapping("/findByMenuId/{id}")
    public ResponseEntity<ApiRequestResponse> findByMenuId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(userMenuBService.findByMenuId(id));
    }

    @GetMapping("/findByMeuName")
    public ResponseEntity<ApiRequestResponse> findByMeuName(@RequestParam String menuName) {
        return ResponseEntity.ok(userMenuBService.findByMeuName(menuName));
    }

    @GetMapping("/getUserAccess")
    public ResponseEntity<ApiRequestResponse> getUserAccess(@RequestParam String userName) {
        return ResponseEntity.ok(userMenuBService.getUserAccess(userName));
    }

    @GetMapping("/getUserAccessNew")
    public ResponseEntity<ApiRequestResponse> getUserAccessNew(@RequestParam String userName) {
        return ResponseEntity.ok(userMenuBService.getUserAccessNew(userName));
    }

    @GetMapping("/getAllManuName")
    public ResponseEntity<ApiRequestResponse> getAllManuName() {
        return ResponseEntity.ok(userMenuBService.getAllManuName());
    }

    @GetMapping("/getAllMenu")
    public ResponseEntity<ApiRequestResponse> getAllMenu() {
        return ResponseEntity.ok(userMenuBService.getAllMenu());
    }

    @PostMapping("/save")
    public ResponseEntity<ApiRequestResponse> save(@Valid @RequestBody UserMenuRequest userMenuRequest) throws Exception {
        return ResponseEntity.ok(userMenuBService.save(userMenuRequest));
    }

    @PostMapping("/saveline")
    public ResponseEntity<ApiRequestResponse> saveline(@Valid @RequestBody UserSubMenuRequest userSubMenuReq) {
        return ResponseEntity.ok(userMenuBService.saveline(userSubMenuReq));
    }

    @GetMapping("findByUser")
    public ResponseEntity<ApiRequestResponse> findByUser(@RequestParam String userName) {
        return ResponseEntity.ok(userMenuBService.findByUser(userName));
    }

    @GetMapping("/findByMainMenu/{userMenuId}")
    public ResponseEntity<ApiRequestResponse> findByMainMenu(@PathVariable("userMenuId") Integer userMenuId) {
        return ResponseEntity.ok(userMenuBService.findByMainMenu(userMenuId));
    }

    @GetMapping("/findBysubmId/{id}")
    public ResponseEntity<ApiRequestResponse> findBysubmId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(userMenuBService.findBysubmId(id));
    }

    @GetMapping("/findSubMenuById/{submenuId}")
    public ResponseEntity<ApiRequestResponse> findSubMenuById(@PathVariable("submenuId") Integer submenuId) {
        return ResponseEntity.ok(userMenuBService.findSubMenuById(submenuId));
    }


    @GetMapping("/getUserAccessByUserName")
    public ResponseEntity<ApiRequestResponse> getUserAccessByUserName(@RequestParam String username,
                                                                      @RequestParam String roleType) {
        return ResponseEntity.ok(userMenuBService.getUserAccessByUserName(username, roleType));
    }

    @GetMapping("/findByPage")
    public ResponseEntity<ApiRequestResponse> findByPage(@RequestParam String pageName) {
        return ResponseEntity.ok(userMenuBService.findByPage(pageName));
    }

    @GetMapping("/getUserReport/{temltDtId}")
    public ResponseEntity<ApiRequestResponse> getUserReport(@PathVariable("temltDtId") Integer temltDtId) {
        return ResponseEntity.ok(userMenuBService.getUserReport(temltDtId));
    }
}
