package com.myfruit.pms.service;

import com.myfruit.pms.dto.PageDto;
import com.myfruit.pms.dto.SupplyDto;
import com.myfruit.pms.mapper.SupplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SupplyService {
    @Autowired
    private SupplyMapper supplyMapper;
    public void createSupply(SupplyDto supplyDto) {
        supplyMapper.insertSupply(supplyDto);
    }
    public SupplyDto getSupply(int id) {
        return supplyMapper.getSupplyById(id).orElseThrow(
                () -> new IllegalStateException("데이터를 찾을 수 없습니다.")
        );
    }
    public PageDto <SupplyDto> getSupplyList(int page, int limit) {
        int offset = (page - 1) * limit;
        List<SupplyDto> supplyList = supplyMapper.getSupplyList(limit, offset);
        int totalElements = supplyMapper.countTotal();
        int totalPages = (int) Math.ceil((double) totalElements / limit);
        PageDto <SupplyDto> pageDto = new PageDto(page, limit, totalPages, totalElements, supplyList);
        return pageDto;
    }
    public void modifySupply(SupplyDto supplyDto) {
        supplyMapper.updateSupply(supplyDto);
    }
    public void removeSupply(int id) {
        supplyMapper.deleteSupply(id);
    }
}