package com.davidlapes.janca.external.ckait;

import com.davidlapes.janca.external.ckait.entity.CKAITEngineer;

import java.util.List;

public class CKAITFacade {

    private final CKAITHtmlPageService pageService;

    public CKAITFacade( CKAITHtmlPageService pageService ) {
        this.pageService = pageService;
    }

    public List<CKAITEngineer> getEngineers() {


        throw new UnsupportedOperationException();
    }

    public List<CKAITEngineer> getInactiveEngineers() {
        throw new UnsupportedOperationException();
    }
}
