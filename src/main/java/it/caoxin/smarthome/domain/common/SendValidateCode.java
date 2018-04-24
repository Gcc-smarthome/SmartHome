package it.caoxin.smarthome.domain.common;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.util.Random;

public class SendValidateCode {
    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAIgCEqX0wsVK86";
    static final String accessKeySecret = "fzaLFR1OI1qwtl3pr5hZ0SQfuHFMoC";

    public static SendSmsResponse sendSms() throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers("18320338949");
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("智能家居云平台");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_126270121");

        String validateCode = "";
        Random random = new Random();
        for(int i = 0; i < 4; i++){
            int randomNumber = random.nextInt(10);
            validateCode += randomNumber;
        }
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"code\":"+validateCode+"}");
        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }


    public static String testCode(){
        String validateCode = "";
        Random random = new Random();
        for(int i = 0; i < 6; i++){
            int randomNumber = random.nextInt(10);
            validateCode += randomNumber;
        }
        System.out.println("验证码为：-----------------"+validateCode);

        return validateCode;
    }
}
