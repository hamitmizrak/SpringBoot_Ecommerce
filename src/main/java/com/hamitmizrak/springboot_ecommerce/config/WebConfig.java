package com.hamitmizrak.springboot_ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // CORS (Cross-Origin Resource Sharing) yapılandırması
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Tüm endpoint'ler için CORS izin ver
                .allowedOrigins("http://localhost:3000") // Sadece belirtilen kaynaklar için izin ver
                .allowedMethods("GET", "POST", "PUT", "DELETE") // İzin verilen HTTP metotları
                .allowedHeaders("*") // Tüm başlıkları kabul et,  İzin verilen başlıklar
                .allowCredentials(true); // Kimlik doğrulama bilgilerini (cookie, authorization header vs.) kabul et
    }

    // Statik kaynaklar için yapılandırma
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")  // Kaynakların URL deseni
                .addResourceLocations("/public/", "classpath:/static/")  // Kaynakların yerleri
                .setCachePeriod(3600);  // Cache süresi (saniye)
    }

    // Interceptor eklemek için yapılandırma
    // "/api/**":
    // Bu ifade, /api/ ile başlayan tüm URL’ler üzerinde Interceptor’ın geçerli olacağını belirtir.
    // Örneğin, /api/products, /api/orders, /api/customers gibi yollar bu desene uyar.
    // ** kısmı, api/'den sonra herhangi bir alt yolun (subpath) gelebileceğini belirtir.
    // Yani, /api/'nin ardından gelen her türlü yol bu Interceptor tarafından işlenecektir.
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CustomInterceptor())  // CustomInterceptor sınıfı ile Interceptor eklenir
                .addPathPatterns("/api/**","/controller/**")  // Hangi URL deseninde geçerli olacağı
                .excludePathPatterns("/api/login", "/api/register");  // Hariç tutulacak URL desenleri
    }


    // Eğer Spring MVC uygulamanızda JSP, Thymeleaf gibi görünümler kullanıyorsanız, bir View Resolver yapılandırabilirsiniz.
    // Bu, HTML dosyalarının veya JSP sayfalarının nerede bulunduğunu belirtir.
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp");  // JSP dosyalarının yeri ve uzantısı
    }

   // Tarihler veya sayılar gibi verilerin formatlanması gerekiyorsa, özel formatlayıcılar ekleyebilirsiniz.
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new DateFormatter("yyyy-MM-dd"));  // Tarih biçimlendirici ekler
    }


    // Varsayılan dil olarak İngilizce ayarlanır
    @Bean
    public LocaleResolver localeResolver() {
        // Varsayılan dil olarak İngilizce ayarlanır
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(Locale.US);
        return resolver;
    }

    // 2.YOL Varsayılan dil olarak Türkçe  ayarlanır
    // Varsayılan dil olarak Türkçe ayarlanır
    /*
    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(new Locale("tr", "TR"));  // Varsayılan dil Türkçe
        return localeResolver;
    }

    // Dili değiştirmek için LocaleChangeInterceptor eklenir
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
        localeInterceptor.setParamName("lang");  // URL parametresi ile dil değiştirme
        registry.addInterceptor(localeInterceptor);
    }
    */
    /*
    Açıklamalar:
    CookieLocaleResolver: Kullanıcının seçtiği dili bir cookie'de saklar. Bu sayede kullanıcı uygulamaya tekrar girdiğinde aynı dili kullanabilir.
    localeResolver.setDefaultLocale(new Locale("tr", "TR")): Bu satır, varsayılan dil olarak Türkçe'yi (Türkiye) ayarlar. Bu, uygulama ilk açıldığında Türkçe'nin kullanılacağı anlamına gelir.
    LocaleChangeInterceptor: Bu interceptor, URL parametresine göre dili değiştirmeye olanak tanır. Örneğin, ?lang=en parametresi ile dili İngilizce yapabilirsiniz.
    Örnek Kullanım:
    Uygulama varsayılan olarak Türkçe başlayacaktır.
    URL'de ?lang=en gibi bir parametre ile dili İngilizce veya başka dillerle değiştirebilirsiniz.
     */

}
