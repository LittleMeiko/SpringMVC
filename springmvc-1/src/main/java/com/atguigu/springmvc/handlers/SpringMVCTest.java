package com.atguigu.springmvc.handlers;

import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.atguigu.springmvc.entities.Address;
import com.atguigu.springmvc.entities.User;

/**
 * 标准的HTTP请求头包含如下部分：
 * 	1.请求方法
 *  2.请求的URL
 *  3.请求的协议及版本
 *  4.请求头
 *  5.请求参数
 * 
 * @RequestMapping除了可以使用请求URL映射请求外，还可以使用请求方法、请求参数、请求头来映射请求
 * 	value:请求URL
 *  method:请求方法
 *  params:请求参数
 *    -- param1:表示请求必须包含名为param1的请求参数
 *    -- !param1:表示请求不能包含名为param1的请求参数
 *    -- parma1 != value1:表示请求必须包含名为param1的请求参数,但其值不能为value1
 *    -- {"parma1=value1","param2"}:表示请求必须包含名为param1和param2的请求参数，且param1参数的值必须为value1
 *  headers:请求头
 * 联合使用多个条件可以让请求更加精确化
 * 
 * @RequestMapping的URL支持三种形式的通配符：
 * 	？:匹配一个字符
 * 	   /user/createUser??可以匹配:/user/createUseraa、/user/createUserbb等URL
 *  *:匹配任意字符
 *  **:匹配多层路径
 *  
 *  @PathVariable映射URL绑定的占位符：可以将URL中占位符参数绑定到控制器方法的入参中
 *  
 *  REST:Representational State Transfer即(资源)表现层状态转化。是一种互联网软件架构。
 *    -- 资源(Resources):网络上的一个实体，或者是网络上的一个具体信息。URI(统一资源定位符)为每一个资源的独一无二的识别符
 *    -- 表现层(Representation):把资源具体呈现出来的形式，叫做它的表现层
 *    -- 状态转化(State Tranafer):如果客户端想要操作服务器，必须通过某种手段，让服务器端发生状态转化。而这种转化就是建立在
 *    	 表现层之上的，所以就是"表现层状态转化"。
 *    	 HTTP协议里四个表示操作方式的动词：
 *    	  -- GET:获取资源
 *        -- POST:新建资源
 *        -- PUT:更新资源
 *        -- DELETE:删除资源
 *    HiddenHttpMethodFilter过滤器可将POST请求转化为PUT/DELETE请求
 *  
 * 
 * @author Meiko
 *
 */

/**
 * 处理模型数据输出的方式三：将模型中的某个属性暂存到HttpSession中，以便多个请求之间可以共享这个属性
 * 此注解只能放在类的上面，而不能放到方法的上面
 */
//@SessionAttributes(value = {"user"}, types = {Address.class})
@RequestMapping("/springmvc")
@Controller
public class SpringMVCTest {
	
	private static final String SUCCESS = "success";
	
	/**
	 * 视图解析器的作用：将 逻辑视图 转换为 物理视图
	 * 工作过程：
	 * 	1.无论目标方法返回什么类型的返回值(String、View、ModelAndView、ModelMap等)，SpringMVC都会将其封装成一个ModelAndView
	 *  2.视图解析器从ModelAndView解析出View
	 *  3.最终调用View的render方法渲染出具体的视图
	 * 
	 */
	@RequestMapping(value = "/testResourceViewReslover")
	public String testResourceViewReslover() {
		System.out.println("testResourceViewReslover");
		return SUCCESS;
	}
	
	/**
	 * 处理模型数据输出的方式四：有@ModelAttribute注解标记的方法，会在每个目标方法执行之前被SprimgMVC调用
	 */
	@ModelAttribute
	public void getUser(@RequestParam(value = "id", required = false) Integer id,
			Map<String, Object> map) {
		System.out.println("ModelAttribute Method...");
		if (Objects.nonNull(id)) {
			//模拟从数据库中获取对象
			User user = new User(1, "Tom", "123456", "tom@atguigu.com", 13);
			System.out.println("从数据库中获取一个对象：" + user);
			
			map.put("user", user);
		}
	}
	
	/**
	 * 运行流程：
	 * 1.执行@ModelAttribute注解标记的方法：从数据库中取出对象，并将其放入map中(键为user)
	 * 2.SpringMVC从Map中取出User对象，并把表单的请求参数赋值给该User对象的对应属性
	 * 3.SpringMVC把上述对象传入目标方法的入参
	 * 
	 * 源码分析过程：
	 * 1.首先调用@ModelAttribute注解修饰的方法，实际上把@ModelAttribute方法中Map中的数据放在了implictModel中
	 * 2.解析请求处理器的目标参数，实际该目标参数来自于WebDataBinder对象的target属性
	 *   (1)创建WebDataBinder对象：
	 *     1)确定objectName属性：若传入的attrName为"",则objectName为目标方法入参的类名第一个字母小写对应的字符串
	 *       注意：若目标方法的POJO属性使用了@ModelAttribute注解来修饰，则attrName的值即为@ModelAttribute注解的value属性值
	 *     2)确定target属性： 
	 *       > 在implictModel中查找attrName对应的属性值，若存在，ok
	 *       > 若不存在:
	 *       	> 则验证当前Handler是否使用了@SessionAttributes注解修饰，若使用了则尝试从session中获取attrName对应的属性值
	 *            若session中没有对应的属性值则抛出异常
	 *          > 若Handler没有使用@SessionAttributes注解修饰，或者@SessionAttributes中没有使用value值指定的key和attrName
	 *            相匹配，则通过反射创建POJO对象
	 *   (2)SpringMVC把表单的请求参数赋值给WebDataBinder对象的target对应的属性
	 * 3.SpringMVC会把WebDataBinder的attrName和target给到implictModel,进而传到request域对象中
	 * 4.把WebDataBinder的target属性作为参数传递给目标方法的入参
	 *			 
	 */
	@RequestMapping(value = "/testModelAttribute")
	public String testModelAttribute(@ModelAttribute(value = "user")User user) {
		System.out.println("修改：" + user);
		return SUCCESS;
	}
	
	@RequestMapping(value = "/testSessionAttributes")
	public String testSessionAttributes (Map<String, Object> map) {
		User user = new User("Tom", "123456", "tome@atugui.com", 15);
		map.put("user", user);
		Address address = new Address("GuangDong", "ShenZhen");
		map.put("address", address);
		return SUCCESS;
	}
	
	
	/**
	 * 处理模型数据输出的方式二：目标方法的入参可以为Model、ModelMap、java.util.Map
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/testMap")
	public String testMap(Map<String, List<String>> map) {
		map.put("names", Arrays.asList("Tom", "Jerry", "Meiko"));	
		return SUCCESS;
	}
	
	/**
	 * 处理模型数据输出的方式一：处理方法返回值类型为ModelAndView时，方法体即可通过该对象添加模型数据
	 * 其中可以包含视图和模型数据
	 * 
	 * SpringMVC会把ModelAndView的model中的数据放到request的域对象中
	 * 
	 */
	@RequestMapping(value = "/testModelAndView")
	public ModelAndView testModelAndView() {
		String viewName = SUCCESS;
		ModelAndView modelAndView = new ModelAndView(viewName);
		modelAndView.addObject("time", LocalDateTime.now());
		return modelAndView;
	}
	
	/**
	 * 可以使用Servlet原生的API作为目标方法的入参，主要支持以下类型： 
	 * HttpSerlvletRequest 
	 * HttpSerlvletResponse
	 * HttpSession 
	 * java.security.Principal 
	 * Locale 
	 * InputStream 
	 * OutputStream 
	 * Reader
	 * Writer
	 * @throws IOException 
	 */
	@RequestMapping(value = "/testServletAPI")
	public void testServletAPI(HttpServletRequest request, 
			HttpServletResponse response, Writer writer) throws IOException {
		System.out.println("request:" + request);
		System.out.println("response:" + response);
		writer.write("hello springmvc");
	}
	
	/**
	 * SpringMVC会按请求参数名和POJO属性名进行自动匹配
	 * 自动为该对象填充属性值，且支持级联属性赋值
	 */
	@RequestMapping(value = "/testPojo")
	public String testPojo(User user) {
		System.out.println("user:" + user);
		return SUCCESS;
	}
	
	/**
	 * @CookieValue：映射一个Cookie值
	 */
	@RequestMapping(value = "testCookieValue")
	public String testCookieValue(@CookieValue(value = "JSESSIONID") String sessionId) {
		System.out.println("testCookieValue:sessionId = " + sessionId);
		return SUCCESS;
	}
	
	
	/**
	 * @RequestHeader:映射请求头信息
	 */
	@RequestMapping(value = "/testRequestHeander")
	public String testRequestHeander(@RequestHeader(value = "accept-language") String al) {
		System.out.println("accept-language:" + al);
		return SUCCESS;
	}
	
	
	/**
	 * @RequestParam 用于映射请求参
	 * 	value--请求参数名称
	 *  required--请求参数是否为必须，默认值为true
	 *  defaultValue--请求参数的默认值
	 * 
	 */
	@RequestMapping(value = "/testRequestParam")
	public String testRequestParam(@RequestParam(value = "username") String userName,
			@RequestParam(value = "age", required = false, defaultValue = "0") int age) {
		System.out.println("testRequestParam: userName=" + userName + ", age = " + age);
		return SUCCESS;
	}
	
	// DELETE 和 PUT 是不支持转发的，只支持重定向，所以发送put和delete请求时浏览器会报405错误
	@RequestMapping(value = "/testRestPut/{id}", method = RequestMethod.PUT)
	public String testRestPut(@PathVariable("id")Integer id) {
		System.out.println("testRestPut:" + id);
		return SUCCESS;
	}
	
	@RequestMapping(value = "/testRestDelete/{id}", method = RequestMethod.DELETE)
	public String testRestDelete(@PathVariable("id")Integer id) {
		System.out.println("testRestDelete:" + id);
		return SUCCESS;
	}
	
	@RequestMapping(value = "/testRestPost", method = RequestMethod.POST)
	public String testRestPost() {
		System.out.println("testRestPost");
		return SUCCESS;
	}
	
	@RequestMapping(value = "/testRestGet/{id}", method = RequestMethod.GET)
	public String testRestGet(@PathVariable("id")Integer id) {
		System.out.println("testRestGet:" + id);
		return SUCCESS;
	}
	
	@RequestMapping("/testPathVariable/{id}")
	public String testPathVariable(@PathVariable("id") Integer id) {
		System.out.println("testPathVariable:" + id);
		return SUCCESS;
	}
	
	@RequestMapping("/testAntPath/**/mvc??")
	public String testAntPath() {
		System.out.println("testAntPath");
		return SUCCESS;
	}
	
	@RequestMapping(value = "/testParamsAndHeaders", 
			params = {"username", "age!=11"}, headers = "Accept-Language=en-US,zh;q=0.9")
	public String testParamsAndHeaders() {
		System.out.println("testParamsAndHeaders");
		return SUCCESS;
	}
	
	/**
	 * 较为常用：使用请求方法来映射请求
	 * @return
	 */
	@RequestMapping(value = "/testMethod", method = RequestMethod.POST)
	public String testMethod() {
		System.out.println("testMethod");
		return SUCCESS;
	}
	
	/**
	 * 1.@RequestMapping不仅可以修饰方法还可以修饰类
	 * 2.类定义处：提供初步的请求映射信息，相对于WEB应用的根目录
	 *   方法处：提供进一步的细分映射信息，相对于类定义处的URL。若类定义处未标注@RequestMapping，则方法标记
	 *   	   的URL相对于WEB应用的根目录。
	 *         
	 * @return
	 */
	@RequestMapping("/testRequestMapping")
	public String testRequestMapping() {
		System.out.println("testRequestMapping");
		return SUCCESS;
	}

}
