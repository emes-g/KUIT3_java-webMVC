package core.mvc.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

// 데이터(모델)와 이동할 뷰 페이지를 같이 저장
public class ModelAndView {
    View view;
    Map<String, Object> model = new HashMap<>();

    public ModelAndView(View view) {
        this.view = view;
    }

    public ModelAndView addObject(String key, Object val) {
        model.put(key, val);
        return this;
    }

    public void render(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        view.render(model, req, resp);
    }
}