package com.lsy.backend.rest;

import com.lsy.common.service.SelectionService;
import com.lsy.common.domain.Selection;
import com.lsy.common.rest.AbstractRest;
import com.lsy.common.service.AbstractService;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("admin/selection")
public class SelectionRest extends AbstractRest<Selection> {
    private final SelectionService selectionService;

    public SelectionRest(SelectionService selectionService) {
        this.selectionService = selectionService;
    }

    @PostMapping("/create")
    public Selection create(@RequestBody Selection selection) {
        return super.create(selection);
    }


    @PostMapping("/list")
    public List<Selection> list(@RequestBody Selection selection) {
        return super.find(selection);
    }

    @Override
    public AbstractService<Selection> getService() {
        return selectionService;
    }

    @Override
    @GetMapping
    public Selection findById(@RequestParam @Min(0) Long id) {
        return selectionService.findById(id);
    }
}
