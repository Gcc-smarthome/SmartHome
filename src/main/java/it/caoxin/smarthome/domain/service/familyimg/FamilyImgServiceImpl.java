package it.caoxin.smarthome.domain.service.familyimg;

import it.caoxin.smarthome.domain.mapper.family.FamilyMapper;
import it.caoxin.smarthome.domain.model.Family;
import it.caoxin.smarthome.domain.model.User;
import it.caoxin.smarthome.domain.service.family.FamilyService;
import net.sf.json.JSONArray;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public static final Logger logger = LoggerFactory.getLogger(FamilyImgServiceImpl.class);

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
            if (files != null && files.length != 0) {

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
                logger.debug("进入文件上传");

                uploadPath = request.getServletContext().getRealPath("/upload/family");
                logger.debug("files.length"+files.length);
                for (int i = 0; i < files.length; i++) {
                    String originalFilename = files[i].getOriginalFilename();
                    logger.debug("files:"+originalFilename);
                    String fileName = UUID.randomUUID()+originalFilename.substring(originalFilename.length()-4,originalFilename.length());
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
                    logger.debug("添加家庭文件");
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

    //查看家庭默认图片
    @Override
    public String getDefaultPhoto(Family family) {
        if (family.getId() != null){
            FamilyImg familyImg = familyImgMapper.selectByDefaultFamilyId(family.getId());
            if (familyImg != null){
                return familyImg.getImgUrl();
            }
            return "get photo failure";
        }
        return "get photo failure";
    }
    //修改家庭默认图片
    @Override
    public String updateFamilyImg(FamilyImg familyImg,Family family) {
        if (familyImg.getId() != null &&
                family.getId() != null){
            FamilyImg defaultFamilyImg = familyImgMapper.selectByDefaultFamilyId(family.getId());
            if (defaultFamilyImg != null){
                defaultFamilyImg.setStatus(FamilyImg.STATUS_NORMAL);
                familyImgMapper.updateSelect(defaultFamilyImg);

            }
            FamilyImg newFamilyImg = familyImgMapper.selectById(familyImg.getId());
            newFamilyImg.setStatus(FamilyImg.STATUS_DEFAULT);
            familyImgMapper.updateSelect(newFamilyImg);

            return familyService.getFamilyById(family);
        }
        return "update failure";
    }



}
