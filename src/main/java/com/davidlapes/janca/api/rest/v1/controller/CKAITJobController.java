package com.davidlapes.janca.api.rest.v1.controller;

import com.davidlapes.janca.api.rest.v1.entity.CKAITJobRequest;
import com.davidlapes.janca.external.ckait.entity.CKAITEngineer;
import com.davidlapes.janca.external.ckait.CKAITEngineerService;
import com.davidlapes.janca.external.ckait.entity.CKAITEngineerDetail;
import com.davidlapes.janca.mapper.EngineerMapper;
import com.davidlapes.janca.service.CKAITJobService;
import com.davidlapes.janca.service.EngineerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/ckait/job")
public class CKAITJobController {

    private final CKAITJobService ckaitJobService;

    @Autowired
    public CKAITJobController(CKAITJobService ckaitJobService) {
        this.ckaitJobService = ckaitJobService;
    }

    @PostMapping
    public void processEngineers( @RequestBody CKAITJobRequest request ) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put( "tid", request.getTid() );
        queryParams.put( "page", request.getPage() );
        ckaitJobService.processEngineers( queryParams );
    }
}
