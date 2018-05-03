package it.caoxin.smarthome.app.controller.scene;

import it.caoxin.smarthome.domain.model.Family;
import it.caoxin.smarthome.domain.model.Scene;
import it.caoxin.smarthome.domain.model.User;
import it.caoxin.smarthome.domain.service.family.FamilyService;
import it.caoxin.smarthome.domain.service.scene.SceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SceneContoller {
    @Autowired
    private SceneService sceneService;

    //添加场景
    @RequestMapping(value = "/add_scene",method = RequestMethod.POST)
    @ResponseBody
    public String addScene(Scene scene){
        //获取不到uniqueCode
        System.out.println("Scene:"+scene);

       return  sceneService.addScene(scene);
    }

    //查找场景通过家庭id，设备id，用户id
    @RequestMapping(value = "/get_scenebyotherid",method = RequestMethod.POST)
    @ResponseBody
    public String getSceneByOtherId(Scene scene){
        //获取不到uniqueCode
        System.out.println("Scene:"+scene);

        return  sceneService.findSceneByOtherId(scene);
    }

    //删除场景
    @RequestMapping(value = "/del_scene",method = RequestMethod.POST)
    @ResponseBody
    public String deleteScene(Integer sceneId){

        System.out.println("sceneId:"+sceneId);
        Scene scene = new Scene();
        scene.setId(sceneId);
        return  sceneService.deleteScene(scene);
    }


}
