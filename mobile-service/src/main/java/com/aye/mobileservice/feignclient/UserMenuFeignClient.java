package com.aye.mobileservice.feignclient;


import com.aye.commonlib.dto.request.UserMenuRequest;
import com.aye.commonlib.dto.request.UserSubMenuRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "UserMenuFeignClient",
        url = "${backend.service.url}/api/menus")
public interface UserMenuFeignClient {
    @GetMapping("/findByMenuId/{id}")
    ResponseEntity<ApiRequestResponse> findByMenuId(@PathVariable("id") Integer id);

    @GetMapping("/findByMeuName")
    ResponseEntity<ApiRequestResponse> findByMeuName(@RequestParam String menuName);

    @GetMapping("/getUserAccess")
    ResponseEntity<ApiRequestResponse> getUserAccess(@RequestParam String userName);

    @GetMapping("/getUserAccessNew")
    ResponseEntity<ApiRequestResponse> getUserAccessNew(@RequestParam String userName);

    @GetMapping("/getAllManuName")
    ResponseEntity<ApiRequestResponse> getAllManuName();

    @GetMapping("/getAllMenu")
    ResponseEntity<ApiRequestResponse> getAllMenu();

    @PostMapping("/save")
    ResponseEntity<ApiRequestResponse> save(@Valid @RequestBody UserMenuRequest userMenuRequest);

    @PostMapping("/saveline")
    ResponseEntity<ApiRequestResponse> saveline(@Valid @RequestBody UserSubMenuRequest userSubMenuReq);

    @GetMapping("findByUser")
    ResponseEntity<ApiRequestResponse> findByUser(@RequestParam String userName);

    @GetMapping("/findByMainMenu/{userMenuId}")
    ResponseEntity<ApiRequestResponse> findByMainMenu(@PathVariable("userMenuId") Integer userMenuId);

    @GetMapping("/findBysubmId/{id}")
    ResponseEntity<ApiRequestResponse> findBysubmId(@PathVariable("id") Integer id);

    @GetMapping("/findSubMenuById/{submenuId}")
    ResponseEntity<ApiRequestResponse> findSubMenuById(@PathVariable("submenuId") Integer submenuId);


    @GetMapping("/getUserAccessByUserName")
    ResponseEntity<ApiRequestResponse> getUserAccessByUserName(@RequestParam String username,
                                                               @RequestParam String roleType);

    @GetMapping("/findByPage")
    ResponseEntity<ApiRequestResponse> findByPage(@RequestParam String pageName);

    @GetMapping("/getUserReport/{temltDtId}")
    ResponseEntity<ApiRequestResponse> getUserReport(@PathVariable("temltDtId") Integer temltDtId);
}
