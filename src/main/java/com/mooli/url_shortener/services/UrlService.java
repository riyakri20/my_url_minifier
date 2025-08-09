package com.mooli.url_shortener.services;

import com.mooli.url_shortener.dtos.UrlPairDto;
import com.mooli.url_shortener.entities.Url;
import com.mooli.url_shortener.dtos.UrlDto;
import com.mooli.url_shortener.repositories.UrlRepository;
import com.mooli.url_shortener.utilities.Base62Encoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UrlService {
    private final UrlRepository urlRepository;
    private final Base62Encoder base62Encoder;

    public UrlService(UrlRepository urlRepository, Base62Encoder base62Encoder) {
        this.urlRepository = urlRepository;
        this.base62Encoder = base62Encoder;
    }

    @Transactional
    public UrlPairDto saveUrl(UrlDto urlDto) {
        Url url = new Url();
        url.setUrl(urlDto.url());

        Url url_ = urlRepository.save(url);

        String shortUrl = base62Encoder.Long2Base62(url_.getId());
        url.setShortUrl(shortUrl);

        return new UrlPairDto(url_.getUrl(), shortUrl);
    }

    @Transactional(readOnly = true)
    public UrlPairDto findUrlById(Long id) {
        Optional<Url> urlEntity = urlRepository.findById(id);

        return urlEntity.map(url -> {
            return new UrlPairDto(url.getUrl(), url.getShortUrl());
        }).orElseThrow(() -> new NoSuchElementException("URL not found with id : " + id));
    }

    @Transactional(readOnly = true)
    public UrlPairDto findUrlByShortUrl(String shortUrl) {
        Optional<Url> urlEntity = urlRepository.findByShortUrl(shortUrl);

        return urlEntity.map(url -> {
            return new UrlPairDto(url.getUrl(), url.getShortUrl());
        }).orElseThrow(() -> new NoSuchElementException("URL not found with url : " + shortUrl));
    }

    @Transactional(readOnly = true)
    public List<UrlPairDto> listUrls() {
        List<Url> urls = urlRepository.findAll();

        return urls.stream().map(url -> {
            return new UrlPairDto(url.getUrl(), url.getShortUrl());
        }).toList();
    }

    @Transactional(readOnly = true)
    public List<Url> listEntities() {
        return urlRepository.findAll();
    }
}
