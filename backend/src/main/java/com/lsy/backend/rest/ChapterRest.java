package com.lsy.backend.rest;

import com.lsy.common.service.ChapterService;
import com.lsy.common.domain.Chapter;
import com.lsy.common.rest.AbstractRest;
import com.lsy.common.service.AbstractService;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("admin/chapter")
public class ChapterRest extends AbstractRest<Chapter> {
    private final ChapterService chapterService;

    public ChapterRest(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @PostMapping("/create")
    public Chapter create(@RequestBody Chapter chapter) {
        return super.create(chapter);
    }

    @Override
    @GetMapping
    public Chapter findById(@RequestParam @Min(0) Long id) {
        return chapterService.findById(id);
    }

    @PostMapping("/list")
    public List<Chapter> list(@RequestBody Chapter chapter) {
        return super.find(chapter);
    }

    @Override
    public AbstractService<Chapter> getService() {
        return chapterService;
    }
}
