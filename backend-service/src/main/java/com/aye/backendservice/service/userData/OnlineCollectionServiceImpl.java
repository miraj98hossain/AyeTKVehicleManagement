package com.aye.backendservice.service.userData;

//import com.aye.RestfulServer.repo.OnlineCollectionRepo;
//import com.aye.entity.OnlineCollection;
//import jakarta.persistence.EntityNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
/// **
// * @author: Miraj
// * @date: 17/01/2026
// * @time: 11:29
// */
//@Service
//public class OnlineCollectionServiceImpl implements OnlineCollectionService {
//    @Autowired
//    private OnlineCollectionRepo onlineCollectionRepo;
//
//    @Transactional(readOnly = true)
//    @Override
//    public OnlineCollection findById(Integer id) {
//        var onlineCollection = onlineCollectionRepo.findById(id).orElseThrow(
//                () -> new EntityNotFoundException("Collection not found with id " + id)
//        );
//        return onlineCollection;
//    }
//}
