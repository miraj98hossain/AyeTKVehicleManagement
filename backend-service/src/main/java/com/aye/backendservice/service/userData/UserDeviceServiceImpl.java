package com.aye.backendservice.service.userData;


import com.aye.backendservice.repository.userData.UserDeviceRepo;
import com.aye.entity.user.Muser;
import com.aye.entity.user.UserDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by toufiq on 6/5/2021.
 */
@Service
public class UserDeviceServiceImpl implements UserDeviceService {

    @Autowired
    private UserDeviceRepo userDeviceRepo;
    private String userAgentData;
    private String dviceId;
    private String userAppVerion;

    @Override
    public UserDevice findActiveUserDevice(Muser u) {
        return this.userDeviceRepo.findByMuserAndIsActive(u, true);
    }

    @Override
    public UserDevice findActiveUserDeviceByDeviceId(String dId) {
        return this.userDeviceRepo.findByDeviceIdAndIsActive(dId, true);
    }

    @Override
    public void save(UserDevice ud) {
        this.userDeviceRepo.save(ud);
    }

    @Override
    public void delete(UserDevice ud) {
        this.userDeviceRepo.delete(ud);

    }

    @Override
    public String getUserAppVerion(Map<String, String> headers) throws Exception {
        userAgentData = "";
        headers.forEach((key, value) -> {
            if (key.equals("user-agent")) {
                userAgentData = value;
            }
        });
        if ((userAgentData == null) || (userAgentData == "")) {
            throw new Exception("No user-agent");
        }

        return this.getUserAppVersion(userAgentData);
    }

    //    @Override
    private void regisstryUserDevice(Map<String, String> headers, Muser u) throws Exception {

        String dId = getDviceId(headers);

        this.createNewUserDevice(dId, u);
    }

    @Override
    public void processUserDeviceId(Map<String, String> headers, Muser u) throws Exception {
        System.out.println("processUserDeviceId");
        UserDevice userDevice = findActiveUserDevice(u);
//        String dvId =getDviceId(headers);
//        System.out.println(dvId+" dvId");
//        UserDevice userDeviceByid =findActiveUserDeviceByDeviceId(dvId);

        if (((userDevice == null) || (userDevice.getId() == null))) {
            String dvId = getDviceId(headers);
            System.out.println(dvId + " dvId");
            if (!dvId.equals("")) {
                UserDevice userDeviceByid = findActiveUserDeviceByDeviceId(dvId);
                if (((userDeviceByid == null) || (userDeviceByid.getId() == null))) {

                    regisstryUserDevice(headers, u);
                } else {
                    throw new Exception("User Device Already Registered");
                }

            }
        } else {

            if (!chkDviceId(headers, u)) {
                throw new Exception("User Device Id Not Matched");
            }

        }
    }

    private String getDviceId(Map<String, String> headers) throws Exception {
        dviceId = "";
        headers.forEach((key, value) -> {
            if (key.equals("deviceid")) {
                dviceId = value;
            }
        });
        if (dviceId.equals("")) {
            throw new Exception("No Device Id Found From user Headers Info.");
        }
        return dviceId;
    }

    private void getFromHeadersValue(Map<String, String> headers, Muser u) {
        this.dviceId = "";

        headers.forEach((key, value) -> {
            if (key.equals("deviceid")) {
                this.dviceId = value;
            }
        });

        UserDevice ud = new UserDevice();
        ud.setMuser(u);
        ud.setDeviceId(this.dviceId);
        ud.setIsActive(true);
        save(ud);
    }

    private void createNewUserDevice(String dId, Muser u) {
        UserDevice ud = new UserDevice();
        ud.setMuser(u);
        ud.setDeviceId(dId);
        ud.setIsActive(true);
        save(ud);
    }

    private Boolean chkDviceId(Map<String, String> headers, Muser u) throws Exception {
        String dId = getDviceId(headers);
        UserDevice userDevice = findActiveUserDevice(u);
        if (!userDevice.getDeviceId().equals(dId)) {
            return false;
        }
        return true;
    }

    private String getUserAppVersion(String givenInfo) {
        userAppVerion = "";
        String a = "1.0.15-5/1000150 Dalvik/2.1.0 (Linux; U; Android 11; SM-A515F Build/RP1A.200720.012)";
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(givenInfo);
        int first = 0;
        while (matcher.find()) {
            first = matcher.start();
            String xx = givenInfo.substring(0, first);
            System.out.println("Start " + matcher.start());
            Pattern spattern = Pattern.compile("/");
            Matcher smatcher = spattern.matcher(xx);
            while (smatcher.find()) {
                first = smatcher.start();
            }
            System.out.println(xx.substring(first + 1, xx.length()));
            userAppVerion = xx.substring(first + 1, xx.length());
            break;
        }
        return userAppVerion;
    }
}
