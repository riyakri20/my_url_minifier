package com.mooli.url_shortener.controllers;

import com.mooli.url_shortener.dtos.UrlPairDto;
import com.mooli.url_shortener.entities.Url;
import com.mooli.url_shortener.dtos.UrlDto;
import com.mooli.url_shortener.services.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/urls")
public class UrlController {

    UrlService urlService;

    public UrlController(UrlService urlService){
        this.urlService = urlService;
    }

    @PostMapping("")
    public ResponseEntity<UrlPairDto> addUrl(@RequestBody UrlDto urlDto) {
        return ResponseEntity.ok(urlService.saveUrl(urlDto));
    }

    @GetMapping("")
    public ResponseEntity<List<UrlPairDto>> getUrl(){
        return ResponseEntity.ok(urlService.listUrls());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UrlPairDto> getUrlById(@PathVariable Long id) {
        return ResponseEntity.ok(urlService.findUrlById(id));
    }

    @GetMapping("/by-short-url")
    public ResponseEntity<UrlPairDto> getUrlByShortUrl(@RequestParam String shortUrl) {
        return ResponseEntity.ok(urlService.findUrlByShortUrl(shortUrl));
    }

    @GetMapping("/database")
    public ResponseEntity<List<Url>> getDb(){
        return ResponseEntity.ok(urlService.listEntities());
    }
}
