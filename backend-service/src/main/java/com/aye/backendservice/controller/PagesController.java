package com.aye.backendservice.controller;

import com.aye.backendservice.service.PagesBService;
import com.aye.commonlib.dto.request.PagesRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/pages")
public class PagesController {
    @Autowired
    PagesBService pageService;

    @GetMapping("/findAll")
    public ResponseEntity<ApiRequestResponse> findAll() {
        return ResponseEntity.ok().body(this.pageService.findAll());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ApiRequestResponse> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(this.pageService.findById(id));
    }

    @GetMapping("/findByName")
    public ResponseEntity<ApiRequestResponse> findByName(@RequestParam String name) {
        return ResponseEntity.ok().body(this.pageService.findByName(name));
    }

    @PostMapping("/save")
    public ResponseEntity<ApiRequestResponse> save(@Valid @RequestBody PagesRequest pagesRequest) {
        return ResponseEntity.ok().body(this.pageService.save(pagesRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiRequestResponse> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(this.pageService.delete(id));
    }

    @GetMapping("/findByUrl")
    public ResponseEntity<ApiRequestResponse> findByUrl(@RequestParam String p) {
        return ResponseEntity.ok().body(this.pageService.findByUrl(p));
    }

    @GetMapping("/findByPageGroup/{group}")
    public ResponseEntity<ApiRequestResponse> findByPageGroup(@PathVariable("group") Integer group) {
        return ResponseEntity.ok().body(this.pageService.findByPageGroup(group));
    }
}
