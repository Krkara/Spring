package com.kristjan.webshop.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.kristjan.webshop.entity.Product;
import com.kristjan.webshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Component
public class ProductCache {

    @Autowired
    ProductRepository productRepository;

    LoadingCache<Long, Product> loadingCache =
            CacheBuilder.newBuilder()
                    .expireAfterWrite(15, TimeUnit.MINUTES)
                    .build(new CacheLoader<>() {
                        @Override
                        public Product load(Long id) {
                            return productRepository.findById(id).get();
                        }
                    });

    public Product getProduct(Long id) throws ExecutionException {
        return loadingCache.get(id);
    }

    public void refreshProduct(Long id, Product updatedProduct) throws ExecutionException {
        loadingCache.put(id, updatedProduct);
    }

    public void deleteFromCache(Long id) {
        loadingCache.invalidate(id);
    }
}