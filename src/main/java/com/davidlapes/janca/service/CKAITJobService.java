package com.davidlapes.janca.service;

import com.davidlapes.janca.external.ckait.CKAITEngineerService;
import com.davidlapes.janca.external.ckait.entity.CKAITEngineer;
import com.davidlapes.janca.external.ckait.entity.CKAITEngineerDetail;
import com.davidlapes.janca.mapper.EngineerMapper;
import com.davidlapes.janca.model.Engineer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CKAITJobService {

    private final EngineerService engineerService;

    public CKAITJobService( EngineerService engineerService ) {
        this.engineerService = engineerService;
    }

    public void processEngineers( Map<String, String> queryParams ) {
        System.out.println( "Processing started..." );

        List<CKAITEngineer> engineers = CKAITEngineerService.getAllEngineers( queryParams );

        System.out.println( "Processing " + engineers.size() + " engineers..." );

        engineers.forEach( engineer -> {
            CKAITEngineerDetail detail = null;

            if ( engineer.getDetailUrl() != null ) {
                try {
                    Thread.sleep( 1000 );
                    detail = CKAITEngineerService.getDetail( engineer.getDetailUrl() );
                } catch ( InterruptedException ex ) {
                    throw new RuntimeException( ex );
                }
            }

            Engineer eng = EngineerMapper.map( engineer, detail );
            engineerService.create( eng );
        } );

        System.out.println( "Processing finished..." );
    }
}
