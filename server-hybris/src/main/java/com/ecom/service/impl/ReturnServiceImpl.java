package com.ecom.service.impl;

import com.ecom.common.exception.ExtensionNotCorrectException;
import com.ecom.common.utils.LocalFolderUtil;
import com.ecom.mapper.ReturnHybrisMapper;
import com.ecom.pojo.dto.HybrisReturnDTO;
import com.ecom.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ReturnServiceImpl implements ReturnService {

    @Autowired
    ReturnHybrisMapper returnHybrisMapper;
    @Autowired
    private LocalFolderUtil localFolderUtil;

    @Override
    public List<HybrisReturnDTO> getByPo(List<String> poList) {

        if(poList.size() == 0) return null;
        // load to temp
        returnHybrisMapper.createTempRDOTable();
        returnHybrisMapper.insertrdoList(poList);
        //get result
        List<HybrisReturnDTO> res = returnHybrisMapper.getHybrisReturn();
        returnHybrisMapper.truncateTempRDOTable();
        return res;
    }

    @Override
    public void uploadstg(MultipartFile file) {
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")) ;
        if(!extension.equals(".csv")) throw new ExtensionNotCorrectException("please upload correct file -- csv file is accepted");
        String filename =  "hybris_return.txt";
        String path = localFolderUtil.upload(file,filename);
        returnHybrisMapper.updateStgTable(path,1,"stg_return_hybris");
        returnHybrisMapper.updateReturnMaster();
        returnHybrisMapper.truncateTempRDOTable();

    }


}
