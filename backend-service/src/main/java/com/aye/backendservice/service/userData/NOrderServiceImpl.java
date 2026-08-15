package com.aye.backendservice.service.userData;//package com.aye.RestfulServer.service;
//
//import com.aye.RestfulServer.repo.NOrderHeaderRepo;
//import com.aye.RestfulServer.repo.NOrderLineRepo;
//import com.aye.entity.NorderHeader;
//import jakarta.persistence.EntityNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
/// **
// * @author: Miraj
// * @date: 15/01/2026
// * @time: 12:09
// */
//@Service
//public class NOrderServiceImpl implements NOrderService {
//    @Autowired
//    private NOrderHeaderRepo headerRepo;
//    @Autowired
//    private NOrderLineRepo lineRepo;
//
//    @Override
//    @Transactional(readOnly = true)
//    public NorderHeader findById(Integer id) {
//        return headerRepo.findById(id).orElseThrow(
//                () -> new EntityNotFoundException("No Order Record Found with id " + id + " not found")
//        );
//    }
//
//
//}
