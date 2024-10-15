package com.hamitmizrak.springboot_ecommerce.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

// LOMBOK
@Log4j2

public class CustomInterceptor implements HandlerInterceptor {

    // preHandle: İstek sunucuya ulaşmadan önce çalışır.
    // Burada, kimlik doğrulama, loglama, izin kontrolü gibi işlemler yapılabilir.
    // Eğer false dönerse istek işlenmez ve burada durdurulur.
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // İstek işlenmeden önce çalışır
        log.info("preHandle","İstek işlenmeden önce çalışır");
        return true;  // Eğer false dönerse istek devam etmez
    }

    // postHandle: İstek başarıyla işlendiğinde ama yanıt henüz istemciye gitmeden önce çalışır.
    // Bu aşamada, model veya view ile ek işlemler yapılabilir.
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // İstek işlendikten sonra ama cevap istemciye gitmeden önce çalışır
        log.info("postHandle","İstek işlendikten sonra ama cevap istemciye gitmeden önce çalışır");
    }

    // afterCompletion: İstek tamamen tamamlandığında çalışır.
    // Bu aşamada genellikle temizleme, loglama gibi işlemler yapılır.
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // İstek tamamlandıktan sonra çalışır, genellikle temizleme işlemleri için kullanılır
        log.info("afterCompletion","İstek tamamlandıktan sonra çalışır, genellikle temizleme işlemleri için kullanılır");
    }
}
