package summerain.privacy.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import summerain.privacy.service.CollectionService;
import summerain.privacy.service.PrivacyService;
import summerain.privacy.service.ResultService;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.Map;


/**
 * @author ：summerain
 * @date ：Created in 2021/5/5 11:33 下午
 */
@Controller
public class PrivacyController {

    @Autowired
    PrivacyService privacyService;

    @Autowired
    ResultService resultService;

    @Autowired
    CollectionService collectionService;


    @PostMapping("/text")
    public String text(@RequestParam("protocol") String content, HttpSession session) {
        Integer privacyId = privacyService.getIdByContent(content);
        Integer resultId = 0;
        if (privacyId == 0) {
            System.out.println(content);
            try {
                File writename = new File("/Users/sunbowen/PycharmProjects/nlp/textToProcess.txt");
                writename.createNewFile();
                BufferedWriter out = new BufferedWriter(new FileWriter(writename));
                out.write(content); // \r\n为换行
                out.flush(); // 把缓存区内容压入文件
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            Process proc;
            String line = null;
            try {
                proc = Runtime.getRuntime().exec("/Users/sunbowen/PycharmProjects/nlp/venv/bin" + "/python3 /Users/sunbowen/PycharmProjects/nlp/main.py ");// 执行py文件
                //用输入输出流来截取结果
                BufferedReader in =
                        new BufferedReader(new InputStreamReader(proc.getInputStream()));
                line = in.readLine();
                in.close();
                proc.waitFor();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            privacyId = privacyService.insert(content);
            resultId = resultService.insert(line, privacyId);
            if (session.getAttribute("userId") != null) {
                Integer userId = (Integer) session.getAttribute("userId");
                if (collectionService.isNew(content)) {
                    collectionService.insert(userId, content, resultId);
                }
            }
            return "redirect:/result/" + resultId;
        }
        resultId = resultService.getByPrivacyId(privacyId);
        if (session.getAttribute("userId") != null) {
            Integer userId = (Integer) session.getAttribute("userId");
            if (collectionService.isNew(content)){
                collectionService.insert(userId,content.substring(0,10)+"......", resultId);
            }
        }
        return "redirect:/result/" + resultId;

    }

    @GetMapping("/result/{resultId}")
    public String result(@PathVariable("resultId") Integer resultId, Model model) {
        String jsonString = resultService.getById(resultId);
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<String, Object>();
        map = gson.fromJson(jsonString, map.getClass());
        System.out.println(map);
        model.addAttribute("map", map);
        return "result";
    }
}
