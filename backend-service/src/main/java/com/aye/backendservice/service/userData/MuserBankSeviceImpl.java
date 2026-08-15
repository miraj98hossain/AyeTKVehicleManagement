package com.aye.backendservice.service.userData;


import com.aye.backendservice.repository.userData.MuserBankRepo;
import com.aye.entity.user.userData.MuserBank;
import com.aye.enums.PaymentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MuserBankSeviceImpl implements MuserBankService {

    @Autowired
    private MuserBankRepo muserBankRepo;

    public MuserBankSeviceImpl() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public List<MuserBank> findAll() {
        // TODO Auto-generated method stub
        return this.muserBankRepo.findAll();
    }

    @Override
    public List<MuserBank> findbyOrg(Long orgId) {
        // TODO Auto-generated method stub
        return this.muserBankRepo.findByOrgId(orgId);
    }

    @Override
    public List<MuserBank> findbyOrgAndRefType(Long orgId, PaymentType refType) {
        return this.muserBankRepo.findByOrgIdAndRefType(orgId, refType);
    }

    @Override
//	public boolean isBankExists(Long orgId,Integer bankId) {
    public boolean isBankExists(Long orgId, Integer bankId, PaymentType refType) {
        // TODO Auto-generated method stub
        boolean res = this.muserBankRepo.findByOrgIdAndBankIdAndRefType(orgId, bankId, refType) != null;
//		if (this.muserBankRepo.isBankExists(orgId, bankId)==null)
        return res;
    }

    @Override
    public List<MuserBank> findByBankName(Long orgId, String accountName) {
        // TODO Auto-generated method stub
        return this.muserBankRepo.findByBankName(orgId, accountName);
    }

}
