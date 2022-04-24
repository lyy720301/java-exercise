package cn.lz.spring.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("params")
public class ParamsController {

    /**
     * @param longs [1, 2, 3, 4]
     */
    @PostMapping("/list")
    public void longs(@RequestBody List<Long> longs) {
        log.info("list params {}", longs);
    }
}
