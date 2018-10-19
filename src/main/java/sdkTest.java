import com.baidu.aip.ocr.AipOcr;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.imageio.stream.FileImageInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class sdkTest {
    //设置APPID/AK/SK
    public static final String APP_ID = "14472164";
    public static final String API_KEY = "BoBfMB0YAmAYdVKaLwU1skgC";
    public static final String SECRET_KEY = "a1sRsoN2yhmkBvTVqmZQT3diVI8ujHqg";
    //

    public static void main(String[] args) {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        //client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        //client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量

        // 调用接口
        sample(client);

    }

    public static void sample(AipOcr client) {
        long start_time = System.currentTimeMillis();
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();

        String templateSign = "66669901e55a39807964b385d134eb7e";

        // 参数为本地图片路径
        String image = "D:\\11.png";
        JSONObject res = client.custom(image, templateSign, options);
        long end_time = System.currentTimeMillis();
        System.out.println((end_time - start_time) / 1000);
        JSONObject data = res.getJSONObject("data");
        JSONArray Array1 = data.getJSONArray("ret");
        for (int i = 0; i < Array1.length(); i++) {
            JSONObject Array2 = Array1.getJSONObject(i);
            Object ob1 = Array2.get("word_name");
            Object ob2 = Array2.get("word");
            System.err.println(ob1.toString() + ob2.toString());

        }
        System.err.println(Array1);
    }

    //图片到byte数组
    public static byte[] readImageFile(String path) {
        byte[] data = null;
        FileImageInputStream input = null;
        try {
            input = new FileImageInputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
            output.close();
            input.close();
        } catch (FileNotFoundException ex1) {
            ex1.printStackTrace();
        } catch (IOException ex1) {
            ex1.printStackTrace();
        }
        return data;

    }
}
