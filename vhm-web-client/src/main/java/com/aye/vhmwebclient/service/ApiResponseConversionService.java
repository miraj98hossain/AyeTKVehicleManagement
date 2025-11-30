package com.aye.vhmwebclient.service;

//@Slf4j
//@Service
public class ApiResponseConversionService {

//    @Autowired
//    private ObjectConversionService objectConversionService;
//
//    public void apiResponseConversion(ApiRequestResponse apiRequestResponse, Model modelMap) {
//        if (apiRequestResponse.getHttpStatus().contains(HttpStatus.OK.name())) {
//            apiRequestResponse.getApiRequestResponseDetails().forEach(rd ->
//            {
//                if (rd.getObject() == null) {
//
//                    return;
//                }
//                try {
//                    Object o = objectConversionService.convertToObject(rd);
//
//                    if (rd.getObjectType().equals(ApiRequestResponseDetail.ObjectType.A)) {
//                        List<?> objects = objectConversionService.convertObjectArayListToClassArrayList((List<Object>) o, rd);
//                        modelMap.addAttribute(rd.getObjectTag(), objects);
//                    } else if (rd.getObjectType().equals(ApiRequestResponseDetail.ObjectType.PD)) {
//                        Page<?> objects = objectConversionService.convertObjectPageableDataToClassPageableData2((Page<Object>) o, rd);
//                        modelMap.addAttribute(rd.getObjectTag(), objects);
//                    } else {
//                        modelMap.addAttribute(rd.getObjectTag(), o);
//                    }
//                } catch (ClassNotFoundException | JsonProcessingException e) {
//                    throw new RuntimeException(e);
//                }
//            });
//        } else {
//            modelMap.addAttribute("errorMessage", apiRequestResponse);
//        }
//
//    }
}
