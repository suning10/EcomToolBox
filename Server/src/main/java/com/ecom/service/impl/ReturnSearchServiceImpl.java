package com.ecom.service.impl;

import com.ecom.common.exception.ExtensionNotCorrectException;
import com.ecom.common.utils.LocalFolderUtil;
import com.ecom.mapper.mysql.ReturnMapper;
import com.ecom.pojo.dto.SearchByRDODTO;
import com.ecom.pojo.entity.Return;
import com.ecom.pojo.entity.ReturnSimple;
import com.ecom.service.ReturnSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ReturnSearchServiceImpl implements ReturnSearchService {

    @Autowired
    ReturnMapper returnMapper;

    @Autowired
    private LocalFolderUtil localFolderUtil;

    @Override
    public List<Return> getByRDO(SearchByRDODTO searchByRDODTO) {

        returnMapper.createTempRDOTable();
        returnMapper.truncateTempRDOTable();
        returnMapper.insertrdoList(searchByRDODTO.getRdoList());
        List<Return> returnResult = returnMapper.searchByRDO(searchByRDODTO);

        return returnResult;
    }

    @Override
    public List<Return> getByPO(SearchByRDODTO searchByRDODTO) {
        returnMapper.createTempRDOTable();
        returnMapper.truncateTempRDOTable();
        returnMapper.insertrdoList(searchByRDODTO.getRdoList());
        List<Return> returnResult = returnMapper.searchByPO(searchByRDODTO);

        return returnResult;
    }

    @Override
    public void upload(MultipartFile file) {
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")) ;

        if(!extension.equals(".csv")) throw new ExtensionNotCorrectException("please upload correct file -- csv file is accepted");
        String filename =  "ageReturn.csv";
        String path = localFolderUtil.upload(file,filename);
        returnMapper.updateReturnMaster();
        returnMapper.loadDataInline(path);

    }

    @Override
    public List<ReturnSimple> getByRDOSimple(SearchByRDODTO searchByRDODTO) {
        // use temp table to hold the value
        returnMapper.createTempRDOTable();
        returnMapper.truncateTempRDOTable();
        returnMapper.insertrdoList(searchByRDODTO.getRdoList());
        List<ReturnSimple> returnResult = returnMapper.searchByRDOSimple(searchByRDODTO);

        return returnResult;
    }

    @Override
    public void uploadSimple(MultipartFile file) {
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")) ;

        if(!extension.equals(".csv")) throw new ExtensionNotCorrectException("please upload correct file -- csv file is accepted");
        String filename =  "ageReturnSimple.csv";
        String path = localFolderUtil.upload(file,filename);
        //update stgTable
        returnMapper.updateStgTable();
        returnMapper.loadDataInlineStg(path);
        //load into actual table
        returnMapper.loadDataReturnSearch();

    }
}
