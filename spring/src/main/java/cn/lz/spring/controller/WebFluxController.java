package cn.lz.spring.controller;

import cn.lz.spring.service.MockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@Slf4j
@RestController
public class WebFluxController {

    @Resource
    private MockService mockService;

    @GetMapping("/webFlux")
    public Mono<String> webFlux() {
        log.info("web flux start...");
        Mono<String> mono = Mono.fromSupplier(() -> mockService.doSomeThing());
        log.info("web flux end...");
        return mono;
    }
}
