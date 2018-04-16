package it.caoxin.smarthome.domain.service.familyimg;

import it.caoxin.smarthome.domain.mapper.family.FamilyMapper;
import it.caoxin.smarthome.domain.model.Family;
import it.caoxin.smarthome.domain.model.User;
import it.caoxin.smarthome.domain.service.family.FamilyService;
import net.sf.json.JSONArray;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

import it.caoxin.smarthome.domain.model.FamilyImg;
import it.caoxin.smarthome.domain.mapper.familyimg.FamilyImgMapper;
import it.caoxin.smarthome.domain.service.familyimg.FamilyImgService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Transactional
@Service
public class FamilyImgServiceImpl implements FamilyImgService{

    private static final String FamilyPhotoPath = "/upload/family/";

    @Autowired
    private FamilyImgMapper familyImgMapper;

    @Autowired
    private FamilyService familyService;

    @Override
    public int insert(FamilyImg familyImg){
        return familyImgMapper.insert(familyImg);
    }

    @Override
    public int insertSelective(FamilyImg familyImg){
        return familyImgMapper.insertSelective(familyImg);
    }

    @Override
    public int insertList(List<FamilyImg> familyImgs){
        return familyImgMapper.insertList(familyImgs);
    }

    @Override
    public int updateSelect(FamilyImg familyImg){
        return familyImgMapper.updateSelect(familyImg);
    }

    //添加家庭图片
    @Override
    public String addFamilyImg(User user, Family family,
                               MultipartFile[] files,
                               HttpServletRequest request) {
        String uploadPath;
        if (familyService.isFamilyManager(user,family)) {
            if (files != null) {

                for (MultipartFile file:files
                     ) {
                    String originalFilename = file.getOriginalFilename();
                    if (!originalFilename.endsWith(".jpg") &&
                        !originalFilename.endsWith(".bmp") &&
                        !originalFilename.endsWith(".jpeg")&&
                        !originalFilename.endsWith(".png")){
                        return "Illegal file format";
                    }
                }
                uploadPath = request.getServletContext().getRealPath("/upload/family");
                for (int i = 0; i < files.length; i++) {


                    String fileName = UUID.randomUUID().toString();
                    try {
                        FileUtils.copyInputStreamToFile(files[i].getInputStream(), new File(uploadPath, fileName));
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("家庭文件上传文件失败。。。");
                    }

                    String photoUrl = FamilyPhotoPath+fileName;

                    FamilyImg familyImg = new FamilyImg();
                    familyImg.setFamilyId(family.getId());
                    familyImg.setImgUrl(photoUrl);
                    familyImg.setStatus(FamilyImg.STATUS_NORMAL);

//                    System.out.println("familyImg:"+familyImg);
                    familyImgMapper.insert(familyImg);


                }
                return "addFamilyImgSuccess";

            }
        }
        return "addFamilyImgFailure";
    }

    //删除家庭图片
    @Override
    public String deleteFamilyImg(User user, Family family, FamilyImg familyImg) {
        if (familyService.isFamilyManager(user,family)){
            if (familyImg.getId() != null){
                familyImgMapper.deleteById(familyImg.getId());

                return "delete success";
            }else {
                return "familyImg id is Null";
            }
        }
        return "delete failure";
    }


    //查看家庭照片
    @Override
    public String getfamilyImgs(Family family,User user) {
        if (familyService.isFamilyManager(user,family)){

            List<FamilyImg> familyImgs = familyImgMapper.selectByFamilyId(family.getId());

            return JSONArray.fromObject(familyImgs).toString();

        }

        return "you have not privilege";
    }
}
