package core.mvc;

import jwp.controller.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RequestMapper {
    private HttpServletRequest req;
    private HttpServletResponse resp;
    private Map<String, Controller> controllers = new HashMap<>();
    private Controller controller;

    public RequestMapper(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
        initControllers();
        controller = controllers.get(req.getRequestURI());
    }

    private void initControllers() {
        controllers.put("/", new HomeController());
        controllers.put("/user/signup", new CreateUserController());
        controllers.put("/user/userList", new ListUserController());
        controllers.put("/user/updateForm", new UpdateUserFormController());
        controllers.put("/user/update", new UpdateUserController());
        controllers.put("/user/login", new LoginController());
        controllers.put("/user/logout", new LogoutController());
    }

    public void proceed() throws ServletException, IOException {
        String result = controller.execute(req, resp);

        if (result.startsWith("redirect:")) {
            int index = result.indexOf(":");
            String url = result.substring(index + 1);
            resp.sendRedirect(url);
            return;
        }
        RequestDispatcher rd = req.getRequestDispatcher(result);
        rd.forward(req, resp);
    }
}