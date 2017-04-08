package smvcMybatis.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import smvcMybatis.Entity.User;
import smvcMybatis.Service.UserService;
import smvcMybatis.util.JsonResult;
import smvcMybatis.vo.UserCondition;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("user")
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "login")
	@ResponseBody
	public JsonResult login(@RequestBody UserCondition condition, Model model) {
		User user = userService.getUserByName(condition.getName());
		if (user == null) {
			return new JsonResult(JsonResult.FAIL, null, "用户名不存在");
		} else if (!user.getPassword().equals(condition.getPassword())) {
			return new JsonResult(JsonResult.FAIL, null, "密码错误");
		}
		model.addAttribute("user", user);
		return new JsonResult(JsonResult.SUCCESS, null, null);
	}
	
//	@RequestMapping(value = "/loginTest")
//	public ModelAndView loginTest(HttpSession httpSession,@RequestBody UserCondition condition) {
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("name", condition.getUserName());
//		User user = userService.getUserByName(condition.getUserName());
//		if (user == null || !user.getPassword().equals(condition.getPassword())) {
//			mav.setViewName("login");
//			return mav;
//		} else {
//			mav.setViewName("main");
//			httpSession.setAttribute("user", user);
//			return mav;
//		}
//	}
	
//	@RequestMapping(value = "/loginTest") //form表单提交时那么不能像struts2一样命名为对象属性模式
//	public ModelAndView loginTest(HttpSession httpSession,@RequestBody UserCondition condition) {
//		User user = userService.getUserByName(condition.getUserName());
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("main");
//		httpSession.setAttribute("user", user);
//		return mav;
//	}

	@RequestMapping(value = "/test")
	public ModelAndView loginTest() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("testJsp/container");
		return mav;
	}
}
